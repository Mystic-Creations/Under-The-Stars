package net.mysticcreations.underthestars.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Smore extends Block {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 1, 3);
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    private static final VoxelShape SINGLE = Block.box(2, 0, 2, 14, 4, 14);
    private static final VoxelShape DOUBLE = Block.box(1, 0, 1, 15, 6, 15);
    private static final VoxelShape TRIPLE = Block.box(0, 0, 0, 16, 8, 16);

    public Smore() {
        super(Properties.of()
            .mapColor(MapColor.WOOL)
            .strength(0.4F)
            .sound(SoundType.WOOL)
            .noOcclusion()
            .lightLevel(state -> 0)
            .isSuffocating((bs, br, bp) -> false)
            .isViewBlocking((bs, br, bp) -> false)
        );

        this.registerDefaultState(this.stateDefinition.any()
            .setValue(COUNT, 1)
            .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (state.is(this)) {
            int count = state.getValue(COUNT);
            if (count < 3) {
                return state.setValue(COUNT, count + 1);
            }
            return null;
        }

        return this.defaultBlockState()
            .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!player.isShiftKeyDown()
            && stack.getItem() instanceof BlockItem blockItem
            && blockItem.getBlock() == this) {

            int count = state.getValue(COUNT);
            if (count < 3) {
                if (!level.isClientSide) {
                    level.setBlock(pos, state.setValue(COUNT, count + 1), 3);
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            return InteractionResult.PASS;
        }

        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            player.getFoodData().eat(2, 0.3F);

            int count = state.getValue(COUNT);
            if (count > 1) {
                level.setBlock(pos, state.setValue(COUNT, count - 1), 3);
            } else {
                level.removeBlock(pos, false);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(COUNT)) {
            case 2 -> DOUBLE;
            case 3 -> TRIPLE;
            default -> SINGLE;
        };
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COUNT, FACING);
    }
}
