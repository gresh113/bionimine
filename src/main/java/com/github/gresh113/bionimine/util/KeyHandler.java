package com.github.gresh113.bionimine.util;

import org.lwjgl.glfw.GLFW;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.list.KeyBindingList;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyHandler {
	private static long window = Minecraft.getInstance().getMainWindow().getHandle();
	
	public static KeyBinding kanohiTrigger = new KeyBinding(Bionimine.MODID + ".key.kanohiTrigger", GLFW.GLFW_KEY_R, "BioniMine");
	
	
	public static void registerKeys()
	{
		ClientRegistry.registerKeyBinding(kanohiTrigger);
	}
	
	public static Boolean isHoldingShift()
	{
		
		return InputMappings.isKeyDown(window, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_SHIFT);
	}
}
