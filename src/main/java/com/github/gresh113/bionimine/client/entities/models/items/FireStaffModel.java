package com.github.gresh113.bionimine.client.entities.models.items;

import com.github.gresh113.bionimine.Bionimine;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

@OnlyIn(Dist.CLIENT)
public class FireStaffModel extends Model {
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Bionimine.MODID, "textures/entity/fire_staff.png");
	private final ModelRenderer Staff;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;

	public FireStaffModel() {
		super(RenderType::getEntityCutout);
		textureWidth = 32;
		textureHeight = 32;

		Staff = new ModelRenderer(this);
		Staff.setRotationPoint(0.0F, 24.0F, 0.0F);
		Staff.setTextureOffset(0, 0).addBox(-1.0F, -18.0F, 0.0F, 1.0F, 18.0F, 1.0F, 0.0F, false);
		Staff.setTextureOffset(0, 19).addBox(-2.0F, -21.0F, -1.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		Staff.setTextureOffset(4, 0).addBox(-1.0F, -21.0F, -2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-4.0F, -21.0F, -6.0F);
		Staff.addChild(bone);
		setRotationAngle(bone, 0.0F, -0.7854F, 0.0F);
		bone.setTextureOffset(24, 0).addBox(7.0711F, -3.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-7.0F, -21.0F, -6.0F);
		Staff.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.7854F, 0.0F);
		bone2.setTextureOffset(24, 0).addBox(0.0F, -3.0F, 7.0711F, 0.0F, 3.0F, 4.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-0.5F, -19.5F, 2.5F);
		Staff.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 3.1416F, 0.0F);
		bone3.setTextureOffset(4, 0).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Staff.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}