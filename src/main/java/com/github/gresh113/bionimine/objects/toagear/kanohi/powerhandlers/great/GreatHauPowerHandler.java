package com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great;

import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.objects.blocks.HauShieldBlock;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.KanohiPowerHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GreatHauPowerHandler extends KanohiPowerHandler {

	@Override
	public void trigger(int powerLevelIn, ItemStack stackIn, World world, PlayerEntity playerIn) {
		playerIn.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 0, 1, false, false, false));
		playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 0, 1, false, false, false));
		playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 0, 2, false, false, false));
		if (!world.isRemote) {
			BlockPos currentPos = playerIn.func_233580_cy_();
			//Direction[] directions = Direction.getFacingDirections(playerIn);
			//Bionimine.LOGGER.info(directions[0].func_176610_l());
			int xparity;
			int zparity;
			Direction dir = playerIn.getHorizontalFacing();
			switch (dir) {
			case EAST:
				xparity = 1;
				zparity = 0;
				break;
			case WEST:
				xparity = -1;
				zparity = 0;
				break;
			case NORTH:
				xparity = 0;
				zparity = -1;
				break;
			case SOUTH:
				xparity = 0;
				zparity = 1;
				break;

			default:
				xparity = 1;
				zparity = 0;
				break;
			}

			BlockPos origin = new BlockPos(currentPos.getX() + (xparity * 2), currentPos.getY(), currentPos.getZ() + (zparity * 2));

			if (powerLevelIn == 1) {
				BlockPos setPos = movePosFromDir(currentPos, dir);
				set1x2(setPos, world, dir);
				
			} else if (powerLevelIn == 2) {
				int maxheight = 3;
//				BlockPos setPos = movePosFromDir(currentPos, dir);
//				set1x3(setPos, world, dir);
//				set1x3(setPos, world, dir);
//				set1x3(setPos, world, dir);
				if (xparity == 0) {
					for (int height = 0; height < maxheight; ++height) {
						for (int length = origin.getX() - 2; length < origin.getX() + 2; ++length) {

							BlockPos setPos = new BlockPos(length, origin.getY() + height, origin.getZ());
							if (world.isAirBlock(setPos)) {
								world.setBlockState(setPos, ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
							}
						}
					}

				} else if (xparity != 0) {
					for (int height = 0; height < maxheight; ++height) {
						for (int length = origin.getZ() - 2; length < origin.getZ() + 2; ++length) {

							BlockPos setPos = new BlockPos(origin.getX(), origin.getY() + height, length);
							if (world.isAirBlock(setPos)) {
								world.setBlockState(setPos, ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
							}
						}
					}

				}
			} else if (powerLevelIn == 3) {
				BlockPos setPos = currentPos.east();
				set1x2(setPos, world, Direction.EAST);
				setPos = currentPos.west();
				set1x2(setPos, world, Direction.WEST);
				setPos = currentPos.north();
				set1x2(setPos, world, Direction.NORTH);
				setPos = currentPos.south();
				set1x2(setPos, world, Direction.SOUTH);
					
			}
		}
	}

	private void set1x2(BlockPos setPos, World world, Direction dir) {
		if (world.isAirBlock(setPos)) {
			world.setBlockState(setPos, ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
		}
		if (world.isAirBlock(setPos.up())) {
			world.setBlockState(setPos.up(), ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
		}
	}
	private void set1x3(BlockPos setPos, World world, Direction dir) {
		if (world.isAirBlock(setPos)) {
			world.setBlockState(setPos, ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
		}
		if (world.isAirBlock(setPos.up())) {
			world.setBlockState(setPos.up(), ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
		}
		if (world.isAirBlock(setPos.up().up())) {
			world.setBlockState(setPos.up().up(), ((HauShieldBlock) BlockInit.hau_shield.get()).setShieldDirection(dir));
		}
	}
	
	private BlockPos movePosFromDir(BlockPos cur, Direction dir) {
		switch (dir) {
		case EAST:
			return cur.east();
		case WEST:
			return cur.west();
		case NORTH:
			return cur.north();
		case SOUTH:
			return cur.south();
		default:
			return cur.north();
		}
	}
}
