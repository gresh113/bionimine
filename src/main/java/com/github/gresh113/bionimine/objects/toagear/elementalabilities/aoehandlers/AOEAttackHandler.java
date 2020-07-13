package com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers;

import java.util.List;

import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.network.BioniminePacketHandler;
import com.github.gresh113.bionimine.network.ToaEnergyMessage;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ToaAbilityHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public abstract class AOEAttackHandler extends ToaAbilityHandler{
	private static final String name = "AOE";
	private int cooldownTicks = 50;
	public String getAbilityTypeName() {return name;}

	@Override
	public void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		callAoe(stackIn, worldIn, playerentity);
		playerentity.getCooldownTracker().setCooldown(stackIn.getItem(), cooldownTicks );
		this.handleEnergy(playerentity);
	}

	protected void callAoe(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		BlockPos playerPos = playerentity.func_233580_cy_();
		AxisAlignedBB aabb = new AxisAlignedBB(playerPos.north().west().north().west(), playerPos.south().east().south().east());
		for (double x = aabb.minX; x <= aabb.maxX; ++x) {
			for (double z = aabb.minZ; z <= aabb.maxZ; ++z) {
				BlockPos pos = new BlockPos(x, playerPos.getY(), z);
				handleParticles(worldIn, pos);
				List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(playerentity, new AxisAlignedBB(pos).expand(1, 0, 1));
				for (Entity entity : entities) {
					doAttackDamage(entity);
				}
			}
		}
	}

	protected abstract void handleParticles(World worldIn, BlockPos pos);
	protected abstract void doAttackDamage(Entity entityIn);

	protected void handleEnergy(PlayerEntity playerentityIn, boolean flag) {
		IToaEnergy playerCapability = playerentityIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		int eEnergy = playerCapability.getElementalEnergy();
		if (!flag) {
			eEnergy -= (ToaEnergy.maxElementalEnergy / 6);
			if (eEnergy < 0)
				eEnergy = 0;
		}
		playerCapability.setElementalEnergy(eEnergy);
		
		if (playerentityIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) playerentityIn;
			IToaEnergy capability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			ToaEnergyMessage kanohiMessage = new ToaEnergyMessage(capability.getKanohiEnergy(), capability.getElementalEnergy());
			BioniminePacketHandler.INSTANCE.sendTo(kanohiMessage, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	

}
