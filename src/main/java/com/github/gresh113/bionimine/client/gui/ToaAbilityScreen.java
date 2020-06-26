//package com.github.gresh113.bionimine.client.gui;
//
//import com.github.gresh113.bionimine.Bionimine;
//import com.mojang.blaze3d.platform.GlStateManager;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.gui.widget.button.Button;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.StringTextComponent;
//
//public class ToaAbilityScreen extends Screen {
//	private static final int WIDTH = 154;
//	private static final int HEIGHT = 75;
//
//	private ResourceLocation GUI = new ResourceLocation(Bionimine.MODID, "textures/gui/ability_selection.png");
//
//	protected ToaAbilityScreen() {
//		super(new StringTextComponent("Select Ability:"));
//	}
//
//	@Override
//	protected void init() {
//		int relX = (this.width - WIDTH) / 2;
//		int relY = (this.height - HEIGHT) / 2;
//
//		addButton(new Button(relX + 10, relY + 10, 160, 20, "Skeleton", button -> select("minecraft:skeleton")));
//
////        addButton(new Button(relX + 10, relY + 10, 160, 20, "Skeleton", button -> spawn("minecraft:skeleton")));
////        addButton(new Button(relX + 10, relY + 37, 160, 20, "Zombie", button -> spawn("minecraft:zombie")));
////        addButton(new Button(relX + 10, relY + 64, 160, 20, "Cow", button -> spawn("minecraft:cow")));
////        addButton(new Button(relX + 10, relY + 91, 160, 20, "Sheep", button -> spawn("minecraft:sheep")));
////        addButton(new Button(relX + 10, relY + 118, 160, 20, "Chicken", button -> spawn("minecraft:chicken")));
//	}
//
//	private void select(String id) {
//		//BioniminePacketHandler.INSTANCE.sendToServer(new AbilitySelectMessage(id, minecraft.player.dimension, minecraft.player.getPosition()));
//		minecraft.displayGuiScreen(null);
//	}
//
//	@Override
//	public boolean isPauseScreen() {
//		return false;
//	}
//
//	@Override
//	public void render(int mouseX, int mouseY, float partialTicks) {
//		this.minecraft.getTextureManager().bindTexture(GUI);
//		int relX = (this.width - WIDTH) / 2;
//		int relY = (this.height - HEIGHT) / 2;
//		this.blit(relX, relY, 0, 0, WIDTH, HEIGHT);
//		super.render(mouseX, mouseY, partialTicks);
//	}
//
//	public static void open() {
//		Minecraft.getInstance().displayGuiScreen(new ToaAbilityScreen());
//	}
//
//}
