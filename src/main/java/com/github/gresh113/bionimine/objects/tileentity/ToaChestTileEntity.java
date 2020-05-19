package com.github.gresh113.bionimine.objects.tileentity;


import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.inventory.container.ToaChestContainer;
import com.github.gresh113.bionimine.objects.blocks.ToaChestBlock;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ToaChestTileEntity extends LockableLootTileEntity {
   private NonNullList<ItemStack> chestContents = NonNullList.withSize(6, ItemStack.EMPTY);
   private int numPlayersUsing;

   private ToaChestTileEntity(TileEntityType<?> typeIn) {
      super(typeIn);
   }

   public ToaChestTileEntity() {
      this(BionimineTileEntityTypes.TOA_CHEST.get());
   }

   public CompoundNBT write(CompoundNBT compound) {
      super.write(compound);
      if (!this.checkLootAndWrite(compound)) {
         ItemStackHelper.saveAllItems(compound, this.chestContents);
      }

      return compound;
   }

   public void read(CompoundNBT compound) {
      super.read(compound);
      this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
      if (!this.checkLootAndRead(compound)) {
         ItemStackHelper.loadAllItems(compound, this.chestContents);
      }

   }

   /**
    * Returns the number of slots in the inventory.
    */
   public int getSizeInventory() {
      return 6;
   }

   protected NonNullList<ItemStack> getItems() {
      return this.chestContents;
   }

   protected void setItems(NonNullList<ItemStack> itemsIn) {
      this.chestContents = itemsIn;
   }

   protected ITextComponent getDefaultName() {
      return new TranslationTextComponent("container.toa_chest");
   }

   protected Container createMenu(int id, PlayerInventory player) {
	   return new ToaChestContainer(id, player, this);
   }

   public void openInventory(PlayerEntity player) {
      if (!player.isSpectator()) {
         if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
         }

         ++this.numPlayersUsing;
         BlockState blockstate = this.getBlockState();
         boolean flag = blockstate.get(ToaChestBlock.PROPERTY_OPEN);
         if (!flag) {
            this.playSound(blockstate, SoundEvents.BLOCK_BARREL_OPEN);
            this.setOpenProperty(blockstate, true);
         }

         this.scheduleTick();
      }

   }

   private void scheduleTick() {
      this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
   }

   public void barrelTick() {
      int i = this.pos.getX();
      int j = this.pos.getY();
      int k = this.pos.getZ();
      this.numPlayersUsing = ChestTileEntity.calculatePlayersUsing(this.world, this, i, j, k);
      if (this.numPlayersUsing > 0) {
         this.scheduleTick();
      } else {
         BlockState blockstate = this.getBlockState();
         if (blockstate.getBlock() != BlockInit.toa_chest.get()) {
            this.remove();
            return;
         }

         boolean flag = blockstate.get(ToaChestBlock.PROPERTY_OPEN);
         if (flag) {
            this.playSound(blockstate, SoundEvents.BLOCK_BARREL_CLOSE);
            this.setOpenProperty(blockstate, false);
         }
      }

   }

   public void closeInventory(PlayerEntity player) {
      if (!player.isSpectator()) {
         --this.numPlayersUsing;
      }

   }

   private void setOpenProperty(BlockState state, boolean p_213963_2_) {
      this.world.setBlockState(this.getPos(), state.with(ToaChestBlock.PROPERTY_OPEN, Boolean.valueOf(p_213963_2_)), 3);
   }

   private void playSound(BlockState p_213965_1_, SoundEvent p_213965_2_) {
      Vec3i vec3i = p_213965_1_.get(ToaChestBlock.PROPERTY_FACING).getDirectionVec();
      double d0 = (double)this.pos.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
      double d1 = (double)this.pos.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
      double d2 = (double)this.pos.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
      this.world.playSound((PlayerEntity)null, d0, d1, d2, p_213965_2_, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
   }
}
