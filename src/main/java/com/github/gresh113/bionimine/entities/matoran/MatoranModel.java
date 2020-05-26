package com.github.gresh113.bionimine.entities.matoran;

import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MatoranModel<T extends MatoranEntity> extends EntityModel<T> implements IHasArm, IHasHead {
	public boolean isSitting;

	public final ModelRenderer Body;
	public final ModelRenderer Torso;
	public final ModelRenderer LegL;
	public final ModelRenderer Head;
	public final ModelRenderer Headwear;
	public final ModelRenderer ArmL;
	public final ModelRenderer LegR;
	public final ModelRenderer ArmR;
	public MatoranModel.ArmPose leftArmPose = MatoranModel.ArmPose.EMPTY;
	public MatoranModel.ArmPose rightArmPose = MatoranModel.ArmPose.EMPTY;

	public MatoranModel() {
		this(RenderType::getEntityCutoutNoCull, .5f, 0.0F, 64, 32);
	}

	protected MatoranModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
		this(RenderType::getEntityCutoutNoCull, modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
	}

	public MatoranModel(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn,
			int textureWidthIn, int textureHeightIn) {
		textureWidth = 64;
		textureHeight = 40;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(4.0F, -8.0F, -2.0F);
		Body.addChild(Torso);
		Torso.setTextureOffset(16, 16).addBox(-8.0F, -8.0F, 0.0F, 8.0F, 8.0F, 4.0F, 0.0F, false);

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(2.0F, -7.0F, 0.0F);
		Body.addChild(LegL);
		LegL.setTextureOffset(41, 16).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -16.0F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Headwear = new ModelRenderer(this);
		Headwear.setRotationPoint(0.0F, 0.0F + yOffsetIn, 0.0F);
		Body.addChild(Headwear);
		Headwear.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn + 0.25F);
		

		ArmL = new ModelRenderer(this);
		ArmL.setRotationPoint(5.5F, -15.0F, 0.0F);
		Body.addChild(ArmL);
		ArmL.setTextureOffset(0, 16).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, false);

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(-2.0F, -7.0F, 0.0F);
		Body.addChild(LegR);
		LegR.setTextureOffset(41, 16).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		ArmR = new ModelRenderer(this);
		ArmR.setRotationPoint(-5.5F, -15.0F, 0.0F);
		Body.addChild(ArmR);
		ArmR.setTextureOffset(0, 28).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, false);

	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		this.Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.Headwear.copyModelAngles(this.Head);

		this.Body.rotateAngleY = 0.0F;
		this.ArmR.rotationPointZ = 0.0F;
		this.ArmR.rotationPointX = -5.0F;
		this.ArmL.rotationPointZ = 0.0F;
		this.ArmL.rotationPointX = 5.0F;
		float f = 1.0F;

		if (f < 1.0F) {
			f = 1.0F;
		}

		this.ArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F
				/ f;
		this.ArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.ArmR.rotateAngleZ = 0.0F;
		this.ArmL.rotateAngleZ = 0.0F;
		this.LegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.LegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
		this.LegR.rotateAngleY = 0.0F;
		this.LegL.rotateAngleY = 0.0F;
		this.LegR.rotateAngleZ = 0.0F;
		this.LegL.rotateAngleZ = 0.0F;
		if (this.isSitting) {
			this.ArmR.rotateAngleX += (-(float) Math.PI / 5F);
			this.ArmL.rotateAngleX += (-(float) Math.PI / 5F);
			this.LegR.rotateAngleX = -1.4137167F;
			this.LegR.rotateAngleY = ((float) Math.PI / 10F);
			this.LegR.rotateAngleZ = 0.07853982F;
			this.LegL.rotateAngleX = -1.4137167F;
			this.LegL.rotateAngleY = (-(float) Math.PI / 10F);
			this.LegL.rotateAngleZ = -0.07853982F;
		}

		this.ArmR.rotateAngleY = 0.0F;
		this.ArmR.rotateAngleZ = 0.0F;
		switch (this.leftArmPose) {
		case EMPTY:
			this.ArmL.rotateAngleY = 0.0F;
			break;
		case ITEM:
			this.ArmL.rotateAngleX = this.ArmL.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
			this.ArmL.rotateAngleY = 0.0F;
		}

		switch (this.rightArmPose) {
		case EMPTY:
			this.ArmR.rotateAngleY = 0.0F;
		case ITEM:
			this.ArmR.rotateAngleX = this.ArmR.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
			this.ArmR.rotateAngleY = 0.0F;
			break;
		}

