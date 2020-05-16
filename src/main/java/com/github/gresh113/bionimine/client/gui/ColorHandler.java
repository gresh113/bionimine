package com.github.gresh113.bionimine.client.gui;

import com.github.gresh113.bionimine.BioniMine;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {
	@SubscribeEvent
	public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
		// the LiquidColour lambda function is used to change the rendering colour of
		// the liquid in the bottle
		// i.e.: when vanilla wants to know what colour to render our itemVariants
		// instance, it calls the LiquidColour lambda function

		// event.getItemColors().register(new KanohiColor(),
		// StartupCommon.itemVariants);
	}
}
