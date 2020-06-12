package com.github.gresh113.bionimine.entities.telescope;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.entities.BionimineEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TelescopeEntity extends LivingEntity {

	public TelescopeEntity(EntityType<? extends TelescopeEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public TelescopeEntity(World worldIn, double posX, double posY, double posZ) {
		this(BionimineEntityTypes.TELESCOPE.get(), worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public void recalculateSize() {
		double d0 = this.getPosX();
		double d1 = this.getPosY();
		double d2 = this.getPosZ();
		super.recalculateSize();
		this.setPosition(d0, d1, d2);
	}

	public boolean hitByEntity(Entity entityIn) {
		return entityIn instanceof PlayerEntity
				&& !this.world.isBlockModifiable((PlayerEntity) entityIn, new BlockPos(this));
	}

	protected SoundEvent getFallSound(int heightIn) {
		return SoundEvents.ENTITY_ARMOR_STAND_FALL;
	}

	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ARMOR_STAND_HIT;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ARMOR_STAND_BREAK;
	}

	public boolean canBeHitWithPotion() {
		return false;
	}

	public boolean attackable() {
		return false;
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub

	}

	@Override
 public void writeAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HandSide getPrimaryHand() {
		// TODO Auto-generated method stub
		return null;
	}
}
