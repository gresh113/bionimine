package com.github.gresh113.bionimine.objects.tileentity;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HauShieldTileEntity extends TileEntity implements ITickableTileEntity {

	public HauShieldTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public HauShieldTileEntity() {
		this(BionimineTileEntityTypes.HAU_SHIELD.get());
	}

	@Override
	public void tick() {
		World world = this.getWorld();
		if (!world.isRemote) {
			BlockPos pos = this.getPos();
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}

	}

}
