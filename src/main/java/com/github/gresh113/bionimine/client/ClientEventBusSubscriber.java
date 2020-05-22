package com.github.gresh113.bionimine.client;

import java.util.List;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.client.gui.KanohiPedestalScreen;
import com.github.gresh113.bionimine.entities.fikou_spider.FikouSpiderRender;
import com.github.gresh113.bionimine.init.BionimineEntityTypes;
import com.github.gresh113.bionimine.init.KanohiInit;
import com.github.gresh113.bionimine.inventory.container.BionimineContainerTypes;
import com.github.gresh113.bionimine.kanohi.KanohiColorHandler;
import com.github.gresh113.bionimine.kanohi.KanohiItem;
import com.github.gresh113.bionimine.kanohi.KanohiShape;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(BionimineContainerTypes.PEDESTAL.get(), KanohiPedestalScreen::new);
		RenderingRegistry.registerEntityRenderingHandler(BionimineEntityTypes.FIKOU_SPIDER.get(), FikouSpiderRender::new);
		//for (KanohiShape shape : KanohiShape.values()) {BioniMine.LOGGER.info("{|predicate|: {|bionimine:shape|: " + shape.getPredicate() + "}, |model|: |bionimine:item/masks/"+shape.getName()+"|},");}
	}
	
	@SubscribeEvent
	public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
		List<KanohiItem> array = KanohiInit.itemArray;
		for (KanohiItem index : array) {
			event.getItemColors().register(new KanohiColorHandler(), index);
		}
	}

}
