package com.github.gresh113.bionimine.entities.matoran;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class MatoranProfession extends ForgeRegistryEntry<MatoranProfession>{
	public static final MatoranProfession NONE = new MatoranProfession("none", PointOfInterestType.UNEMPLOYED, ImmutableSet.of(), ImmutableSet.of(), (SoundEvent)null);
	private final String name;
	   private final PointOfInterestType pointOfInterest;
	   /** Defines items villagers of this profession can pick up and use. */
	   private final ImmutableSet<Item> specificItems;
	   /** World blocks this profession interracts with. */
	   private final ImmutableSet<Block> relatedWorldBlocks;
	   @Nullable
	   private final SoundEvent sound;

	   private MatoranProfession(String nameIn, PointOfInterestType pointOfInterestIn, ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, @Nullable SoundEvent soundIn) {
	      this.name = nameIn;
	      this.pointOfInterest = pointOfInterestIn;
	      this.specificItems = specificItemsIn;
	      this.relatedWorldBlocks = relatedWorldBlocksIn;
	      this.sound = soundIn;
	   }

	   public PointOfInterestType getPointOfInterest() {
	      return this.pointOfInterest;
	   }

	   /**
	    * @return A shared static immutable set of the specific items this profession can handle.
	    */
	   public ImmutableSet<Item> getSpecificItems() {
	      return this.specificItems;
	   }

	   /**
	    * @return A shared static immutable set of the world blocks this profession interracts with beside job site block.
	    */
	   public ImmutableSet<Block> getRelatedWorldBlocks() {
	      return this.relatedWorldBlocks;
	   }

	   @Nullable
	   public SoundEvent getSound() {
	      return this.sound;
	   }

	   public String toString() {
	      return this.name;
	   }

}
