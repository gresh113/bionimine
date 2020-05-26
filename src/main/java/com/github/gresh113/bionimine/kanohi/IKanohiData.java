package com.github.gresh113.bionimine.kanohi;

import net.minecraft.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IKanohiData {
	public KanohiPower getAbility();
	public KanohiType getType();
	//public Element getElement(NewKanohiItem kanohiIn);
	public DyeColor getColor();
	@OnlyIn(Dist.CLIENT)
	public String getName();

}
