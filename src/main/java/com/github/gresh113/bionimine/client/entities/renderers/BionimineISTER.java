package com.github.gresh113.bionimine.client.entities.renderers;

import com.github.gresh113.bionimine.client.entities.models.items.FireStaffModel;
import com.github.gresh113.bionimine.objects.items.StaffItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BionimineISTER extends ItemStackTileEntityRenderer{
	 private final FireStaffModel model = new FireStaffModel();
	 
	@Override
	public void func_239207_a_(ItemStack stack, TransformType transform, MatrixStack matrixstack, IRenderTypeBuffer buffer, int p_239207_5_, int p_239207_6_) {
		Item item = stack.getItem();
		if (item instanceof StaffItem) {
			matrixstack.push();
            matrixstack.scale(1.0F, -1.0F, -1.0F);
            IVertexBuilder ivertexbuilder1 = ItemRenderer.func_239391_c_(buffer, this.model.getRenderType(FireStaffModel.TEXTURE_LOCATION), false, stack.hasEffect());
            this.model.render(matrixstack, ivertexbuilder1, p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixstack.pop();
		}
	}

}
