package com.github.gresh113.bionimine.registration;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.effects.MaduCaboloEffect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionRegistration {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Bionimine.MODID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Bionimine.MODID);
	private static final NonNullLazy<Effect> myEffectInitializer = NonNullLazy.of(() -> new MaduCaboloEffect());
	public static final RegistryObject<Effect> MADU = EFFECTS.register("madu", () -> myEffectInitializer.get());
	public static final RegistryObject<Potion> MADU_CABOLO = POTIONS.register("name", () -> new Potion("madu_cabolo", new EffectInstance(myEffectInitializer.get())));
	
}
