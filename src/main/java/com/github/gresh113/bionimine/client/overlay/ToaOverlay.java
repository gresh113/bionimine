package com.github.gresh113.bionimine.client.overlay;

import java.awt.Color;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.toa_gear.ToaArmorItem;
import com.github.gresh113.bionimine.toa_gear.kanohi.ArmorPalette;
import com.github.gresh113.bionimine.toa_gear.kanohi.KanohiItem;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;

@OnlyIn(Dist.CLIENT)
public class ToaOverlay extends ForgeIngameGui {
	private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/gui/overlay/kanohioverlay.png");
	protected int scaledWidth;
	protected int scaledHeight;
	private RenderGameOverlayEvent eventParent;

	public ToaOverlay(Minecraft clientIn) {
		super(clientIn);
	}

	private void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}

	public void render(RenderGameOverlayEvent.Pre event) {
		this.scaledWidth = this.mc.getMainWindow().getScaledWidth();
		this.scaledHeight = this.mc.getMainWindow().getScaledHeight();
		eventParent = new RenderGameOverlayEvent(this.mc.getRenderPartialTicks(), this.mc.getMainWindow());

		if (event.getType() == ElementType.HOTBAR) {
			return;
		}

		PlayerEntity player = mc.player;
		IToaEnergy playerCapability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		Item headItem = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
		Item chestItem = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
		boolean kanohiFlag = false;
		boolean armorFlag = false;
		int kanohiLevel = 0;
		int kanohiEnergy = 0;
		byte elementEnergy = 0;
		ArmorPalette kanohiPalette = ArmorPalette.GRAY;
		ArmorPalette armorPalette = ArmorPalette.GRAY;

		// ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		// KanohiShape shape = kanohiItem.getShape(stack);
		// ArmorPalette palette = kanohiItem.getPalette(stack);
		if (headItem instanceof KanohiItem) {
			KanohiItem kanohiItem = (KanohiItem) headItem;
			kanohiFlag = true;
			kanohiLevel = kanohiItem.getPowerLevel();
			kanohiEnergy = playerCapability.getKanohiEnergy();
			kanohiPalette = kanohiItem.getPalette(player.getItemStackFromSlot(EquipmentSlotType.HEAD));
		}

		if (chestItem instanceof ToaArmorItem) {
			ToaArmorItem armorItem = (ToaArmorItem) chestItem;
			armorFlag = true;
			elementEnergy = playerCapability.getElementalEnergy();
			armorPalette = armorItem.getPalette(player.getItemStackFromSlot(EquipmentSlotType.CHEST));
		}

		if (kanohiFlag || armorFlag) {
			if (!event.isCanceled()) {
				if (pre(ElementType.HOTBAR))
					return;
				bind(OVERLAY_TEXTURE);
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.disableBlend();
				this.blit(6, 19, 0, 15, 6, 182);
				RenderSystem.enableBlend();
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
				if (kanohiFlag) {
					renderKanohiBar(kanohiEnergy, kanohiLevel, kanohiPalette);
				}
				if (armorFlag) {
					renderElementBar(elementEnergy, armorPalette);
				}
				pre(ElementType.HOTBAR);
			}
		}
	}

	// Blit functions (from Mekanism?)
	// blit(int x, int y, TextureAtlasSprite icon, int width, int height);
	// blit(int x, int y, int textureX, int textureY, int width, int height, int
	// textureWidth, int textureHeight);
	// blit(int x, int y, int zLevel, float textureX, float textureY, int width, int
	// height, int textureWidth, int textureHeight);
	// blit(int x, int y, int desiredWidth, int desiredHeight, int textureX, int
	// textureY, int width, int height, int textureWidth, int textureHeight);
	// innerBlit(int x, int endX, int y, int endY, int zLevel, int width, int
	// height, float textureX, float textureY, int textureWidth, int textureHeight);

	// blit(int x, int y, int textureX, int textureY, int width, int height);
	protected void renderKanohiBar(int energyIn, int kanohiLevel, ArmorPalette kanohiPalette) {
		bind(OVERLAY_TEXTURE);
		this.mc.getProfiler().startSection("kanohiBar");
		Color color = kanohiPalette.getColorLayer2();
		RenderSystem.color4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		RenderSystem.enableBlend();

		// int j = 182;
		int length = (int) (energyIn * 0.01525F);
		int l = 19;
		this.blit(3, (l - 2 - 11), kanohiLevel * 11, 0, 11, 11);
		if (length > 0) {
			this.blit(6, l, 6, 15, 3, length);
		}
		RenderSystem.enableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getProfiler().endSection();
	}

	protected void renderElementBar(byte energyIn, ArmorPalette armorPalette) {
		bind(OVERLAY_TEXTURE);
		this.mc.getProfiler().startSection("elementBar");
		Color color = armorPalette.getColorLayer2();
		RenderSystem.color4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		RenderSystem.enableBlend();
		// int j = 182;
		int length = (int) (energyIn * 1.46F);
		int l = 19;
		if (length > 0) {
			this.blit(9, l, 10, 15, 3, length);
		}
		RenderSystem.enableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getProfiler().endSection();
	}

	private boolean pre(ElementType type) {
		return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(eventParent, type));
	}

}
