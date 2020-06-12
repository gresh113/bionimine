package com.github.gresh113.bionimine.toa_gear.kanohi;

import com.github.gresh113.bionimine.capabilities.IToaEnergy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EquipmentEventChecker {
	@CapabilityInject(IToaEnergy.class)
	public static final Capability<IToaEnergy> TOA_ENERGY = null;

	@SubscribeEvent
	public void equipmentChange(LivingEquipmentChangeEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			if (event.getSlot() == EquipmentSlotType.HEAD) {
				if (event.getFrom().getItem() instanceof KanohiItem) {
					KanohiItem kanohiFrom = (KanohiItem) event.getFrom().getItem();
					kanohiFrom.resetPowerLevel();
					}
			}
		}
	}
}
