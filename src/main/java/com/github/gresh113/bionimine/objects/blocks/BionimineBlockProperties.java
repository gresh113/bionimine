package com.github.gresh113.bionimine.objects.blocks;

import com.github.gresh113.bionimine.state.properties.MaskType;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class BionimineBlockProperties extends BlockStateProperties{
	public static final EnumProperty<MaskType> MASK = EnumProperty.create("mask", MaskType.class);
}
