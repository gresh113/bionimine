package com.github.gresh113.bionimine.entities.matoran;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.entities.matoran.ai.IMatoranReputationType;
import com.github.gresh113.bionimine.init.ItemInit;
import com.github.gresh113.bionimine.init.MatoranRegistration;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.IReputationTracking;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//
public class MatoranEntity extends AbstractVillagerEntity implements IMatoranDataHolder {

	// public static final ResourceLocation TALKED_TO_MATORAN =
	// Stats.registerCustom("talked_to_villager", IStatFormatter.DEFAULT);

	private static final DataParameter<MatoranData> MATORAN_DATA = EntityDataManager.createKey(MatoranEntity.class, MatoranData.DATA);

	//@formatter:off
	private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(MatoranEntity.class, DataSerializers.STRING);
	private static final DataParameter<Boolean> HAS_TRADES = EntityDataManager.createKey(MatoranEntity.class, DataSerializers.BOOLEAN);
	private static final Set<Item> ALLOWED_INVENTORY_ITEMS = ImmutableSet.of(
			ItemInit.bamboo_disk.get(),
			ItemInit.heatstone.get(),
			ItemInit.flag.get(),
			ItemInit.air_bladder.get(),
			ItemInit.widgets.get(),
			ItemInit.madu_fruit.get()
			);
	private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
			MemoryModuleType.HOME, 
			MemoryModuleType.JOB_SITE, 
			MemoryModuleType.MEETING_POINT, 
			MemoryModuleType.MOBS, 
			MemoryModuleType.VISIBLE_MOBS, 
			MemoryModuleType.NEAREST_PLAYERS, 
			MemoryModuleType.NEAREST_VISIBLE_PLAYER, 
			MemoryModuleType.WALK_TARGET, 
			MemoryModuleType.LOOK_TARGET, 
			MemoryModuleType.INTERACTION_TARGET, 
			MemoryModuleType.PATH, 
			MemoryModuleType.INTERACTABLE_DOORS, 
			MemoryModuleType.field_225462_q, //Opened Doors
			MemoryModuleType.NEAREST_BED, 
			MemoryModuleType.HURT_BY, 
			MemoryModuleType.HURT_BY_ENTITY, 
			MemoryModuleType.NEAREST_HOSTILE, 
			MemoryModuleType.SECONDARY_JOB_SITE, 
			MemoryModuleType.HIDING_PLACE, 
			MemoryModuleType.HEARD_BELL_TIME, 
			MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE
			);
	
	private static final ImmutableList<SensorType<? extends Sensor<? super MatoranEntity>>> SENSOR_TYPES = ImmutableList.of(
			SensorType.NEAREST_LIVING_ENTITIES, 
			SensorType.NEAREST_PLAYERS, 
			SensorType.INTERACTABLE_DOORS,
			SensorType.HURT_BY 
			//SensorType.VILLAGER_HOSTILES, //TODO - This will need to be updated to MATORAN_HOSTILES
			//SensorType.SECONDARY_POIS
			);   
	 //@formatter:on
	private boolean canPickUpLoot = true;

	public MatoranEntity(EntityType<? extends MatoranEntity> type, World worldIn) {
		this(type, worldIn, MatoranRegistration.TA.get());
	}

	public MatoranEntity(EntityType<? extends MatoranEntity> type, World worldIn, MatoranElement element) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 0.0F);
		((GroundPathNavigator) this.getNavigator()).setBreakDoors(true);
		this.getNavigator().setCanSwim(true);
		this.setCanPickUpLoot(true);
		this.setMatoranData(this.getMatoranData().withElement(element).withProfession(MatoranProfession.NONE));
	}

	public Brain<MatoranEntity> getBrain() {
		return (Brain<MatoranEntity>) super.getBrain();
	}

	protected Brain<?> createBrain(Dynamic<?> dynamicIn) {
		Brain<MatoranEntity> brain = Brain.func_233705_a_(MEMORY_TYPES, SENSOR_TYPES).func_233748_a_(dynamicIn);
		this.initBrain(brain);
		return brain;
	}

	public void resetBrain(ServerWorld serverWorldIn) {
		Brain<MatoranEntity> brain = this.getBrain();
		brain.stopAllTasks(serverWorldIn, this);
		this.brain = brain.copy();
		this.initBrain(this.getBrain());
	}

	private void initBrain(Brain<MatoranEntity> matoranbrain) {
		MatoranProfession matoranprofession = this.getMatoranData().getProfession();
		float f = 0.5f;
		matoranbrain.setSchedule(Schedule.SIMPLE);
		// matoranbrain.registerActivity(Activity.WORK,
		// VillagerTasks.work(matoranprofession, f),
		// ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE,
		// MemoryModuleStatus.VALUE_PRESENT)));

//	      matoranbrain.registerActivity(Activity.CORE, VillagerTasks.core(matoranprofession, f));
//	      matoranbrain.registerActivity(Activity.MEET, VillagerTasks.meet(matoranprofession, f), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleStatus.VALUE_PRESENT)));
//	      matoranbrain.registerActivity(Activity.REST, VillagerTasks.rest(matoranprofession, f));
//	      matoranbrain.registerActivity(Activity.IDLE, VillagerTasks.idle(matoranprofession, f));
//	      matoranbrain.registerActivity(Activity.PANIC, VillagerTasks.panic(matoranprofession, f));
//	      matoranbrain.registerActivity(Activity.PRE_RAID, VillagerTasks.preRaid(matoranprofession, f));
//	      matoranbrain.registerActivity(Activity.RAID, VillagerTasks.raid(matoranprofession, f));
//	      matoranbrain.registerActivity(Activity.HIDE, VillagerTasks.hide(matoranprofession, f));
		matoranbrain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
		matoranbrain.setFallbackActivity(Activity.IDLE);
		matoranbrain.switchTo(Activity.IDLE);
		matoranbrain.updateActivity(this.world.getDayTime(), this.world.getGameTime());
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, .5D));
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(TEXTURE, (String) "");
		this.dataManager.register(MATORAN_DATA, new MatoranData(MatoranRegistration.TA.get(), MatoranRegistration.NONE.get()));
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
		if (this.dataManager.get(TEXTURE).isEmpty()) {
			textureOut = "matoran";
		} else {
			textureOut = this.dataManager.get(TEXTURE);
		}
		return textureOut.toLowerCase();
	}

	public void setTexture(String stringIn) {
		// String s = this.dataManager.get(TEXTURE);
		this.dataManager.set(TEXTURE, (String) stringIn);
	}

