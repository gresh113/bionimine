package com.github.gresh113.bionimine.objects.tileentity;

import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class KanohiPedestalTileEntity extends LockableLootTileEntity{
	
	//public int x,y,z;
	//boolean initialized = false;

	public KanohiPedestalTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public KanohiPedestalTileEntity() {
		this(BionimineTileEntityTypes.KANOHI_PEDESTAL.get());
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("kanohi_pedestal");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		// TODO Auto-generated method stub
		return null;
	}

}
