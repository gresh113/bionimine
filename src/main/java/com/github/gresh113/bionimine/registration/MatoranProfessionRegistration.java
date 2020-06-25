package com.github.gresh113.bionimine.registration;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.api.BionimineRegistries;
import com.github.gresh113.bionimine.entities.matoran.MatoranElement;
import com.github.gresh113.bionimine.entities.matoran.MatoranProfession;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class MatoranProfessionRegistration {
	public static final DeferredRegister<MatoranProfession> MATORAN_PROFESSIONS = new DeferredRegister<>(BionimineRegistries.MATORAN_PROFESSIONS, Bionimine.MODID);
	// public static final RegistryObject<MatoranElementRegistry> PO = MATORAN_ELEMENTS.register("po");

}
