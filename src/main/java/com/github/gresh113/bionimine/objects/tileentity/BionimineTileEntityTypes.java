package com.github.gresh113.bionimine.objects.tileentity;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.init.BlockInit;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, Bionimine.MODID);

	public static final RegistryObject<TileEntityType<KanohiPedestalTileEntity>> KANOHI_PEDESTAL = TILE_ENTITY_TYPES
			.register("kanohi_pedestal", () -> TileEntityType.Builder
					.create(KanohiPedestalTileEntity::new, BlockInit.kanohi_pedestal.get()).build(null));

}
