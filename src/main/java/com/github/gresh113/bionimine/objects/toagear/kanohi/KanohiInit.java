package com.github.gresh113.bionimine.objects.toagear.kanohi;

import java.util.ArrayList;
import java.util.List;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Bionimine.MODID, bus = Bus.MOD)
@ObjectHolder(Bionimine.MODID) // Makes this class an ObjectHolder for items of the bionimine mod
public class KanohiInit {
	// public static final DeferredRegister<Item> KANOHI = new
	// DeferredRegister<>(ForgeRegistries.ITEMS, BioniMine.MODID);
	// private final static Kanohi test = new Kanohi(KanohiPowerLevel.GREAT,
	// KanohiAbility.HAU, KanohiShape.HAU_GREAT, KanohiType.STANDARD, DyeColor.RED);
	// public static final RegistryObject<Item> test_kanohi =
	// KANOHI.register(test.getName(), () ->
	// new NewKanohiItem(test));

	// Instantiate legendary Kanohi separately
	public static List<KanohiItem> itemArray = new ArrayList<KanohiItem>();
	private final static Kanohi VAHI = new Kanohi(KanohiPower.VAHI, KanohiType.STANDARD);
	//private final static Kanohi IGNIKA = new Kanohi(KanohiAbility.IGNIKA, KanohiType.STANDARD);
	//private final static Kanohi MASK_OF_CREATION = new Kanohi(KanohiAbility.CREATION, KanohiType.STANDARD);
	
	private final static Kanohi POWERLESS_MASK = new Kanohi(KanohiPower.NONE, KanohiType.STANDARD);
	public final static KanohiItem PowerlessMaskItem = new KanohiItem(POWERLESS_MASK);

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {

		// Iterate through Kanohi Abilities
		for (KanohiPower ability : KanohiPower.values()) {
			if (ability == KanohiPower.NONE || ability == KanohiPower.VAHI) {continue;}

			Kanohi iteratedKanohi = new Kanohi(ability, KanohiType.STANDARD);
			;
			String name = iteratedKanohi.getName().toLowerCase();
			KanohiItem itemtoRegister = new KanohiItem(iteratedKanohi);
			itemArray.add(itemtoRegister);
			event.getRegistry().register(itemtoRegister.setRegistryName(name));

			// BioniMine.LOGGER.info("item.bionimine." + name +" : |" +
			// iteratedKanohi.getFormattedName() + "|,");

		}

		// Register legendary masks separately
		event.getRegistry().register(new KanohiItem(VAHI).setRegistryName(VAHI.getName().toLowerCase()));
		//event.getRegistry().register(new KanohiItem(IGNIKA).setRegistryName(IGNIKA.getName().toLowerCase()));
		//event.getRegistry()
			//	.register(new KanohiItem(MASK_OF_CREATION).setRegistryName(MASK_OF_CREATION.getName().toLowerCase()));
		
		event.getRegistry().register(PowerlessMaskItem.setRegistryName(POWERLESS_MASK.getName().toLowerCase()));
		
		// BioniMine.LOGGER.info("Array includes:");
		// for (NewKanohiItem index : itemArray) {BioniMine.LOGGER.info("-" +
		// index.getName());}
		// BioniMine.LOGGER.info(itemArray.toString());

	}
}
