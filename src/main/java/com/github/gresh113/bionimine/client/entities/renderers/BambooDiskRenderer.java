package com.github.gresh113.bionimine.client.entities.renderers;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.client.entities.models.KanokaDiskModel;
import com.github.gresh113.bionimine.entities.KanokaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BambooDiskRenderer<T extends KanokaEntity> extends EntityRenderer<T> {
	protected final ResourceLocation TEXTURE = new ResourceLocation(Bionimine.MODID, "textures/entity/bamboo_disk.png");
	protected final KanokaDiskModel modelKanoka = new KanokaDiskModel();

	public BambooDiskRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		// matrixStackIn.push();
		// matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		// this.modelKanoka.setRotationAngles(entityIn, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelKanoka.getRenderType(TEXTURE));
		this.modelKanoka.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		// matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}
}
