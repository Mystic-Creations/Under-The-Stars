package net.mysticcreations.underthestars.block;

import com.mojang.datafixers.util.Either;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.mixin.PlayerAccessor;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class SleepingBag extends BedBlock {
    public static final EnumProperty<BedPart> PART = EnumProperty.create("part", BedPart.class);
    public static final BooleanProperty OCCUPIED = BooleanProperty.create("occupied");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected static final VoxelShape BAG = Block.box(0, 0, 0, 16, 2, 16);
    protected static final VoxelShape NORTH_PILLOW = Block.box(2, 2, 1, 14, 3, 7);
    protected static final VoxelShape EAST_PILLOW  = Block.box(9, 2, 2, 15, 3, 14);
    protected static final VoxelShape SOUTH_PILLOW = Block.box(2, 2, 9, 14, 3, 15);
    protected static final VoxelShape WEST_PILLOW  = Block.box(1, 2, 2, 7, 3, 14);

    public SleepingBag(DyeColor color) {
        super(color, BlockBehaviour.Properties.copy(Blocks.WHITE_BED).sound(SoundType.WOOL));
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(PART, BedPart.FOOT)
            .setValue(OCCUPIED, false)
            .setValue(FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        BedPart part = state.getValue(PART);

        VoxelShape pillow = Shapes.empty();
        if (part == BedPart.HEAD) {
            pillow = switch (direction) {
                case NORTH -> NORTH_PILLOW;
                case SOUTH -> SOUTH_PILLOW;
                case WEST  -> WEST_PILLOW;
                case EAST  -> EAST_PILLOW;
                default    -> Shapes.empty();
            };
        }

        return Shapes.or(BAG, pillow);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state,
                            @Nullable LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide) {
            Direction direction = state.getValue(FACING);
            level.setBlock(pos.relative(direction),
                state.setValue(PART, BedPart.HEAD), 3);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        if (world.isClientSide) return InteractionResult.SUCCESS;

        Direction direction = state.getValue(FACING);
        BedPart part = state.getValue(PART);

        BlockPos footPos = (part == BedPart.FOOT) ? pos : pos.relative(direction.getOpposite());
        BlockPos headPos = footPos.relative(direction);

        if (player.isShiftKeyDown()) {
            world.removeBlock(headPos, false);
            world.removeBlock(footPos, false);
            player.getInventory().add(new ItemStack(this));
            return InteractionResult.SUCCESS;
        }

        if (!world.dimensionType().natural()) {
            world.removeBlock(footPos, false);
            world.removeBlock(headPos, false);
            world.explode(null,
                footPos.getX() + 0.5,
                footPos.getY() + 0.5,
                footPos.getZ() + 0.5,
                5.0F,
                Level.ExplosionInteraction.BLOCK);
            return InteractionResult.SUCCESS;
        }

        if (state.getValue(OCCUPIED)) {
            player.displayClientMessage(
                Component.translatable("block.minecraft.bed.occupied"), true);
            return InteractionResult.SUCCESS;
        }

        var result = makePlayerSleep((ServerPlayer) player,footPos);
        result.left().ifPresent(problem ->
            player.displayClientMessage(problem.getMessage(), true));

        return InteractionResult.SUCCESS;
    }

    public Either<Player.BedSleepingProblem, Unit> makePlayerSleep(ServerPlayer player, BlockPos bedPos) {
        Direction direction = player.level().getBlockState(bedPos).getValue(HorizontalDirectionalBlock.FACING);

        if (!player.isSleeping() && player.isAlive()) {
            if (!player.level().dimensionType().natural()) {
                return Either.left(Player.BedSleepingProblem.NOT_POSSIBLE_HERE);
            } else if (!bedInRange(bedPos, direction, player)) {
                return Either.left(Player.BedSleepingProblem.TOO_FAR_AWAY);
            } else if (bedBlocked(bedPos, direction, player)) {
                return Either.left(Player.BedSleepingProblem.OBSTRUCTED);
            } else {
                if (player.level().isDay()) {
                    return Either.left(Player.BedSleepingProblem.NOT_POSSIBLE_NOW);
                } else {
                    if (!player.isCreative()) {
                        double d = 8.0;
                        double e = 5.0;
                        Vec3 vec3 = Vec3.atBottomCenterOf(bedPos);
                        List<Monster> list = player.level().getEntitiesOfClass(
                                Monster.class,
                                new AABB(
                                        vec3.x() - d, vec3.y() - e, vec3.z() - d,
                                        vec3.x() + d, vec3.y() + e, vec3.z() + d
                                ),
                                (monster) -> monster.isPreventingPlayerRest(player)
                        );

                        if (!list.isEmpty()) {
                            return Either.left(Player.BedSleepingProblem.NOT_SAFE);
                        }
                    }

                    Either<Player.BedSleepingProblem, Unit> either = this.startSleepInBed(bedPos, player).ifRight((unit) -> {
                        player.awardStat(Stats.SLEEP_IN_BED);
                        CriteriaTriggers.SLEPT_IN_BED.trigger((ServerPlayer) player);
                    });

                    if (!player.serverLevel().canSleepThroughNights()) {
                        player.displayClientMessage(Component.translatable("sleep.not_possible"), true);
                    }

                    ((ServerLevel) player.level()).updateSleepingPlayerList();
                    return either;
                }
            }
        } else {
            return Either.left(Player.BedSleepingProblem.OTHER_PROBLEM);
        }
    }

    private boolean bedInRange(BlockPos pos, Direction direction, Player player) {
        return isReachableBedBlock(pos, player) || isReachableBedBlock(pos.relative(direction.getOpposite()), player);
    }

    private boolean isReachableBedBlock(BlockPos pos, Player player) {
        Vec3 vec3 = Vec3.atBottomCenterOf(pos);
        return Math.abs(player.getX() - vec3.x()) <= (double)3.0F && Math.abs(player.getY() - vec3.y()) <= (double)2.0F && Math.abs(player.getZ() - vec3.z()) <= (double)3.0F;
    }

    private boolean bedBlocked(BlockPos pos, Direction direction, ServerPlayer player) {
        BlockPos blockPos = pos.above();
        return !freeAt(blockPos, player) || !freeAt(blockPos.relative(direction.getOpposite()), player);
    }

    protected boolean freeAt(BlockPos pos, ServerPlayer player) {
        return !player.level().getBlockState(pos).isSuffocating(player.level(), pos);
    }

    public void startSleeping(BlockPos pos, ServerPlayer player) {
        if (!UnderTheStars.hasAdvancement(player, "exploration/sleeping_bag"))
            UnderTheStars.grantAdvancement(player, "exploration/sleeping_bag");
        if (player.isPassenger()) {
            player.stopRiding();
        }

        player.setPose(Pose.SLEEPING);
        player.setPos((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.6875F, (double)pos.getZ() + (double)0.5F);
        player.setSleepingPos(pos);
        player.setDeltaMovement(Vec3.ZERO);
        player.hasImpulse = true;
    }

    public Either<Player.BedSleepingProblem, Unit> startSleepInBed(BlockPos bedPos,ServerPlayer player) {
        this.startSleeping(bedPos,player);
        ((PlayerAccessor)player).setSleepCounter(0);
        return Either.right(Unit.INSTANCE);
    }
}