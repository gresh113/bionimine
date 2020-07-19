package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.objects.toagear.kanohi.Kanohi;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiInit;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiItem;
import com.github.gresh113.bionimine.registration.BlockRegistration;
import com.github.gresh113.bionimine.registration.ItemRegistration;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElementalLists {
    public static ImmutableList<BlockState> EARTH_BLOCKS = ImmutableList.of( //@formatter:off
            Blocks.DIRT.getDefaultState(),
            Blocks.COARSE_DIRT.getDefaultState(),
            Blocks.MYCELIUM.getDefaultState(),
            Blocks.PODZOL.getDefaultState(),
            Blocks.GRASS_BLOCK.getDefaultState(),
            Blocks.GRASS_PATH.getDefaultState(),
            Blocks.CLAY.getDefaultState(),
            Blocks.BRICKS.getDefaultState()); //@formatter:on
    public static ImmutableList<BlockState> STONE_BLOCKS = ImmutableList.of( //@formatter:off
            Blocks.STONE.getDefaultState()
            , Blocks.COBBLESTONE.getDefaultState()
            , Blocks.MOSSY_COBBLESTONE.getDefaultState()
            , Blocks.SMOOTH_STONE.getDefaultState()
            , Blocks.STONE_BRICKS.getDefaultState()
            , Blocks.MOSSY_STONE_BRICKS.getDefaultState()

            , Blocks.SAND.getDefaultState()
            , Blocks.SANDSTONE.getDefaultState()
            , Blocks.SMOOTH_SANDSTONE.getDefaultState()
            , Blocks.CUT_SANDSTONE.getDefaultState()
            , Blocks.CHISELED_SANDSTONE.getDefaultState()

            , Blocks.RED_SAND.getDefaultState()
            , Blocks.RED_SANDSTONE.getDefaultState()
            , Blocks.SMOOTH_RED_SANDSTONE.getDefaultState()
            , Blocks.CHISELED_RED_SANDSTONE.getDefaultState()
            , Blocks.CUT_RED_SANDSTONE.getDefaultState()

            , Blocks.GRAVEL.getDefaultState()

            , Blocks.ANDESITE.getDefaultState()
            , Blocks.GRANITE.getDefaultState()
            , Blocks.DIORITE.getDefaultState()
            , Blocks.POLISHED_ANDESITE.getDefaultState()
            , Blocks.POLISHED_GRANITE.getDefaultState()
            , Blocks.POLISHED_DIORITE.getDefaultState()

            , Blocks.BLACKSTONE.getDefaultState()
            , Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState()
            , Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()
            , Blocks.POLISHED_BLACKSTONE.getDefaultState()
    ); //@formatter:on


    public static ImmutableList<ItemStack> TAHU_ITEMS = ImmutableList.of(

            new ItemStack(ItemRegistration.toa_armor_fire.get(),1),
            new ItemStack(ItemRegistration.fire_sword.get(),1),
            new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Bionimine.MODID, "great_hau")))
    );
    public static ImmutableList<ItemStack> GALI_ITEMS = ImmutableList.of(
            new ItemStack(ItemRegistration.toa_armor_water.get(),1),
            new ItemStack(ItemRegistration.water_hook.get(),1),
            new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Bionimine.MODID, "great_kaukau")),1)
    );
    public static ImmutableList<ItemStack> LEWA_ITEMS = ImmutableList.of(
            new ItemStack(ItemRegistration.toa_armor_air.get(),1),
            new ItemStack(ItemRegistration.air_axe.get(),1),
            new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Bionimine.MODID, "great_miru")),1)
    );
    public static ImmutableList<ItemStack> KOPAKA_ITEMS = ImmutableList.of(
            new ItemStack(ItemRegistration.toa_armor_ice.get(),1),
            new ItemStack(ItemRegistration.ice_sword.get(),1),
            new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Bionimine.MODID, "great_akaku")),1)
    );
    public static ImmutableList<ItemStack> POHATU_ITEMS = ImmutableList.of(
            new ItemStack(ItemRegistration.toa_armor_stone.get(),1),
            new ItemStack(ItemRegistration.stone_blaster.get(),1),
            new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Bionimine.MODID, "great_kakama")),1)
    );
    public static ImmutableList<ItemStack> ONUA_ITEMS = ImmutableList.of(
            new ItemStack(ItemRegistration.toa_armor_earth.get(),1),
            new ItemStack(ItemRegistration.earth_claw.get(),1),
            new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Bionimine.MODID, "great_pakari")))
    );

}
