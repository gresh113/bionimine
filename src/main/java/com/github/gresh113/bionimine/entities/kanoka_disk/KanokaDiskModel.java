package com.github.gresh113.bionimine.entities.kanoka_disk;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class KanokaDiskModel extends EntityModel<Entity> {
	private final ModelRenderer Disk;
	

	public KanokaDiskModel() {
		textureWidth = 32;
		textureHeight = 16;
		Disk = new ModelRenderer(this);
		Disk.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Disk, 0.0F, 3.1416F, 0.0F);
		Disk.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 16.0F, 1.0F, 16.0F, 0.0F, false);
		
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Disk.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}