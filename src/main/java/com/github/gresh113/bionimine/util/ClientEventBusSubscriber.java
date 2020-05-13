package com.github.gresh113.bionimine.util;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.client.gui.KanohiPedestalScreen;
import com.github.gresh113.bionimine.inventory.container.BionimineContainerTypes;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(BionimineContainerTypes.PEDESTAL.get(), KanohiPedestalScreen::new);
	}

}
