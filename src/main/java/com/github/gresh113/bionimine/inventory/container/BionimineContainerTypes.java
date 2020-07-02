package com.github.gresh113.bionimine.inventory.container;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BionimineContainerTypes {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Bionimine.MODID);
	
	//public static final RegistryObject<ContainerType<KanohiPedestalContainer>> PEDESTAL = CONTAINER_TYPES
	//		.register("kanohi_pedestal", () -> IForgeContainerType.create(KanohiPedestalContainer::new));

}
