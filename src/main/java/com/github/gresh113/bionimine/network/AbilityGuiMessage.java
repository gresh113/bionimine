package com.github.gresh113.bionimine.network;

import java.util.function.Supplier;

//import com.github.gresh113.bionimine.client.gui.ToaAbilityScreen;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class AbilityGuiMessage {
	
	public AbilityGuiMessage(PacketBuffer buf) {
		
	}
	
    public void toBytes(PacketBuffer buf) {
    }

    public AbilityGuiMessage() {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        //ctx.get().enqueueWork(ToaAbilityScreen::open);
        ctx.get().setPacketHandled(true);
    }

}
