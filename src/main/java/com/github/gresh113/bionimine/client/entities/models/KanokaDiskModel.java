package com.github.gresh113.bionimine.client.entities.models;
import com.github.gresh113.bionimine.entities.matoran.KanokaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class KanokaDiskModel extends EntityModel<KanokaEntity> {
	private final ModelRenderer Disk;

	public KanokaDiskModel() {
		textureWidth = 64;
		textureHeight = 17;

		Disk = new ModelRenderer(this);
		Disk.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Disk, 0.0F, 3.1416F, 0.0F);
		Disk.setTextureOffset(0, 0).addBox(-8, 0, -8, 16.0F, 1.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Disk.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	float calcRotation;
	@Override
	public void setRotationAngles(KanokaEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		
		if (entityIn.isAirBorne) {calcRotation =  (float) (Math.log(ageInTicks) / Math.log(360f));}
		this.Disk.rotateAngleY = calcRotation;
	}

}