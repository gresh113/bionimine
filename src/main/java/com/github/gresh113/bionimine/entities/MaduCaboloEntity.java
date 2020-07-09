package com.github.gresh113.bionimine.entities;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.registration.ItemRegistration;
import com.github.gresh113.bionimine.registration.PotionRegistration;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class MaduCaboloEntity extends ProjectileItemEntity implements IRendersAsItem {

	public MaduCaboloEntity(EntityType<? extends ProjectileItemEntity> typeIn, World worldIn) {
		super(typeIn, worldIn);
	}

	public MaduCaboloEntity(World worldIn, LivingEntity livingEntityIn) {
		super(BionimineEntityTypes.MADU_CABOLO.get(), livingEntityIn, worldIn);
	}

	public MaduCaboloEntity(World worldIn, double x, double y, double z) {
		super(BionimineEntityTypes.MADU_CABOLO.get(), x, y, z, worldIn);
	}

	@Override
	protected Item getDefaultItem() {
		return ItemRegistration.madu_cabolo.get();
	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	protected float getGravityVelocity() {
		return 0.05F;
	}

	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if (!this.world.isRemote) {
			ItemStack itemstack = this.getItem();
			Potion potion = PotionRegistration.MADU_CABOLO.get();
			makeAreaOfEffectCloud(itemstack, potion);
			int i = potion.hasInstantEffect() ? 2007 : 2002;
			this.world.playEvent(i, this.func_233580_cy_(), PotionUtils.getColor(itemstack));
			this.remove();
		}
	}

	private void makeAreaOfEffectCloud(ItemStack stackIn, Potion potionIn) {
		AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ());
		Entity entity = this.func_234616_v_();
		if (entity instanceof LivingEntity) {
			areaeffectcloudentity.setOwner((LivingEntity) entity);
		}

		areaeffectcloudentity.setRadius(3.0F);
		areaeffectcloudentity.setRadiusOnUse(-0.5F);
		areaeffectcloudentity.setWaitTime(10);
		areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());
		areaeffectcloudentity.setPotion(potionIn);

		for (EffectInstance effectinstance : PotionUtils.getFullEffectsFromItem(stackIn)) {
			areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
		}

		CompoundNBT compoundnbt = stackIn.getTag();
		if (compoundnbt != null && compoundnbt.contains("CustomPotionColor", 99)) {
			areaeffectcloudentity.setColor(compoundnbt.getInt("CustomPotionColor"));
		}

		this.world.addEntity(areaeffectcloudentity);
	}
}
