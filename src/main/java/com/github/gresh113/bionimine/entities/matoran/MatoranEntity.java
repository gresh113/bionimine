package com.github.gresh113.bionimine.entities.matoran;

import com.github.gresh113.bionimine.init.KanohiInit;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

//
public class MatoranEntity extends CreatureEntity {
	private boolean canPickUpLoot = true;
	private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(MatoranEntity.class,
			DataSerializers.STRING);
	private static final DataParameter<Boolean> HAS_TRADES = EntityDataManager.createKey(MatoranEntity.class,
			DataSerializers.BOOLEAN);
	

	public MatoranEntity(EntityType<? extends MatoranEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, .5D));
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.23F);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(TEXTURE, (String) "");
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putString("Texture", (String) this.getTexture());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		String texture = compound.getString("Texture");
		if (texture.isEmpty()) {
			texture = "matoran";
		}
		this.setTexture(texture);
	}

	public String getTexture() {
		String textureOut;	
		if (this.dataManager.get(TEXTURE).isEmpty()){
			textureOut = "matoran";
		}
		else {textureOut = this.dataManager.get(TEXTURE);}	
		return textureOut;
	}

	public void setTexture(String stringIn) {
		//String s = this.dataManager.get(TEXTURE);
		this.dataManager.set(TEXTURE, (String) stringIn);
	}

	@Override
	protected boolean canEquipItem(ItemStack stack) {
		return super.canEquipItem(stack);
	}

	@Override
	public boolean canPickUpLoot() {
		return this.canPickUpLoot;
	}

	@Override
	public void setCanPickUpLoot(boolean canPickup) {
		this.canPickUpLoot = canPickup;
	}

	@Override
	public boolean canPickUpItem(ItemStack itemstackIn) {
		EquipmentSlotType equipmentslottype = getSlotForItemStack(itemstackIn);
		return this.getItemStackFromSlot(equipmentslottype).isEmpty() && this.canPickUpLoot();
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		//this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(KanohiInit.PowerlessMaskItem));
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if (!this.world.isRemote) {
			player.sendStatusMessage(
					new StringTextComponent(this.getItemStackFromSlot(EquipmentSlotType.HEAD).toString()), true);
		}
		return super.processInteract(player, hand);
	}

}
