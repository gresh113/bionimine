package com.github.gresh113.bionimine.inventory.container;

import com.github.gresh113.bionimine.BioniMine;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineContainerTypes {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
			ForgeRegistries.CONTAINERS, BioniMine.MODID);
	
	public static final RegistryObject<ContainerType<KanohiPedestalContainer>> PEDESTAL = CONTAINER_TYPES
			.register("kanohi_pedestal", () -> IForgeContainerType.create(KanohiPedestalContainer::new));
	public static final RegistryObject<ContainerType<ToaChestContainer>> TOA_CHEST = CONTAINER_TYPES
			.register("toa_chest", () -> IForgeContainerType.create(ToaChestContainer::new));

}
