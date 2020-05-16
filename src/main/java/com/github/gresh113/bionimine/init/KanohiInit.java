package com.github.gresh113.bionimine.init;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.kanohi.Kanohi;
import com.github.gresh113.bionimine.kanohi.KanohiAbility;
import com.github.gresh113.bionimine.kanohi.KanohiPowerLevel;
import com.github.gresh113.bionimine.kanohi.KanohiShape;
import com.github.gresh113.bionimine.kanohi.KanohiType;
import com.github.gresh113.bionimine.kanohi.NewKanohiItem;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD)
@ObjectHolder(BioniMine.MODID) // Makes this class an ObjectHolder for items of the bionimine mod
public class KanohiInit {
	//public static final DeferredRegister<Item> KANOHI = new DeferredRegister<>(ForgeRegistries.ITEMS, BioniMine.MODID);
	//private final static Kanohi test = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.HAU, KanohiShape.HAU_GREAT, KanohiType.STANDARD, DyeColor.RED);
	//public static final RegistryObject<Item> test_kanohi = KANOHI.register(test.getName(), () ->
		//new NewKanohiItem(test));
	
	// Instantiate legendary Kanohi separately
	private final static Kanohi VAHI = new Kanohi(KanohiPowerLevel.LEGENDARY, KanohiAbility.VAHI, KanohiShape.VAHI, KanohiType.STANDARD, DyeColor.YELLOW);
	private final static Kanohi IGNIKA = new Kanohi(KanohiPowerLevel.LEGENDARY, KanohiAbility.IGNIKA, KanohiShape.IGNIKA, KanohiType.STANDARD, DyeColor.YELLOW);
	private final static Kanohi MASK_OF_CREATION = new Kanohi(KanohiPowerLevel.LEGENDARY, KanohiAbility.CREATION, KanohiShape.CREATION, KanohiType.STANDARD, DyeColor.YELLOW);
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		for (KanohiAbility ability : KanohiAbility.values()) {
			// Don't register masks of legendary ability
			if (ability == KanohiAbility.VAHI || ability == KanohiAbility.IGNIKA || ability == KanohiAbility.CREATION){continue;}
			for (KanohiPowerLevel level : KanohiPowerLevel.values()) {
				// Doesn't register any legendary-level masks of legendary-level
				if (level == KanohiPowerLevel.LEGENDARY) {continue;}
				Kanohi iteratedKanohi = new Kanohi(level, ability, KanohiShape.HAU_GREAT, KanohiType.STANDARD, DyeColor.RED);;
				String name = iteratedKanohi.getName().toLowerCase();
				event.getRegistry().register(new NewKanohiItem(iteratedKanohi).setRegistryName(name));
				BioniMine.LOGGER.info("item.bionimine." + name +" : |" + iteratedKanohi.getFormattedName() + "|,");
			}
		}
		// Register legendary masks separately
		event.getRegistry().register(new NewKanohiItem(VAHI).setRegistryName(VAHI.getName().toLowerCase()));
		event.getRegistry().register(new NewKanohiItem(IGNIKA).setRegistryName(IGNIKA.getName().toLowerCase()));
		event.getRegistry().register(new NewKanohiItem(MASK_OF_CREATION).setRegistryName(MASK_OF_CREATION.getName().toLowerCase()));
	}
}
