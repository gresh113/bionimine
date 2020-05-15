package com.github.gresh113.bionimine.objects.blocks;

import com.github.gresh113.bionimine.state.properties.Mask_Type;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class BionimineBlockProperties extends BlockStateProperties{
	public static final EnumProperty<Mask_Type> MASK = EnumProperty.create("mask", Mask_Type.class);
}
