package com.github.gresh113.bionimine.toagear.kanohi;

import java.awt.Color;

import com.github.gresh113.bionimine.toagear.ArmorPalette;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class KanohiColorHandler implements IItemColor {

	/**
	 * Returns the color for rendering, based on 1) the itemstack 2) the "tintindex"
	 * (layer in the item model json) For example: bottle_drinkable.json contains
	 * "layer0": "item/potion_overlay", "layer1": "item/potion_bottle_drinkable"
	 * layer0 = tintindex 0 = for the bottle outline, whose color doesn't change
	 * layer1 = tintindex 1 = for the bottle contents, whose color changes depending
	 * on the type of potion
	 * 
	 * @param stack
	 * @param tintIndex
	 * @return an RGB color (to be multiplied by the texture colors)
	 */
	@Override
	public int getColor(ItemStack stack, int tintIndex) {
		{
			ArmorPalette kanohiColor = KanohiItem.getPalette(stack);
			
			switch (tintIndex) {
			case 0:
				return kanohiColor.getColorLayer1().getRGB();
			case 1: {
				return kanohiColor.getColorLayer2().getRGB();
			}
			case 2: {
				return kanohiColor.getColorLayer3().getRGB();
			}
			case 3: {
				return kanohiColor.getColorLayer4().getRGB();
			}
			case 4: {
				return kanohiColor.getColorLayer5().getRGB();
			}
			default: {
				return Color.WHITE.getRGB();
			}
			}
		}
	}
}