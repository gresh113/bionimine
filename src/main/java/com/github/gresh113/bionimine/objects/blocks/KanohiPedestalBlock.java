package com.github.gresh113.bionimine.objects.blocks;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.objects.tileentity.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.objects.tileentity.KanohiPedestalTileEntity;
import com.github.gresh113.bionimine.state.properties.MaskType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KanohiPedestalBlock extends Block {
	protected static final VoxelShape BASE_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	protected static final VoxelShape MASK_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 13.0D, 15.0D);

	public static final EnumProperty<MaskType> MASK = BionimineBlockProperties.MASK;
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public KanohiPedestalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(MASK, MaskType.NONE).with(FACING, Direction.NORTH));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return BionimineTileEntityTypes.KANOHI_PEDESTAL.get().create();
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (state.get(MASK) == MaskType.NONE) {
			return BASE_SHAPE;
		} else {
			return MASK_SHAPE;
		}

	}

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (state.get(MASK) == MaskType.NONE) {
			return BASE_SHAPE;
		} else {
			return MASK_SHAPE;
		}

	}

	@Override
	public boolean isTransparent(BlockState state) {
		if (state.get(MASK) == MaskType.NONE) {
			return true;
		} else {
			return false;
		}
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(MASK, MaskType.NONE);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(MASK).add(FACING);
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult trace) {

		if (worldIn.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof KanohiPedestalTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (KanohiPedestalTileEntity) tile, pos);
			}
			return ActionResultType.SUCCESS;
		}
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof KanohiPedestalTileEntity) {
				InventoryHelper.dropItems(worldIn, pos, ((KanohiPedestalTileEntity) te).getItems());
			}
		}
	}

	

}
