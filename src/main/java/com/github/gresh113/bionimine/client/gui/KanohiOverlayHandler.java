package com.github.gresh113.bionimine.client.gui;

import com.github.gresh113.bionimine.client.overlay.ToaOverlay;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KanohiOverlayHandler {
	
	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Pre event)
    {
		ToaOverlay overlay = new ToaOverlay(Minecraft.getInstance());
		overlay.render(event);
    }

}
