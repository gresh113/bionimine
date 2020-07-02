package com.github.gresh113.bionimine.entities.matoran.ai.tasks;

import java.util.List;

import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.memory.WalkTarget;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPosWrapper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class MatoranPickupFoodTask  extends Task<MatoranEntity>{
	private List<ItemEntity> nearbyEntities = Lists.newArrayList();

	   public MatoranPickupFoodTask() {
	      super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryModuleStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryModuleStatus.VALUE_ABSENT));
	   }

	   protected boolean shouldExecute(ServerWorld worldIn, MatoranEntity owner) {
	      this.nearbyEntities = worldIn.getEntitiesWithinAABB(ItemEntity.class, owner.getBoundingBox().grow(4.0D, 2.0D, 4.0D));
	      return !this.nearbyEntities.isEmpty();
	   }

	   protected void startExecuting(ServerWorld worldIn, MatoranEntity entityIn, long gameTimeIn) {
	      ItemEntity itementity = this.nearbyEntities.get(worldIn.rand.nextInt(this.nearbyEntities.size()));
	      if (entityIn.canPickupItem(itementity.getItem().getItem())) {
	         Vector3d vec3d = itementity.getPositionVec();
	         entityIn.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosWrapper(new BlockPos(vec3d)));
	         entityIn.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(vec3d, 0.5F, 0));
	      }

	   }
}
