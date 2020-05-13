package com.github.gresh113.bionimine.objects.blocks;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.objects.tileentity.KanohiPedestalTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class KanohiPedestalBlock extends ContainerBlock {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty HAS_MASK = BionimineBlockProperties.HAS_MASK;

	public KanohiPedestalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(HAS_MASK,
				Boolean.valueOf(false)));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return BionimineTileEntityTypes.KANOHI_PEDESTAL.get().create();
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return BionimineTileEntityTypes.KANOHI_PEDESTAL.get().create();
	}

	// Rendering Methods
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch ((Direction) state.get(FACING)) {
		case NORTH:
			return SHAPE;
		case SOUTH:
			return SHAPE;
		case EAST:
			return SHAPE;
		case WEST:
			return SHAPE;
		default:
			return SHAPE;
		}
	}

	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}

	public boolean isTransparent(BlockState state) {
		return true;
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return SHAPE;
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, HAS_MASK);
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		ResourceLocation masks = new ResourceLocation("bionimine", "items");
		if (state.get(HAS_MASK)) {
			return ActionResultType.FAIL;
		} else {
			ItemStack itemstack = player.getHeldItem(handIn);
			Item item = itemstack.getItem();
			boolean isInGroup = ItemTags.getCollection().getOrCreate(masks).contains(item);
			return !itemstack.isEmpty() && isInGroup ? ActionResultType.CONSUME : ActionResultType.PASS;
		}
	}

	public static boolean tryPlaceMask(World worldIn, BlockPos pos, BlockState state, ItemStack stack) {
		if (!state.get(HAS_MASK)) {
			if (!worldIn.isRemote) {
				placeMask(worldIn, pos, state, stack);
			}

			return true;
		} else {
			return false;
		}
	}

	private static void placeMask(World worldIn, BlockPos pos, BlockState state, ItemStack stack) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof KanohiPedestalTileEntity) {
			KanohiPedestalTileEntity kanohipedestal = (KanohiPedestalTileEntity) tileentity;
			kanohipedestal.setMask(stack.split(1));
			setHasMask(worldIn, pos, state, true);
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundCategory.BLOCKS, 1.0F,
					1.0F);
		}

	}

	public static void setHasMask(World worldIn, BlockPos pos, BlockState state, boolean hasMask) {
		worldIn.setBlockState(pos, state.with(HAS_MASK, Boolean.valueOf(hasMask)), 3);
		notifyNeighbors(worldIn, pos, state);

	}

	private static void notifyNeighbors(World worldIn, BlockPos pos, BlockState state) {
		worldIn.notifyNeighborsOfStateChange(pos.down(), state.getBlock());
	}

	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			if (state.get(HAS_MASK)) {
				this.dropMask(state, worldIn, pos);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	private void dropMask(BlockState state, World world, BlockPos pos) {
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof KanohiPedestalTileEntity) {
			KanohiPedestalTileEntity pedestal = (KanohiPedestalTileEntity) tileentity;
			Direction direction = state.get(FACING);
			ItemStack itemstack = pedestal.getMask().copy();
			float f = 0.25F * (float) direction.getXOffset();
			float f1 = 0.25F * (float) direction.getZOffset();
			ItemEntity itementity = new ItemEntity(world, (double) pos.getX() + 0.5D + (double) f,
					(double) (pos.getY() + 1), (double) pos.getZ() + 0.5D + (double) f1, itemstack);
			itementity.setDefaultPickupDelay();
			world.addEntity(itementity);
			pedestal.clear();
		}

	}

}
