package com.github.gresh113.bionimine.entities.matoran.ai;

import net.minecraft.entity.merchant.IReputationType;

public interface IMatoranReputationType {
	IReputationType MATORAN_KILLED = register("matoran_killed");
	
	static IReputationType register(final String key) {
	      return new IReputationType() {
	         public String toString() {
	            return key;
	         }
	      };
	   }

}
