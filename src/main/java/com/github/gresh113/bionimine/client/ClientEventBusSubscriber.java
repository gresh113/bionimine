package com.github.gresh113.bionimine.client;

import java.util.List;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiColorHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiInit;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiItem;
import com.github.gresh113.bionimine.registration.BlockRegistration;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		//ScreenManager.registerFactory(BionimineContainerTypes.PEDESTAL.get(), KanohiPedestalScreen::new);
		RenderTypeLookup.setRenderLayer(BlockRegistration.hau_shield.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegistration.frost.get(), RenderType.getTranslucent());
	}
		
	@SubscribeEvent
	public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
		List<KanohiItem> array = KanohiInit.itemArray;
		for (KanohiItem index : array) {
			event.getItemColors().register(new KanohiColorHandler(), index);
		}
	}
	


}
