package com.github.gresh113.bionimine.client.entities.models;

import java.util.function.Function;

import com.github.gresh113.bionimine.entities.turaga.TuragaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench 3.5.4
//Exported for Minecraft version 1.15
//Paste this class into your mod and generate all required imports

public class TuragaModel<T extends TuragaEntity> extends EntityModel<T> implements IHasArm, IHasHead {
	private final ModelRenderer Body;
	private final ModelRenderer Torso;
	private final ModelRenderer LegL;
	private final ModelRenderer Head;
	public final ModelRenderer Headwear;
	private final ModelRenderer ArmL;
	private final ModelRenderer LegR;
	private final ModelRenderer ArmR;

	public MatoranModel.ArmPose leftArmPose = MatoranModel.ArmPose.EMPTY;
	public MatoranModel.ArmPose rightArmPose = MatoranModel.ArmPose.EMPTY;

	public TuragaModel() {
		this(RenderType::getEntityCutoutNoCull, .5f, 0.0F, 64, 32);
	}

	protected TuragaModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
		this(RenderType::getEntityCutoutNoCull, modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
	}

	public TuragaModel(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
		textureWidth = 64;
		textureHeight = 40;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(4.0F, -8.0F, -2.0F);
		Body.addChild(Torso);
		Torso.setTextureOffset(16, 13).addBox(-8.0F, -8.0F, 0.0F, 8.0F, 11.0F, 4.0F, 0.0F, false);

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(2.0F, -7.0F, 0.0F);
		Body.addChild(LegL);
		LegL.setTextureOffset(41, 19).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
		
		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(-2.0F, -7.0F, 0.0F);
		Body.addChild(LegR);
		LegR.setTextureOffset(41, 19).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -16.0F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Headwear = new ModelRenderer(this);
		Headwear.setRotationPoint(0.0F, 0.0F + yOffsetIn, 0.0F);
		Body.addChild(Headwear);
		Headwear.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, modelSizeIn + 0.25F);

		ArmL = new ModelRenderer(this);
		ArmL.setRotationPoint(5.5F, -15.0F, 0.0F);
		Body.addChild(ArmL);
		ArmL.setTextureOffset(0, 16).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 7.0F, 4.0F, 0.0F, false);

		ArmR = new ModelRenderer(this);
		ArmR.setRotationPoint(-5.5F, -14.0F, 0.0F);
		Body.addChild(ArmR);
		setRotationAngle(ArmR, -1.5708F, 0.0F, 0.0F);
		ArmR.setTextureOffset(0, 28).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 7.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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

		this.ArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ModelRenderer getModelHead() {
		return Head;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
		ModelRenderer arm = this.getArmForSide(sideIn);
		//matrixStackIn.translate((double)(arm.rotationPointX) , (double)(arm.rotationPointY), (double)(arm.rotationPointZ));
		//Bionimine.LOGGER.info(arm.rotationPointX + " " + arm.rotationPointY + " " + arm.rotationPointZ);
		matrixStackIn.translate((double) (arm.rotationPointX/16) - .01, (double)(arm.rotationPointY/-16) - .01, 0D);
		if (arm.rotateAngleZ != 0.0F) {
			matrixStackIn.rotate(Vector3f.ZP.rotation(arm.rotateAngleZ));
		}

		if (arm.rotateAngleY != 0.0F) {
			matrixStackIn.rotate(Vector3f.YP.rotation(arm.rotateAngleY));
		}

		if (arm.rotateAngleX != 0.0F) {
			matrixStackIn.rotate(Vector3f.XP.rotation(arm.rotateAngleX));
		}
	}

	protected ModelRenderer getArmForSide(HandSide side) {
		return side == HandSide.LEFT ? this.ArmL : this.ArmR;
	}

}
