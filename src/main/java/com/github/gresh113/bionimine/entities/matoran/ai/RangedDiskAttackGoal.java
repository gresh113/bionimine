package com.github.gresh113.bionimine.entities.matoran.ai;

import java.util.EnumSet;

import com.github.gresh113.bionimine.entities.matoran.MatoranEntity;
import com.github.gresh113.bionimine.objects.items.BambooDiskItem;
import com.github.gresh113.bionimine.registration.ItemRegistration;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.BowItem;

public class RangedDiskAttackGoal <T extends MatoranEntity & IRangedAttackMob> extends Goal {
	   private final T entitySelf;
	   private final double moveSpeedAmp;
	   private int attackCooldown;
	   private final float maxAttackDistance;
	   private int attackTime = -1;
	   private int seeTime;
	   private boolean strafingClockwise;
	   private boolean strafingBackwards;
	   private int strafingTime = -1;

		public RangedDiskAttackGoal(T entityIn, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn) {
	      this.entitySelf = entityIn;
	      this.moveSpeedAmp = moveSpeedAmpIn;
	      this.attackCooldown = attackCooldownIn;
	      this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
	      this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	   }

	   public void setAttackCooldown(int attackCooldownIn) {
	      this.attackCooldown = attackCooldownIn;
	   }
	   

	   /**
	    * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	    * method as well.
	    */
	   public boolean shouldExecute() {
	      return this.entitySelf.getAttackTarget() == null ? false : this.isDiskInMainhand();
	   }

	   protected boolean isDiskInMainhand() {
	      return this.entitySelf.func_233634_a_(item -> item instanceof BambooDiskItem);
	   }

	   /**
	    * Returns whether an in-progress EntityAIBase should continue executing
	    */
	   public boolean shouldContinueExecuting() {
	      return (this.shouldExecute() || !this.entitySelf.getNavigator().noPath()) && this.isDiskInMainhand();
	   }

	   /**
	    * Execute a one shot task or start executing a continuous task
	    */
	   public void startExecuting() {
	      super.startExecuting();
	      this.entitySelf.setAggroed(true);
	   }

	   /**
	    * Reset the task's internal state. Called when this task is interrupted by another one
	    */
	   public void resetTask() {
	      super.resetTask();
	      this.entitySelf.setAggroed(false);
	      this.seeTime = 0;
	      this.attackTime = -1;
	      this.entitySelf.resetActiveHand();
	   }

	   /**
	    * Keep ticking a continuous task that has already been started
	    */
	   public void tick() {
	      LivingEntity target = this.entitySelf.getAttackTarget();
	      if (target != null) {
	         double targetdistance = this.entitySelf.getDistanceSq(target.getPosX(), target.getPosY(), target.getPosZ());
	         boolean canseetarget = this.entitySelf.getEntitySenses().canSee(target);
	         boolean flag1 = this.seeTime > 0;
	         if (canseetarget != flag1) {
	            this.seeTime = 0;
	         }

	         if (canseetarget) {
	            ++this.seeTime;
	         } else {
	            --this.seeTime;
	         }

	         if (!(targetdistance > (double)this.maxAttackDistance) && this.seeTime >= 20) {
	            this.entitySelf.getNavigator().clearPath();
	            ++this.strafingTime;
	         } else {
	            this.entitySelf.getNavigator().tryMoveToEntityLiving(target, this.moveSpeedAmp);
	            this.strafingTime = -1;
	         }

	         if (this.strafingTime >= 20) {
	            if ((double)this.entitySelf.getRNG().nextFloat() < 0.3D) {
	               this.strafingClockwise = !this.strafingClockwise;
	            }

	            if ((double)this.entitySelf.getRNG().nextFloat() < 0.3D) {
	               this.strafingBackwards = !this.strafingBackwards;
	            }

	            this.strafingTime = 0;
	         }

	         if (this.strafingTime > -1) {
	            if (targetdistance > (double)(this.maxAttackDistance * 0.75F)) {
	               this.strafingBackwards = false;
	            } else if (targetdistance < (double)(this.maxAttackDistance * 0.25F)) {
	               this.strafingBackwards = true;
	            }

	            this.entitySelf.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
	            this.entitySelf.faceEntity(target, 30.0F, 30.0F);
	         } else {
	            this.entitySelf.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
	         }

	         if (this.entitySelf.isHandActive()) {
	            if (!canseetarget && this.seeTime < -60) {
	               this.entitySelf.resetActiveHand();
	            } else if (canseetarget) {
	               int i = this.entitySelf.getItemInUseMaxCount();
	               if (i >= 20) {
	                  this.entitySelf.resetActiveHand();
	                  this.entitySelf.attackEntityWithRangedAttack(target, BambooDiskItem.getArrowVelocity(i));
	                  this.attackTime = this.attackCooldown;
	               }
	            }
	         } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
	            this.entitySelf.setActiveHand(ProjectileHelper.getHandWith(this.entitySelf, ItemRegistration.bamboo_disk.get()));
	         }

	      }
	   }
	}