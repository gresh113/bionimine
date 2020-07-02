package com.github.gresh113.bionimine.client.entities.models;

import com.github.gresh113.bionimine.entities.ElementalSpikesEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ElementalSpikesModel extends EntityModel<ElementalSpikesEntity> {
	private final ModelRenderer Spike;

	public ElementalSpikesModel() {
		textureWidth = 64;
		textureHeight = 17;

		Spike = new ModelRenderer(this);
		Spike.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Spike, 0.0F, 0F, 0.0F);
		Spike.setTextureOffset(0, 0).addBox(4.0F, 8.0F, 4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ElementalSpikesEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub

	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		Spike.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);

	}

}
