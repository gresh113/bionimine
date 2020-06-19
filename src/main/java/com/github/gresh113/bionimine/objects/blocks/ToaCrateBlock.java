package com.github.gresh113.bionimine.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

public class ToaCrateBlock extends DirectionalBlock {

	public ToaCrateBlock(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	 public BlockState getStateForPlacement(BlockItemUseContext context) {
	      return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
	   }
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

}
