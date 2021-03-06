package com.github.gresh113.bionimine.entities.matoran;

import com.github.gresh113.bionimine.entities.BionimineEntityTypes;
import com.github.gresh113.bionimine.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class KanokaEntity extends AbstractArrowEntity {
	private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(KanokaEntity.class,
			DataSerializers.VARINT);

	public KanokaEntity(EntityType<? extends KanokaEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public KanokaEntity(World worldIn, LivingEntity shooter) {
		super(BionimineEntityTypes.KANOKA.get(), shooter, worldIn);
	}
	
	@Override
	public boolean getIsCritical() {
		return false;
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	

	protected void arrowHit(LivingEntity living) {
		super.arrowHit(living);
//
//		      for(EffectInstance effectinstance : this.potion.getEffects()) {
//		         living.addPotionEffect(new EffectInstance(effectinstance.getPotion(), Math.max(effectinstance.getDuration() / 8, 1), effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.doesShowParticles()));
//		      }
//		      if (!this.customPotionEffects.isEmpty()) {
//		         for(EffectInstance effectinstance1 : this.customPotionEffects) {
//		            living.addPotionEffect(effectinstance1);
//		         }
//		      }

	}

}