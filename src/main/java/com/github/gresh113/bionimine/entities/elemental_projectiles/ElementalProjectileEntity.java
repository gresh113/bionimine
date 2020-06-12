package com.github.gresh113.bionimine.entities.elemental_projectiles;

import com.github.gresh113.bionimine.entities.BionimineEntityTypes;
import com.github.gresh113.bionimine.toa_gear.Elements;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ElementalProjectileEntity extends AbstractArrowEntity {
	private Elements element = Elements.NONE;

	public ElementalProjectileEntity(EntityType<? extends ElementalProjectileEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public ElementalProjectileEntity(World worldIn, LivingEntity shooter) {
		super(BionimineEntityTypes.ICE_PROJECTILE.get(), shooter, worldIn);
	}
	public ElementalProjectileEntity(World worldIn, LivingEntity shooter, Elements elementIn) {
		super(BionimineEntityTypes.ICE_PROJECTILE.get(), shooter, worldIn);
		this.element = elementIn;
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}

	protected void arrowHit(LivingEntity living) {
		super.arrowHit(living);
		if (element == Elements.ICE) {
			living.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 60, 1, false, true));
			}
		if (element == Elements.FIRE) {
			living.setFire(10);
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void func_225516_i_() {
		++this.ticksInGround;
		if (this.ticksInGround >= 10) {
			this.remove();
		}

	}

}
