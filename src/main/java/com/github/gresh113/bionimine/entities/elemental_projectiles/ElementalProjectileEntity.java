package com.github.gresh113.bionimine.entities.elemental_projectiles;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.entities.BionimineEntityTypes;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class ElementalProjectileEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData {
	Elements element;

	public ElementalProjectileEntity(EntityType<? extends ElementalProjectileEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public ElementalProjectileEntity(World worldIn, LivingEntity shooter) {
		super(BionimineEntityTypes.ELEMENTAL_PROJECTILE.get(), shooter, worldIn);
	}

	public ElementalProjectileEntity(World worldIn, LivingEntity shooter, Elements elementIn) {
		super(BionimineEntityTypes.ELEMENTAL_PROJECTILE.get(), shooter, worldIn);
		element = elementIn;
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

	public Elements getElement() {
		return element;
	}

	public void setElement(Elements element) {
		this.element = element;
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

	@Override
	public void tick() {
		super.tick();
		if (!(element == null))
			element.getAbilityHolder().getProjectileHandler().spawnParticles(this);

	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		if (!(element == null)) {
			buffer.writeInt(element.getID());
		}
		// Bionimine.LOGGER.info("Wrote element as: " + element.getName());

	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		int id = additionalData.readInt();
		this.element = Elements.getElementFromID(id);
		// Bionimine.LOGGER.info("Read element as: " + element.getName());
	}

}
