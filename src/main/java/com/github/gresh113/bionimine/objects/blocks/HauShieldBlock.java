package com.github.gresh113.bionimine.objects.blocks;

import com.github.gresh113.bionimine.objects.tileentity.BionimineTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class HauShieldBlock extends Block {

	protected static final VoxelShape SHIELD_SOUTH_AABB = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SHIELD_WEST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape SHIELD_NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape SHIELD_EAST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public HauShieldBlock(Properties properties) {
		super(properties);

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return BionimineTileEntityTypes.HAU_SHIELD.get().create();
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch ((Direction) state.get(FACING)) {
		case NORTH:
			return SHIELD_NORTH_AABB;
		case SOUTH:
			return SHIELD_SOUTH_AABB;
		case WEST:
			return SHIELD_WEST_AABB;
		case EAST:
		default:
			return SHIELD_EAST_AABB;
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public boolean isTransparent(BlockState state) {
		return false;
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return true;
	}

	public BlockState setShieldDirection(Direction direction) {
		if (direction == Direction.NORTH) {
			return this.getDefaultState().with(FACING, Direction.EAST);
		} else if (direction == Direction.SOUTH) {
			return this.getDefaultState().with(FACING, Direction.WEST);
		} else if (direction == Direction.EAST) {
			return this.getDefaultState().with(FACING, Direction.NORTH);
		} else if (direction == Direction.WEST) {
			return this.getDefaultState().with(FACING, Direction.SOUTH);
		} else
			return this.getDefaultState().with(FACING, Direction.NORTH);
	}

//	@Override
//	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
//		return false;
//	}
	

}
