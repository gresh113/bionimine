package com.github.gresh113.bionimine;

import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.network.BionimineCommands;
import com.github.gresh113.bionimine.network.BioniminePacketHandler;
import com.github.gresh113.bionimine.network.ToaEnergyMessage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.NetworkDirection;

@EventBusSubscriber(modid = Bionimine.MODID)
public class ForgeEventBusSub {

	static ResourceLocation loc = new ResourceLocation(Bionimine.MODID, "toaenergy");

	@SubscribeEvent
	public static void capabilityAttachement(AttachCapabilitiesEvent<Entity> event) {
		if ((event.getObject() instanceof PlayerEntity)) {
			event.addCapability(loc, new ToaEnergyProvider());
		}
	}
	


	@SubscribeEvent
	public static void onJoinWorld(EntityJoinWorldEvent event) {
		if (!event.isCanceled() && event.getEntity() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
			IToaEnergy capability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			ToaEnergyMessage kanohiMessage = new ToaEnergyMessage(capability.getKanohiEnergy(), capability.getElementalEnergy());
			BioniminePacketHandler.INSTANCE.sendTo(kanohiMessage, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	@SubscribeEvent
	public void serverLoad(FMLServerStartingEvent event) {
		BionimineCommands.register(event.getCommandDispatcher());
	}
	
	

}
