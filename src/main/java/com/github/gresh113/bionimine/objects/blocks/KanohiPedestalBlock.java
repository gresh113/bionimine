package com.github.gresh113.bionimine.objects.blocks;

import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class KanohiPedestalBlock extends Block{

	public KanohiPedestalBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return BionimineTileEntityTypes.KANOHI_PEDESTAL.get().create();
	}
}
