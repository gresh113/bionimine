package com.github.gresh113.bionimine.client.entities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MatoranHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends HeldItemLayer<T, M> {
    public MatoranHeldItemLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwingIn, float limbSwingAmountIn, float partialTicksIn, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == HandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            matrixStackIn.push();
            this.renderItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStackIn, bufferIn, packedLightIn);
            this.renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.pop();
        }
    }
    @OnlyIn(Dist.CLIENT)
    private void renderItem(LivingEntity livingEntityIn, ItemStack stackIn, ItemCameraTransforms.TransformType transformsIn, HandSide handSideIn, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int combinedLightIn) {
        if (!stackIn.isEmpty()) {
            matrixStackIn.push();
            this.getEntityModel().translateHand(handSideIn, matrixStackIn);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-90.0F));
            //matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
            boolean flag = handSideIn == HandSide.LEFT;
           // matrixStackIn.translate((double)((float)(flag ? -1 : 1) / 12.0F) + (double)((float)(flag ? 1 : -1) * .2), -.40, -3.0D);
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(livingEntityIn, stackIn, transformsIn, flag, matrixStackIn, buffer, combinedLightIn);
            matrixStackIn.pop();
        }
    }
}
