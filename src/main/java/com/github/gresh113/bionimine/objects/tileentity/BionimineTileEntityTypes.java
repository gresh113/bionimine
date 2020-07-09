package com.github.gresh113.bionimine.objects.tileentity;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.registration.BlockRegistration;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(
			ForgeRegistries.TILE_ENTITIES, Bionimine.MODID);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

//	public static final RegistryObject<TileEntityType<KanohiPedestalTileEntity>> KANOHI_PEDESTAL = TILE_ENTITY_TYPES
//			.register("kanohi_pedestal", () -> TileEntityType.Builder
//					.create(KanohiPedestalTileEntity::new, BlockInit.kanohi_pedestal.get()).build(null));
	public static final RegistryObject<TileEntityType<HauShieldTileEntity>> HAU_SHIELD = TILE_ENTITY_TYPES
			.register("hau_shield", () -> TileEntityType.Builder
					.create(HauShieldTileEntity::new, BlockRegistration.hau_shield.get()).build(null));

}
