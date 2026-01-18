package net.mysticcreations.underthestars.blockTypes;

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
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
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

public abstract class SleepingBag extends BedBlock {

    public static final EnumProperty<BedPart> PART = EnumProperty.create("part", BedPart.class);
    public static final BooleanProperty OCCUPIED = BooleanProperty.create("occupied");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected static final VoxelShape BAG = Block.box(0, 0, 0, 16, 2, 16);
    protected static final VoxelShape NORTH_PILLOW = Block.box(2, 2, 1, 14, 3, 7);
    protected static final VoxelShape EAST_PILLOW  = Block.box(9, 2, 2, 15, 3, 14);
    protected static final VoxelShape SOUTH_PILLOW = Block.box(2, 2, 9, 14, 3, 15);
    protected static final VoxelShape WEST_PILLOW  = Block.box(1, 2, 2, 7, 3, 14);

    protected SleepingBag(DyeColor color) {
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

        var result = player.startSleepInBed(footPos);
        result.left().ifPresent(problem ->
            player.displayClientMessage(problem.getMessage(), true));

        if (player instanceof ServerPlayer sp) {
            sp.setRespawnPosition(world.dimension(), null, 0.0F, false, false);
        }

        return InteractionResult.SUCCESS;
    }
}
