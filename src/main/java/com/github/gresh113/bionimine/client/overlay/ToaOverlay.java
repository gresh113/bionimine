package com.github.gresh113.bionimine.client.overlay;

import java.awt.Color;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.objects.toagear.ArmorPalette;
import com.github.gresh113.bionimine.objects.toagear.ToaArmorItem;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiItem;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
		if (player.isAlive()) {
			IToaEnergy playerCapability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			Item headItem = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
			Item chestItem = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
			boolean kanohiFlag = false;
			boolean armorFlag = false;
			int kanohiLevel = 0;
			int kanohiEnergy = 0;
			int elementEnergy = 0;
			ItemStack kanohiStack = ItemStack.EMPTY;
			ArmorPalette kanohiPalette = ArmorPalette.GRAY;
			ArmorPalette armorPalette = ArmorPalette.GRAY;

			// ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
			// KanohiShape shape = kanohiItem.getShape(stack);
			// ArmorPalette palette = kanohiItem.getPalette(stack);
			if (headItem instanceof KanohiItem) {
				KanohiItem kanohiItem = (KanohiItem) headItem;
				// kanohiStack = new ItemStack(kanohiItem);
				kanohiStack = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
				kanohiFlag = true;
				kanohiLevel = kanohiItem.getPowerLevel();
				kanohiEnergy = playerCapability.getKanohiEnergy();
				kanohiPalette = KanohiItem.getPalette(player.getItemStackFromSlot(EquipmentSlotType.HEAD));
			}

			if (chestItem instanceof ToaArmorItem) {
				// ToaArmorItem armorItem = (ToaArmorItem) chestItem;
				armorFlag = true;
				elementEnergy = playerCapability.getElementalEnergy();
				armorPalette = ToaArmorItem.getPalette(player.getItemStackFromSlot(EquipmentSlotType.CHEST));
			}

			if (kanohiFlag || armorFlag) {
				if (!event.isCanceled()) {
					if (pre(ElementType.HOTBAR))
						return;
					bind(OVERLAY_TEXTURE);
					RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
					RenderSystem.disableBlend();
					this.blit(4, (scaledHeight / 2) - 13, 17, 75, 28, 26);
					this.blit(14, (scaledHeight / 2) - 73, 49, 15, 6, 146);
					RenderSystem.enableBlend();
					RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
					if (kanohiFlag) {
						renderKanohiBar(kanohiEnergy, kanohiLevel, kanohiPalette, kanohiStack);
					}
					if (armorFlag) {
						renderElementBar(elementEnergy, armorPalette);
					}

					bind(GUI_ICONS_LOCATION);
					pre(ElementType.HOTBAR);
				}
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
	protected void renderKanohiBar(int energyIn, int kanohiLevel, ArmorPalette kanohiPalette, ItemStack kanohiStackIn) {
		bind(OVERLAY_TEXTURE);
		this.mc.getProfiler().startSection("kanohiBar");
		Color color = kanohiPalette.getColorLayer2();
		RenderSystem.color4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		RenderSystem.enableBlend();

		Float barLength = 60F;
		int max = ToaEnergy.maxKanohiEnergy;
		int length = (int) (energyIn * (barLength / max));
		// int l = 19;
		if (!((kanohiStackIn == ItemStack.EMPTY) || (kanohiStackIn == null))) {
			mc.getItemRenderer().renderItemIntoGUI(kanohiStackIn, 9, (scaledHeight / 2) - 8);
			bind(OVERLAY_TEXTURE);
		}
		if (kanohiLevel == 1) {
			this.blit(25, (scaledHeight / 2) - 7, 45, 87, 4, 2);
		} else if (kanohiLevel == 2) {
			this.blit(25, (scaledHeight / 2) - 7, 45, 87, 4, 2);
			this.blit(27, (scaledHeight / 2) - 1, 45, 87, 4, 2);
		} else if (kanohiLevel == 3) {
			this.blit(25, (scaledHeight / 2) - 7, 45, 87, 4, 2);
			this.blit(27, (scaledHeight / 2) - 1, 45, 87, 4, 2);
			this.blit(25, (scaledHeight / 2) + 5, 45, 87, 4, 2);
		}

		if (length > 0) {
			this.blit(14, (int) (((scaledHeight / 2) - 13) - length), 27, (int) (15 + (barLength - length)), 6, length);
			// this.blit(6, l, 6, 15, 3, length);
		}
		RenderSystem.enableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getProfiler().endSection();
	}

	protected void renderElementBar(int energyIn, ArmorPalette armorPalette) {
		bind(OVERLAY_TEXTURE);
		this.mc.getProfiler().startSection("elementBar");
		Color color = armorPalette.getColorLayer2();
		RenderSystem.color4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		RenderSystem.enableBlend();
		Float barLength = 60F;
		int max = ToaEnergy.maxElementalEnergy;
		int length = (int) (energyIn * (barLength / max));
		// int l = 19;
		if (length > 0) {
			this.blit(14, (scaledHeight / 2) + 13, 27, 101, 6, length);
			// this.blit(9, l, 10, 15, 3, length);
		}
		RenderSystem.enableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getProfiler().endSection();
	}

	private boolean pre(ElementType type) {
		return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(eventParent, type));
	}

}