//	  public void setRevengeTarget(@Nullable LivingEntity livingBase) {
//	      if (livingBase != null && this.world instanceof ServerWorld) {
//	         ((ServerWorld)this.world).updateReputation(IReputationType.VILLAGER_HURT, livingBase, this);
//	         if (this.isAlive() && livingBase instanceof PlayerEntity) {
//	            this.world.setEntityState(this, (byte)13);
//	         }
//	      }
//
//	      super.setRevengeTarget(livingBase);
//	   }

	public void onDeath(DamageSource cause) {
		LOGGER.info("Matoran {} died, message: '{}'", this, cause.getDeathMessage(this).getString());
		Entity entity = cause.getTrueSource();
		if (entity != null) {
			this.triggerHostileReputation(entity);
		}

		// this.func_213742_a(MemoryModuleType.HOME);
		// this.func_213742_a(MemoryModuleType.JOB_SITE);
		// this.func_213742_a(MemoryModuleType.MEETING_POINT);
		super.onDeath(cause);
	}

	private void triggerHostileReputation(Entity entityIn) {
		if (this.world instanceof ServerWorld) {
			Optional<List<LivingEntity>> optional = this.brain.getMemory(MemoryModuleType.VISIBLE_MOBS);
			if (optional.isPresent()) {
				ServerWorld serverworld = (ServerWorld) this.world;
				optional.get().stream().filter((predicate) -> {
					return predicate instanceof IReputationTracking;
				}).forEach((action) -> {
					serverworld.updateReputation(IMatoranReputationType.MATORAN_KILLED, entityIn, (IReputationTracking) action);
				});
			}
		}
	}

	public void setOffers(MerchantOffers offersIn) {
		this.offers = offersIn;
	}

	protected ITextComponent getProfessionName() {
		net.minecraft.util.ResourceLocation profName = this.getMatoranData().getProfession().getRegistryName();
		return new TranslationTextComponent(this.getType().getTranslationKey() + '.' + (!"minecraft".equals(profName.getNamespace()) ? profName.getNamespace() + '.' : "") + profName.getPath());
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 12) {
			this.spawnParticles(ParticleTypes.HEART);
		} else if (id == 13) {
			this.spawnParticles(ParticleTypes.ANGRY_VILLAGER);
		} else if (id == 14) {
			this.spawnParticles(ParticleTypes.HAPPY_VILLAGER);
		} else if (id == 42) {
			this.spawnParticles(ParticleTypes.SPLASH);
		} else {
			super.handleStatusUpdate(id);
		}

	}

	@Override
	public boolean canEquipItem(ItemStack stack) {
		return super.canEquipItem(stack);
	}

	@Override
	public boolean canPickUpLoot() {
		return this.canPickUpLoot;
	}

	public boolean canPickupItem(Item itemIn) {
		return ALLOWED_INVENTORY_ITEMS.contains(itemIn) || this.getMatoranData().getProfession().getSpecificItems().contains(itemIn);
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
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setMatoranData(this.getMatoranData().withElement(MatoranRegistration.TA.get()));
		this.populateTradeData();
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

	}

	//Process interact
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) { 
		if (this.isAlive() && !this.hasCustomer() && !this.isSleeping() && !player.isSecondaryUseActive()) {
			boolean flag1 = this.getOffers().isEmpty();
			if (hand == Hand.MAIN_HAND) {
				if (flag1 && !this.world.isRemote) {
					this.shakeHead();
				}

				// player.addStat(Stats.TALKED_TO_VILLAGER);
			}

			if (flag1) {
				return ActionResultType.func_233537_a_(this.world.isRemote);
			} else {
				if (!this.world.isRemote && !this.offers.isEmpty()) {
					this.displayMerchantGui(player);
				}

				return ActionResultType.func_233537_a_(this.world.isRemote);
			}

		} else {
			return super.func_230254_b_(player, hand);
		}
	}

	private void shakeHead() {
		this.setShakeHeadTicks(40);
		if (!this.world.isRemote()) {
			this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
		}
	}

	private void displayMerchantGui(PlayerEntity player) {
		// this.recalculateSpecialPricesFor(player);
		this.setCustomer(player);
		this.openMerchantContainer(player, this.getDisplayName(), 1);
	}

	public void setMatoranData(MatoranData data) {
		MatoranData matoranData = this.getMatoranData();
		if (matoranData.getProfession() != data.getProfession()) {
			this.offers = null;
		}

		this.dataManager.set(MATORAN_DATA, data);
	}

	public MatoranData getMatoranData() {
		return this.dataManager.get(MATORAN_DATA);
	}

	@Override
	protected void onVillagerTrade(MerchantOffer offer) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void populateTradeData() {
		MatoranData matorandata = this.getMatoranData();
		Int2ObjectMap<MatoranTrades.ITrade[]> int2objectmap = MatoranTrades.MATORAN_DEFAULT_TRADES.get(matorandata.getProfession());
		if (int2objectmap != null && !int2objectmap.isEmpty()) {
			MatoranTrades.ITrade[] amatorantrades$itrade = int2objectmap.get(1);
			if (amatorantrades$itrade != null) {
				MerchantOffers merchantoffers = this.getOffers();
				this.addTrades(merchantoffers, amatorantrades$itrade, 2);
			}
		}

	}

	public void setCustomer(@Nullable PlayerEntity player) {
		boolean flag = this.getCustomer() != null && player == null;
		super.setCustomer(player);
		if (flag) {
			this.resetCustomer();
		}

	}

	protected void resetCustomer() {
		super.resetCustomer();
	}
	
	  public boolean func_223340_ej() {
	      return true;
	   }

	@Nullable
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}

}
