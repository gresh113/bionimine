package com.github.gresh113.bionimine.integration.jei;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.toagear.kanohi.KanohiInit;
import com.github.gresh113.bionimine.toagear.kanohi.KanohiItem;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class BionimineJEIPlugin implements IModPlugin{

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Bionimine.MODID);
	}
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		// TODO Auto-generated method stub
		IModPlugin.super.registerItemSubtypes(registration);
		for (KanohiItem item : KanohiInit.itemArray) {
			registration.useNbtForSubtypes(item);
		}
		
	}

}
