package com.github.gresh113.bionimine.entities.matoran;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.registration.ItemRegistration;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;

public class MatoranTrades extends VillagerTrades {
	   public static final Map<MatoranProfession, Int2ObjectMap<VillagerTrades.ITrade[]>> MATORAN_DEFAULT_TRADES = Util.make(Maps.newHashMap(), (map) -> {
		   //@formatter:off
		   map.put(MatoranProfession.NONE, gatAsIntMap(ImmutableMap.of(1, 
		    		  new VillagerTrades.ITrade[]{
		    				  new MatoranTrades.WidgetForItemsTrade(ItemRegistration.madu_fruit.get(), 8, 12, 10), 
		    				  new MatoranTrades.ItemsForWidgetsTrade(ItemRegistration.lightstone.get(), 8, 16, 2), 
		    				  new MatoranTrades.ItemsForWidgetsTrade(ItemRegistration.air_bladder.get(), 4, 16, 2), 
		    				  new MatoranTrades.ItemsForWidgetsTrade(ItemRegistration.bamboo_disk.get(), 4, 16, 2)}, 2, 
		    		  new VillagerTrades.ITrade[]{
		    				  new MatoranTrades.WidgetForItemsTrade(Blocks.PUMPKIN, 6, 12, 10), 
		    				  new MatoranTrades.ItemsForWidgetsTrade(Items.PUMPKIN_PIE, 1, 4, 5), 
		    				  new MatoranTrades.ItemsForWidgetsTrade(Items.APPLE, 1, 4, 16, 5)}, 3, 
		    		  new VillagerTrades.ITrade[]{
		    				  new MatoranTrades.ItemsForWidgetsTrade(Items.COOKIE, 3, 18, 10), 
		    				  new MatoranTrades.WidgetForItemsTrade(Blocks.MELON, 4, 12, 20)}, 4, 
		    		  	new VillagerTrades.ITrade[]{
		    				  new MatoranTrades.ItemsForWidgetsTrade(Items.GOLDEN_CARROT, 3, 3, 30), 
		    				  new MatoranTrades.ItemsForWidgetsTrade(Items.GLISTERING_MELON_SLICE, 4, 3, 30)})));
		 //@formatter:on
//		      map.put(VillagerProfession.ARMORER, gatAsIntMap(ImmutableMap.of(1, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_LEGGINGS), 7, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2F)}, 2, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2F)}, 3, 
//		    		  new VillagerTrades.ITrade[]{new MatoranTrades.EmeraldForItemsTrade(Items.LAVA_BUCKET, 1, 12, 20), 
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 20), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2F)}, 4, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2F), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2F)}, 5, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_HELMET, 8, 3, 30, 0.2F), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2F)})));
//		      map.put(VillagerProfession.WEAPONSMITH, gatAsIntMap(ImmutableMap.of(1, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.IRON_SWORD, 2, 3, 1)}, 2, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)}, 3, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.FLINT, 24, 12, 20)}, 4, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 30), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_AXE, 12, 3, 15, 0.2F)}, 5, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_SWORD, 8, 3, 30, 0.2F)})));
//		      map.put(VillagerProfession.TOOLSMITH, gatAsIntMap(ImmutableMap.of(1, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2F)}, 2, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)}, 3, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.FLINT, 30, 12, 20), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.IRON_AXE, 1, 3, 10, 0.2F), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.IRON_SHOVEL, 2, 3, 10, 0.2F), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.IRON_PICKAXE, 3, 3, 10, 0.2F), 
//		    				  new MatoranTrades.ItemsForEmeraldsTrade(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2F)}, 4, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 30), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_AXE, 12, 3, 15, 0.2F), 
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2F)}, 5, 
//		    		  new VillagerTrades.ITrade[]{
//		    				  new MatoranTrades.EnchantedItemForEmeraldsTrade(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2F)})));
		    });

		   private static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> trade) {
		      return new Int2ObjectOpenHashMap<>(trade);
		   }

		   static class WidgetForItemsTrade implements VillagerTrades.ITrade {
		      private final Item tradeItem;
		      private final int count;
		      private final int maxUses;
		      private final int xpValue;
		      private final float priceMultiplier;

		      public WidgetForItemsTrade(IItemProvider tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
		         this.tradeItem = tradeItemIn.asItem();
		         this.count = countIn;
		         this.maxUses = maxUsesIn;
		         this.xpValue = xpValueIn;
		         this.priceMultiplier = 0.05F;
		      }

		      public MerchantOffer getOffer(Entity trader, Random rand) {
		         ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
		         return new MerchantOffer(itemstack, new ItemStack(ItemRegistration.widgets.get()), this.maxUses, this.xpValue, this.priceMultiplier);
		      }
		   }

		   static class EnchantedItemForEmeraldsTrade implements VillagerTrades.ITrade {
		      private final ItemStack sellingStack;
		      private final int emeraldCount;
		      private final int maxUses;
		      private final int xpValue;
		      private final float priceMultiplier;

		      public EnchantedItemForEmeraldsTrade(Item itemIn, int emeraldCountIn, int maxUsesIn, int xpValuesIn) {
		         this(itemIn, emeraldCountIn, maxUsesIn, xpValuesIn, 0.05F);
		      }

		      public EnchantedItemForEmeraldsTrade(Item sellItem, int emeraldCountIn, int maxUsesIn, int xpValuesIn, float priceMultiplierIn) {
		         this.sellingStack = new ItemStack(sellItem);
		         this.emeraldCount = emeraldCountIn;
		         this.maxUses = maxUsesIn;
		         this.xpValue = xpValuesIn;
		         this.priceMultiplier = priceMultiplierIn;
		      }

		      public MerchantOffer getOffer(Entity trader, Random rand) {
		         int i = 5 + rand.nextInt(15);
		         ItemStack itemstack = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(this.sellingStack.getItem()), i, false);
		         int j = Math.min(this.emeraldCount + i, 64);
		         ItemStack itemstack1 = new ItemStack(Items.EMERALD, j);
		         return new MerchantOffer(itemstack1, itemstack, this.maxUses, this.xpValue, this.priceMultiplier);
		      }
		   }

		   static class ItemsForWidgetssAndItemsTrade implements VillagerTrades.ITrade {
		      private final ItemStack buyingItem;
		      private final int buyingItemCount;
		      private final int widgetCount;
		      private final ItemStack sellingItem;
		      private final int sellingItemCount;
		      private final int maxUses;
		      private final int xpValue;
		      private final float priceMultiplier;

		      public ItemsForWidgetssAndItemsTrade(IItemProvider p_i50533_1_, int p_i50533_2_, Item p_i50533_3_, int p_i50533_4_, int p_i50533_5_, int p_i50533_6_) {
		         this(p_i50533_1_, p_i50533_2_, 1, p_i50533_3_, p_i50533_4_, p_i50533_5_, p_i50533_6_);
		      }

		      public ItemsForWidgetssAndItemsTrade(IItemProvider p_i50534_1_, int p_i50534_2_, int widgetCountIn, Item p_i50534_4_, int p_i50534_5_, int p_i50534_6_, int p_i50534_7_) {
		         this.buyingItem = new ItemStack(p_i50534_1_);
		         this.buyingItemCount = p_i50534_2_;
		         this.widgetCount = widgetCountIn;
		         this.sellingItem = new ItemStack(p_i50534_4_);
		         this.sellingItemCount = p_i50534_5_;
		         this.maxUses = p_i50534_6_;
		         this.xpValue = p_i50534_7_;
		         this.priceMultiplier = 0.05F;
		      }

		      @Nullable
		      public MerchantOffer getOffer(Entity trader, Random rand) {
		         return new MerchantOffer(new ItemStack(ItemRegistration.widgets.get(), this.widgetCount), new ItemStack(this.buyingItem.getItem(), this.buyingItemCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
		      }
		   }

		   static class ItemsForWidgetsTrade implements VillagerTrades.ITrade {
		      private final ItemStack tradedItem;
		      private final int widgetCost;
		      private final int tradedItemCount;
		      private final int maxUses;
		      private final int givenXP;
		      private final float priceMultiplier;

		      public ItemsForWidgetsTrade(Block blockIn, int p_i50528_2_, int p_i50528_3_, int p_i50528_4_, int p_i50528_5_) {
		         this(new ItemStack(blockIn), p_i50528_2_, p_i50528_3_, p_i50528_4_, p_i50528_5_);
		      }

		      public ItemsForWidgetsTrade(Item itemIn, int costIn, int p_i50529_3_, int p_i50529_4_) {
		         this(new ItemStack(itemIn), costIn, p_i50529_3_, 12, p_i50529_4_);
		      }

		      public ItemsForWidgetsTrade(Item itemIn, int costIn, int p_i50530_3_, int p_i50530_4_, int p_i50530_5_) {
		         this(new ItemStack(itemIn), costIn, p_i50530_3_, p_i50530_4_, p_i50530_5_);
		      }

		      public ItemsForWidgetsTrade(ItemStack stackIn, int costIn, int p_i50531_3_, int p_i50531_4_, int p_i50531_5_) {
		         this(stackIn, costIn, p_i50531_3_, p_i50531_4_, p_i50531_5_, 0.05F);
		      }

		      public ItemsForWidgetsTrade(ItemStack tradedItemIn, int widgetCostIn, int tradedItemCountIn, int maxUsesIn, int givenXPIn, float priceMultiplierIn) {
		         this.tradedItem = tradedItemIn;
		         this.widgetCost = widgetCostIn;
		         this.tradedItemCount = tradedItemCountIn;
		         this.maxUses = maxUsesIn;
		         this.givenXP = givenXPIn;
		         this.priceMultiplier = priceMultiplierIn;
		      }

		      public MerchantOffer getOffer(Entity trader, Random rand) {
		         return new MerchantOffer(new ItemStack(ItemRegistration.widgets.get(), this.widgetCost), new ItemStack(this.tradedItem.getItem(), this.tradedItemCount), this.maxUses, this.givenXP, this.priceMultiplier);
		      }
		   }
}
