package com.github.gresh113.bionimine.client.gui;

import java.util.List;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.init.KanohiInit;
import com.github.gresh113.bionimine.inventory.container.BionimineContainerTypes;
import com.github.gresh113.bionimine.kanohi.NewKanohiItem;

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
		
		//ItemInit.disk_launcher.addPropertyOverride(new ResourceLocation(BioniMine.MODID + "count"), new IItemPropertyGetter() {
		BioniMine.LOGGER.info("Client setup!");
		BioniMine.LOGGER.info("Array includes:");
		List<NewKanohiItem> array = KanohiInit.itemArray;
		for (NewKanohiItem index : array) {
			BioniMine.LOGGER.info("-" + index.getName().getFormattedText());
		}
//			
//			@Override
//			public float call(ItemStack stack, World worldIN, LivingEntity entityIn) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		});
	}

}
