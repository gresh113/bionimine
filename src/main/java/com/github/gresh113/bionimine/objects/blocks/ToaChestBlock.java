package com.github.gresh113.bionimine.objects.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.objects.tileentity.ToaChestTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ToaChestBlock extends Block{
	   public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;
	   public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;

	   public ToaChestBlock(Block.Properties properties) {
	      super(properties);
	      this.setDefaultState(this.stateContainer.getBaseState().with(PROPERTY_FACING, Direction.NORTH).with(PROPERTY_OPEN, Boolean.valueOf(false)));
	   }

	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceIn) {
	      if (worldIn.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	         TileEntity tileentity = worldIn.getTileEntity(pos);
	         if (tileentity instanceof ToaChestTileEntity) {
	            player.openContainer((ToaChestTileEntity)tileentity);
	            //player.addStat(Stats.OPEN_BARREL);
	         }

	         return ActionResultType.SUCCESS;
	      }
	   }

	   @Nullable
	   public TileEntity createNewTileEntity(IBlockReader worldIn) {
	      return new ToaChestTileEntity();
	   }

	   /**
	    * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	    */
	   public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
	      if (stack.hasDisplayName()) {
	         TileEntity tileentity = worldIn.getTileEntity(pos);
	         if (tileentity instanceof ToaChestTileEntity) {
	            ((ToaChestTileEntity)tileentity).setCustomName(stack.getDisplayName());
	         }
	      }

	   }

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(PROPERTY_FACING, PROPERTY_OPEN);
	   }

	   public BlockState getStateForPlacement(BlockItemUseContext context) {
	      return this.getDefaultState().with(PROPERTY_FACING, context.getNearestLookingDirection().getOpposite());
	   }
	}
