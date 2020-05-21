package com.github.gresh113.bionimine.init;

import java.util.ArrayList;
import java.util.List;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.kanohi.Kanohi;
import com.github.gresh113.bionimine.kanohi.KanohiAbility;
import com.github.gresh113.bionimine.kanohi.KanohiPalette;
import com.github.gresh113.bionimine.kanohi.KanohiPowerLevel;
import com.github.gresh113.bionimine.kanohi.KanohiShape;
import com.github.gresh113.bionimine.kanohi.KanohiType;
import com.github.gresh113.bionimine.kanohi.KanohiItem;

import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
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
	public static List<KanohiItem> itemArray = new ArrayList<KanohiItem>();
	private final static Kanohi VAHI = new Kanohi(KanohiPowerLevel.LEGENDARY, KanohiAbility.VAHI, KanohiType.STANDARD);
	private final static Kanohi IGNIKA = new Kanohi(KanohiPowerLevel.LEGENDARY, KanohiAbility.IGNIKA, KanohiType.STANDARD);
	private final static Kanohi MASK_OF_CREATION = new Kanohi(KanohiPowerLevel.LEGENDARY, KanohiAbility.CREATION, KanohiType.STANDARD);
	private final static Kanohi POWERLESS_MASK = new Kanohi(KanohiPowerLevel.POWERLESS, KanohiAbility.NONE, KanohiType.STANDARD);
	public final static KanohiItem PowerlessMaskItem = new KanohiItem(POWERLESS_MASK);
	
	
	
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		
		// Iterate through Kanohi Abilities
		for (KanohiAbility ability : KanohiAbility.values()) {
			
			// Don't register masks of legendary ability or masks without a power
			if (ability == KanohiAbility.VAHI || ability == KanohiAbility.IGNIKA || ability == KanohiAbility.CREATION || ability == KanohiAbility.NONE){continue;}
			
			// Iterate through power levels
			for (KanohiPowerLevel level : KanohiPowerLevel.values()) {
				
				// Doesn't register any legendary-level masks or powerless masks
				if (level == KanohiPowerLevel.LEGENDARY || level == KanohiPowerLevel.POWERLESS) {continue;}
				
				Kanohi iteratedKanohi = new Kanohi(level, ability, KanohiType.STANDARD);;
				String name = iteratedKanohi.getName().toLowerCase();
				KanohiItem itemtoRegister = new KanohiItem(iteratedKanohi);
				itemArray.add(itemtoRegister);
				event.getRegistry().register(itemtoRegister.setRegistryName(name));
				
				//BioniMine.LOGGER.info("item.bionimine." + name +" : |" + iteratedKanohi.getFormattedName() + "|,");
			}
			
		}
		itemArray.add(PowerlessMaskItem);
		
		// Register legendary masks separately
		event.getRegistry().register(new KanohiItem(VAHI).setRegistryName(VAHI.getName().toLowerCase()));
		event.getRegistry().register(new KanohiItem(IGNIKA).setRegistryName(IGNIKA.getName().toLowerCase()));
		event.getRegistry().register(new KanohiItem(MASK_OF_CREATION).setRegistryName(MASK_OF_CREATION.getName().toLowerCase()));
		event.getRegistry().register(PowerlessMaskItem.setRegistryName(POWERLESS_MASK.getName().toLowerCase()));
		//BioniMine.LOGGER.info("Array includes:");
		//for (NewKanohiItem index : itemArray) {BioniMine.LOGGER.info("-" + index.getName());}
		//BioniMine.LOGGER.info(itemArray.toString());
		
		
	}
	
	@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.MOD, value = Dist.CLIENT)
	public class ColorInit{
		@SubscribeEvent
		public void onColorHandlerEvent(ColorHandlerEvent.Item event) {
			BioniMine.LOGGER.info("Colors handled!");
			
			
		}
	}
}
