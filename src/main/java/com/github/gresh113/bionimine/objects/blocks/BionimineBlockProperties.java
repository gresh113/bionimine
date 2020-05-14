package com.github.gresh113.bionimine.objects.blocks;

import com.github.gresh113.bionimine.state.properties.Mask;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class BionimineBlockProperties extends BlockStateProperties{
	public static final EnumProperty<Mask> MASK = EnumProperty.create("mask", Mask.class);
}
