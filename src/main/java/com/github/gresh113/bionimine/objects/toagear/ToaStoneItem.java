package com.github.gresh113.bionimine.objects.toagear;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ToaStoneItem extends Item {
    private static final Item.Properties defaultProperties = new Item.Properties().group(Bionimine.BioniMineItemGroup.instance).maxStackSize(1).func_234689_a_();
    //Elements element = Elements.NONE;
    ResourceLocation resourceLocation = new ResourceLocation(Bionimine.MODID, "element");
    private final String tag = "Element";

    private IItemPropertyGetter elementGetter = new IItemPropertyGetter() {
        @OnlyIn(Dist.CLIENT)
        @Override
        public float call(ItemStack stackIn, ClientWorld clientIn, LivingEntity entityIn) {
            CompoundNBT compoundNBT = stackIn.getOrCreateTag();
            Elements element = Elements.NONE;
            if (compoundNBT.contains(tag)) {
                element = Elements.fromNBT(compoundNBT, tag);
            }
            float predicate = (float) element.getID() / 100;
            // Bionimine.LOGGER.info(predicate);
            return predicate;
        }

    };


    public ToaStoneItem() {
        this(defaultProperties);
    }

    public ToaStoneItem(Properties properties) {
        super(properties);
        ItemModelsProperties.func_239418_a_(this, resourceLocation, elementGetter);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote) {
            if (context.getPlayer().isSneaking()) {
                BlockState state = world.getBlockState(context.getPos());
                ItemStack stack = context.getItem();
                Elements element = Elements.NONE;
                if (Elements.isStone(state)) {
                    element = Elements.STONE;
                } else if (Elements.isEarth(state)) {
                    element = Elements.EARTH;
                } else if (Elements.isAir(state)) {
                    element = Elements.AIR;
                } else if (Elements.isFire(state)) {
                    element = Elements.FIRE;
                } else if (Elements.isIce(state)) {
                    element = Elements.ICE;
                } else if (Elements.isWater(state)) {
                    element = Elements.WATER;
                }
                CompoundNBT nbt = stack.getOrCreateTag();
                nbt.putString(tag, element.getString());
                stack.setTag(nbt);
                if (element == Elements.NONE) {
                    return ActionResultType.PASS;
                } else
                    return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemStackFromSlot(handIn == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND);
        if (!playerIn.isSneaking()) {
            CompoundNBT compoundNBT = stack.getOrCreateTag();
            Elements element = Elements.NONE;
            if (compoundNBT.contains(tag)) {
                element = Elements.fromNBT(compoundNBT, tag);
            }
            if (Elements.getItemsFromElement(element) != null) {
                //TODO This is broken : (
                playerIn.addItemStackToInventory(Elements.getItemsFromElement(element).get(0));
                playerIn.addItemStackToInventory(Elements.getItemsFromElement(element).get(1));
                playerIn.addItemStackToInventory(Elements.getItemsFromElement(element).get(2));
                stack.shrink(1);
            }
            return ActionResult.resultConsume(stack);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT compoundNBT = stack.getOrCreateTag();
        Elements element = Elements.NONE;
        if (compoundNBT.contains(tag)) {
            element = Elements.fromNBT(compoundNBT, tag);
        }
        String elementString = element.getString().toUpperCase().charAt(0) + element.getString().substring(1);
        tooltip.add(new StringTextComponent("Element: " + elementString));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        CompoundNBT compoundNBT = stack.getOrCreateTag();
        Elements element = Elements.NONE;
        if (compoundNBT.contains(tag)) {
            element = Elements.fromNBT(compoundNBT, tag);
        }
        if (Elements.isWater(entity.getEntityWorld().getBlockState(entity.func_233580_cy_()))) {
            element = Elements.WATER;
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putString(tag, element.getString());
            stack.setTag(nbt);
        } else if (Elements.isFire(entity.getEntityWorld().getBlockState(entity.func_233580_cy_()))) {
            element = Elements.FIRE;
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putString(tag, element.getString());
            stack.setTag(nbt);
        } else if (Elements.isAir(entity.getEntityWorld().getBlockState(entity.func_233580_cy_()))
                || entity.func_233580_cy_().getY() > 128) {
            element = Elements.AIR;
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putString(tag, element.getString());
            stack.setTag(nbt);
        }

        return false;
    }
}