//			      if (this.swingProgress > 0.0F) {
//			         HandSide handside = this.getMainHand(entityIn);
//			         ModelRenderer modelrenderer = this.getArmForSide(handside);
//			         float f1 = this.swingProgress;
//			         this.Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;
//			         if (handside == HandSide.LEFT) {
//			            this.Body.rotateAngleY *= -1.0F;
//			         }
//
//			         this.ArmR.rotationPointZ = MathHelper.sin(this.Body.rotateAngleY) * 5.0F;
//			         this.ArmR.rotationPointX = -MathHelper.cos(this.Body.rotateAngleY) * 5.0F;
//			         this.ArmL.rotationPointZ = -MathHelper.sin(this.Body.rotateAngleY) * 5.0F;
//			         this.ArmL.rotationPointX = MathHelper.cos(this.Body.rotateAngleY) * 5.0F;
//			         this.ArmR.rotateAngleY += this.Body.rotateAngleY;
//			         this.ArmL.rotateAngleY += this.Body.rotateAngleY;
//			         this.ArmL.rotateAngleX += this.Body.rotateAngleY;
//			         f1 = 1.0F - this.swingProgress;
//			         f1 = f1 * f1;
//			         f1 = f1 * f1;
//			         f1 = 1.0F - f1;
//			         float f2 = MathHelper.sin(f1 * (float)Math.PI);
//			         float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.Head.rotateAngleX - 0.7F) * 0.75F;
//			         modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
//			         modelrenderer.rotateAngleY += this.Body.rotateAngleY * 2.0F;
//			         modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
//			      }

		this.ArmR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.ArmL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.ArmR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.ArmL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ModelRenderer getModelHead() {
		return this.Head;
	}

	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.Head);
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.Body, this.ArmR, this.ArmL, this.LegR, this.LegL);
	}

	@Override
	public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
		this.getArmForSide(sideIn).translateRotate(matrixStackIn);
	}

	protected ModelRenderer getArmForSide(HandSide side) {
		return side == HandSide.LEFT ? this.ArmL : this.ArmR;
	}

	protected HandSide getMainHand(T entityIn) {
		HandSide handside = entityIn.getPrimaryHand();
		return entityIn.swingingHand == Hand.MAIN_HAND ? handside : handside.opposite();
	}

	@OnlyIn(Dist.CLIENT)
	public static enum ArmPose {
		EMPTY, ITEM;
	}

	protected float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
		float f = (maxAngleIn - angleIn) % ((float) Math.PI * 2F);
		if (f < -(float) Math.PI) {
			f += ((float) Math.PI * 2F);
		}

		if (f >= (float) Math.PI) {
			f -= ((float) Math.PI * 2F);
		}

		return angleIn + mulIn * f;
	}
	
	   public void setModelAttributes(MatoranModel<T> modelIn) {
		      super.copyModelAttributesTo(modelIn);
		      modelIn.leftArmPose = this.leftArmPose;
		      modelIn.rightArmPose = this.rightArmPose;
		   }
	
	   public void setVisible(boolean visible) {
		      this.Head.showModel = visible;
		      this.Headwear.showModel = visible;
		      this.Body.showModel = visible;
		      this.ArmR.showModel = visible;
		      this.ArmL.showModel = visible;
		      this.LegR.showModel = visible;
		      this.LegL.showModel = visible;
		   }
}