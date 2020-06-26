package com.github.gresh113.bionimine.objects.toagear;

import java.util.List;

import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ToaAbilityHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ToaTool extends Item {
	private static final Item.Properties defaultProperties = new Item.Properties().group(BioniMineItemGroup.instance).maxStackSize(1);
	Elements element;
	ToaAbilityHandler currentAbilityHandler;

	// Constructors
	public ToaTool(Properties properties) {
		super(properties);
	}

	public ToaTool(Properties properties, Elements elementIn) {
		super(properties);
		this.element = elementIn;
	}

	public ToaTool(Elements elementIn) {
		super(defaultProperties);
		this.element = elementIn;
	}

	public ToaTool() {
		super(defaultProperties);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			// ItemStack itemstack = playerentity.findAmmo(stack);
			if (!(currentAbilityHandler == null)) {
				currentAbilityHandler.trigger(stack, worldIn, playerentity);
			}
			// Bionimine.LOGGER.info(element.getName());
			playerentity.addStat(Stats.ITEM_USED.get(this));
		}
	}

	public Elements getElement() {
		return element;
	}

	/**
	 * Gets the velocity of the arrow entity from the bow's charge
	 */
	public static float getArrowVelocity(int charge) {
		float f = (float) charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f;
	}

	// public AbstractArrowEntity getProjectileEntityFromElement(Element elementIn)
	// { }

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getUseDuration(ItemStack stack) {
		return 1000;
	}

	/**
	 * returns the action that specifies what animation to play when the items is
	 * being used
	 */
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	/**
	 * Called to trigger the item's "innate" right click behavior. To handle when
	 * this item is used on a Block, see {@link #onItemUse}.
	 */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (playerIn.isSneaking()) {
			if (itemstack.getItem() instanceof ToaTool) {
				ToaTool toolItem = (ToaTool) itemstack.getItem();
				CompoundNBT compoundNBT = itemstack.getOrCreateTag();
				String tag = "SelectedAbility";
				int i;
				if (compoundNBT.contains(tag)) {
					i = compoundNBT.getInt(tag);
				} else {
					i = 0;
				}
				ToaAbilityHandler[] abilityArray = toolItem.getElement().getAbilityHolder().getAbilityArray();
				++i;
				if (i > abilityArray.length - 1)
					i = 0;

				currentAbilityHandler = abilityArray[i];
				if (!(currentAbilityHandler == null)) {
					playerIn.sendStatusMessage(new StringTextComponent(currentAbilityHandler.getAbilityTypeName()) + " Mode", true);
				}
				compoundNBT.putInt(tag, i);

			}
			return ActionResult.resultPass(itemstack);
		} else {
//			if (playerIn instanceof ClientPlayerEntity) {
//				ClientPlayerEntity player = (ClientPlayerEntity) playerIn;
//				AbilityGuiMessage guiMessage = new AbilityGuiMessage();
//				BioniminePacketHandler.INSTANCE.sendTo(guiMessage, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_SERVER);
//			}
			IToaEnergy playerCapability = playerIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			int eEnergy = playerCapability.getElementalEnergy();

			if (eEnergy > 1000 || playerIn.abilities.isCreativeMode) {

				boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

				ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
				if (ret != null)
					return ret;

				playerIn.setActiveHand(handIn);
				return ActionResult.resultPass(itemstack);
			} else

				return ActionResult.resultFail(itemstack);
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (stack.getItem() instanceof ToaTool) {
			String laterText = "";
			ToaTool toolItem = (ToaTool) stack.getItem();
			Elements element = toolItem.getElement();
			if (!(element == null)) {
				String elementName = element.func_176610_l();
				String firstLetter = "" + elementName.charAt(0);
				laterText = " of " + firstLetter.toUpperCase() + elementName.substring(1, elementName.length());
			}
			tooltip.add(new StringTextComponent("Toa Tool" + laterText));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
	}

}
