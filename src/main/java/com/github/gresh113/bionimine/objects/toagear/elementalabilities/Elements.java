package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nullable;

public enum Elements implements IStringSerializable {
    //@formatter:off
    NONE("none", 0, ToaAbilityHolder.NONE),
    FIRE("fire", 1, ToaAbilityHolder.FIRE),
    AIR("air", 2, ToaAbilityHolder.AIR),
    WATER("water", 3, ToaAbilityHolder.WATER),
    EARTH("earth", 4, ToaAbilityHolder.EARTH),
    STONE("stone", 5, ToaAbilityHolder.STONE),
    ICE("ice", 6, ToaAbilityHolder.ICE),

    LIGHT("light", 7, ToaAbilityHolder.NONE),
    SHADOW("shadow", 8, ToaAbilityHolder.NONE),

    SONICS("sonics", 9, ToaAbilityHolder.NONE),
    GRAVITY("gravity", 10, ToaAbilityHolder.NONE),
    PLASMA("plasma", 11, ToaAbilityHolder.NONE),
    MAGNETISM("magnetism", 12, ToaAbilityHolder.NONE),
    THE_GREEN("the_green", 13, ToaAbilityHolder.NONE),
    LIGHTNING("lightning", 14, ToaAbilityHolder.NONE),
    IRON("iron", 15, ToaAbilityHolder.NONE),
    PSIONICS("psionics", 16, ToaAbilityHolder.NONE);
    //@formatter:on

    private String name;
    private int id;
    private ToaAbilityHolder abilityHolder;

    Elements(String nameIn, int idIn, ToaAbilityHolder abilityHolderIn) {
        this.name = nameIn;
        this.id = idIn;
        this.abilityHolder = abilityHolderIn;
    }

//	@Override
//	public String getName() {
//		return name;
//	}

    public int getID() {
        return id;
    }

    public static Elements getElementFromID(int idIn) {
        for (Elements element : Elements.values()) {
            if (element.getID() == idIn)
                return element;
        }
        return Elements.NONE;
    }

    public static Elements getElementFromName(String nameIn) {
        for (Elements element : Elements.values()) {
            if (element.getString() == nameIn)
                return element;
        }
        return Elements.NONE;
    }

    public ToaAbilityHolder getAbilityHolder() {
        return abilityHolder;
    }

    public static Elements fromNBT(CompoundNBT compoundNBT, String tagname) {
        String elementname = "none";  // default in case of error
        if (compoundNBT != null && compoundNBT.contains(tagname)) {
            elementname = compoundNBT.getString(tagname);
        }
        Elements element = getElementFromName(elementname);
        return element;
    }

    @Override
    public String getString() {
        return name;
    }

    public static boolean isEarth(BlockState stateIn) {
        return (stateIn.getMaterial() == Material.EARTH || ElementalLists.EARTH_BLOCKS.contains(stateIn));
    }

    public static boolean isStone(BlockState stateIn) {
        return (stateIn.getMaterial() == Material.ROCK || ElementalLists.STONE_BLOCKS.contains(stateIn));
    }

    public static boolean isFire(BlockState stateIn) {
        return stateIn.getMaterial() == Material.FIRE || stateIn.getMaterial() == Material.LAVA;
    }

    public static boolean isWater(BlockState stateIn) {
        return stateIn.getMaterial() == Material.WATER;
    }

    public static boolean isIce(BlockState stateIn) {
        return stateIn.getMaterial() == Material.ICE
                || stateIn.getMaterial() == Material.PACKED_ICE
                || stateIn.getMaterial() == Material.SNOW
                || stateIn.getMaterial() == Material.SNOW_BLOCK;
    }

    public static boolean isAir(BlockState stateIn) {
        return stateIn.getMaterial() == Material.BUBBLE_COLUMN;
    }

    @Nullable
    public static ImmutableList<ItemStack> getItemsFromElement(Elements elementIn){
        switch (elementIn){
            case FIRE:
                return ElementalLists.TAHU_ITEMS;
            case AIR:
                return ElementalLists.LEWA_ITEMS;
            case WATER:
                return ElementalLists.GALI_ITEMS;
            case ICE:
                return ElementalLists.KOPAKA_ITEMS;
            case EARTH:
                return ElementalLists.ONUA_ITEMS;
            case STONE:
                return ElementalLists.POHATU_ITEMS;
            default:
                return null;
        }
    }



}
