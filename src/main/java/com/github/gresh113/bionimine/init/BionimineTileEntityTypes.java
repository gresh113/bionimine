package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.objects.tileentity.KanohiPedestalTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, BioniMine.MODID);

	public static final RegistryObject<TileEntityType<KanohiPedestalTileEntity>> KANOHI_PEDESTAL = TILE_ENTITY_TYPES
			.register("kanohi_pedestal", () -> TileEntityType.Builder
					.create(KanohiPedestalTileEntity::new, BlockInit.kanohi_pedestal.get()).build(null));
	
	public static final RegistryObject<TileEntityType<KanohiPedestalTileEntity>> TOA_CHEST = TILE_ENTITY_TYPES
			.register("toa_chest", () -> TileEntityType.Builder
					.create(KanohiPedestalTileEntity::new, BlockInit.toa_chest.get()).build(null));

}
