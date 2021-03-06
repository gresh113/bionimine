package com.github.gresh113.bionimine.network;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BioniminePacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	private static int id = 0;
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Bionimine.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

	public static void init() {
		INSTANCE.registerMessage(id++, ToaEnergyMessage.class, ToaEnergyMessage::encode, ToaEnergyMessage::decode, ToaEnergyMessage::handle);
		INSTANCE.registerMessage(id++, AbilityGuiMessage.class, AbilityGuiMessage::toBytes, AbilityGuiMessage::new, AbilityGuiMessage::handle);
	}

}
