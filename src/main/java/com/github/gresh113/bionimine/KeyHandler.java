package com.github.gresh113.bionimine;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyHandler {
	public static KeyBinding kanohiTrigger = new KeyBinding(BioniMine.MODID + ".key.kanohiTrigger", GLFW.GLFW_KEY_G, "BioniMine");
	
	public static void registerKeys()
	{
		ClientRegistry.registerKeyBinding(kanohiTrigger);
	}
}
