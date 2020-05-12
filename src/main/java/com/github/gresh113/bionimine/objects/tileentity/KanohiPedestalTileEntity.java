package com.github.gresh113.bionimine.objects.tileentity;

import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class KanohiPedestalTileEntity extends TileEntity{
	
	//public int x,y,z;
	//boolean initialized = false;

	public KanohiPedestalTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public KanohiPedestalTileEntity() {
		this(BionimineTileEntityTypes.KANOHI_PEDESTAL.get());
	}

}
