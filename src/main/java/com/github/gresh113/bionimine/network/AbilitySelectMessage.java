//package com.github.gresh113.bionimine.network;
//
//import java.util.function.Supplier;
//
//import com.github.gresh113.bionimine.Bionimine;
//import com.github.gresh113.bionimine.capabilities.IToaEnergy;
//import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.ByteBufUtil;
//import net.minecraft.client.Minecraft;
//import net.minecraft.network.PacketBuffer;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.fml.DistExecutor;
//import net.minecraftforge.fml.network.NetworkEvent;
//
//public class AbilitySelectMessage {
//
//		private boolean failed;
//
//		public AbilitySelectMessage() {
//			this.failed = false;
//		}
//
//		private AbilitySelectMessage(boolean failed) {
//			this.failed = failed;
//		}
//
//		public static ToaEnergyMessage decode(ByteBuf buf) {
//			try {
//				int k = buf.readInt();
//				int e = buf.readByte();
//				return new ToaEnergyMessage(k, e);
//			} catch (IndexOutOfBoundsException e) {
//				Bionimine.LOGGER.error("CountUpdateMessage: Unexpected end of packet.\nMessage: " + ByteBufUtil.hexDump(buf, 0, buf.writerIndex()), e);
//				return new ToaEnergyMessage(true);
//			}
//		}
//
//		public static void encode(ToaEnergyMessage msg, PacketBuffer buf) {
//			buf.writeInt(msg.kEnergy);
//			buf.writeByte(msg.eEnergy);
//		}
//
//		public static void handle(ToaEnergyMessage msg, Supplier<NetworkEvent.Context> ctx) {
//			DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> handleClient(msg, ctx.get()));
//			
//		}
//
//		@OnlyIn(Dist.CLIENT)
//		private static void handleClient(ToaEnergyMessage msg, NetworkEvent.Context ctx) {
//			if (!msg.failed) {
//				Minecraft mc = Minecraft.getInstance();
//				IToaEnergy capability = mc.player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
//				capability.setKanohiEnergy(msg.kEnergy);
//				capability.setElementalEnergy(msg.eEnergy);
//			}
//			ctx.setPacketHandled(true);
//		}
//
//	}
//
//
