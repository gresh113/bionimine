package com.github.gresh113.bionimine.entities.matoran.ai.tasks;

import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;
import com.github.gresh113.bionimine.entities.matoran.MatoranProfession;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.ai.brain.task.InteractWithDoorTask;
import net.minecraft.entity.ai.brain.task.LookTask;
import net.minecraft.entity.ai.brain.task.SwimTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.WalkToTargetTask;

public class MatoranTasks {
	//@formatter:off
	public static ImmutableList<Pair<Integer, ? extends Task<? super MatoranEntity>>> core(MatoranProfession profession, float speed) {
	      return ImmutableList.of(
	    		  Pair.of(0, new SwimTask(0.8F)), 
	    		  Pair.of(0, new InteractWithDoorTask()), 
	    		  Pair.of(0, new LookTask(45, 90)) 
//	    		  ,Pair.of(0, new PanicTask()) // Would need to be rewritten as task for Matoran
	    		  ,Pair.of(1, new WalkToTargetTask(200)) 
	    		  ,Pair.of(2, new MatoranTradeTask(speed)) 
	    		  ,Pair.of(5, new MatoranPickupFoodTask()) 
//	    		  ,Pair.of(10, new GatherPOITask(profession.getPointOfInterest(), MemoryModuleType.JOB_SITE, true)) 
//	    		  ,Pair.of(10, new GatherPOITask(PointOfInterestType.HOME, MemoryModuleType.HOME, false)) 
//	    		  ,Pair.of(10, new GatherPOITask(PointOfInterestType.MEETING, MemoryModuleType.MEETING_POINT, true)) 
//	    		  ,Pair.of(10, new AssignProfessionTask()), Pair.of(10, new ChangeJobTask())
	    		  );
	   }

}
