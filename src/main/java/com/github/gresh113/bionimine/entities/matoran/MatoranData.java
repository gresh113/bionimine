package com.github.gresh113.bionimine.entities.matoran;

import com.github.gresh113.bionimine.api.BionimineRegistries;
import com.github.gresh113.bionimine.registration.MatoranRegistration;
import com.google.common.collect.ImmutableMap;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.datafix.DelegatingDynamicOps;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MatoranData {
	private static final int[] field_221136_a = new int[] { 0, 10, 70, 150, 250 };
	private final MatoranElement element;
	private final MatoranProfession profession;

	public MatoranData(MatoranElement elementIn, MatoranProfession professionIn) {
		this.element = elementIn;
		this.profession = professionIn;
	}

	public MatoranElement getElement() {
		return this.element;
	}

	public MatoranProfession getProfession() {
		return this.profession;
	}

	public MatoranData withElement(MatoranElement elementIn) {
		return new MatoranData(elementIn, this.profession);
	}

	public MatoranData withProfession(MatoranProfession professionIn) {
		return new MatoranData(this.element, professionIn);
	}

	public <T> T serialize(DelegatingDynamicOps<T> opIn) {
		return opIn.createMap(ImmutableMap.of(opIn.createString("element"), opIn.createString(BionimineRegistries.MATORAN_ELEMENTS.getKey(this.element).toString()), opIn.createString("profession"), opIn.createString(BionimineRegistries.MATORAN_PROFESSIONS.getKey(this.profession).toString())));
	}

	@OnlyIn(Dist.CLIENT)
	public static int func_221133_b(int p_221133_0_) {
		return func_221128_d(p_221133_0_) ? field_221136_a[p_221133_0_ - 1] : 0;
	}

	public static int func_221127_c(int p_221127_0_) {
		return func_221128_d(p_221127_0_) ? field_221136_a[p_221127_0_] : 0;
	}

	public static boolean func_221128_d(int p_221128_0_) {
		return p_221128_0_ >= 1 && p_221128_0_ < 5;
	}

	public static final IDataSerializer<MatoranData> DATA = new IDataSerializer<MatoranData>() {
		public void write(PacketBuffer buf, MatoranData value) {
			buf.writeVarInt(BionimineRegistries.MATORAN_ELEMENTS.getID(value.getElement()));
			// buf.writeVarInt(Registry.VILLAGER_PROFESSION.getId(value.getProfession()));
		}

		public MatoranData read(PacketBuffer buf) {
			return new MatoranData(BionimineRegistries.MATORAN_ELEMENTS.getValue(buf.readVarInt()), BionimineRegistries.MATORAN_PROFESSIONS.getValue(buf.readVarInt()));
		}

		public MatoranData copyValue(MatoranData value) {
			return value;
		}
	};
	static {
		DataSerializers.registerSerializer(DATA);
	}
}
