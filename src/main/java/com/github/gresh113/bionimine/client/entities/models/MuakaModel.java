package com.github.gresh113.bionimine.client.entities.models;// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;

public class MuakaModel<M extends MonsterEntity> extends EntityModel<M> {
    private final ModelRenderer Muaka_Body;
    private final ModelRenderer LegL;
    private final ModelRenderer LowerLegL;
    private final ModelRenderer Foot;
    private final ModelRenderer Foot2;
    private final ModelRenderer Claw;
    private final ModelRenderer LegL2;
    private final ModelRenderer LowerLegL2;
    private final ModelRenderer Foot3;
    private final ModelRenderer Foot4;
    private final ModelRenderer Claw2;
    private final ModelRenderer TreadSpineConnector;
    private final ModelRenderer Head2;
    private final ModelRenderer Spine;
    private final ModelRenderer LowerSpine;
    private final ModelRenderer MaskPoles;
    private final ModelRenderer Right;
    private final ModelRenderer FakeHeadR;
    private final ModelRenderer CorruptedMaskR;
    private final ModelRenderer Left;
    private final ModelRenderer FakeHeadL;
    private final ModelRenderer CorruptedMaskL;
    private final ModelRenderer Side_Left;
    private final ModelRenderer Side_Right;

    public MuakaModel() {
        textureWidth = 256;
        textureHeight = 128;

        Muaka_Body = new ModelRenderer(this);
        Muaka_Body.setRotationPoint(-3.0F, 24.0F, -2.0F);
        setRotationAngle(Muaka_Body, 0.0F, 1.5708F, 0.0F);
        Muaka_Body.setTextureOffset(110, 54).addBox(-35.0F, -28.0F, -3.0F, 36.0F, 6.0F, 4.0F, 0.0F, false);
        Muaka_Body.setTextureOffset(0, 89).addBox(-62.0F, -20.0F, -9.0F, 43.0F, 20.0F, 16.0F, 0.0F, false);
        Muaka_Body.setTextureOffset(0, 0).addBox(0.0F, -49.0F, -6.0F, 35.0F, 23.0F, 10.0F, 0.0F, false);
        Muaka_Body.setTextureOffset(118, 84).addBox(1.0F, -26.0F, -19.0F, 31.0F, 4.0F, 36.0F, 0.0F, false);
        Muaka_Body.setTextureOffset(0, 36).addBox(6.0F, -22.0F, -26.0F, 21.0F, 2.0F, 50.0F, 0.0F, false);

        LegL = new ModelRenderer(this);
        LegL.setRotationPoint(38.0F, -15.5F, -6.5F);
        Muaka_Body.addChild(LegL);
        LegL.setTextureOffset(179, 0).addBox(-28.0F, -4.5F, -19.5F, 12.0F, 11.0F, 9.0F, 0.0F, false);

        LowerLegL = new ModelRenderer(this);
        LowerLegL.setRotationPoint(-1.0F, 1.0F, -9.0F);
        LegL.addChild(LowerLegL);
        setRotationAngle(LowerLegL, 0.0F, 0.0F, 0.6981F);
        LowerLegL.setTextureOffset(178, 28).addBox(-17.1479F, 17.5685F, -9.5F, 19.0F, 4.0F, 7.0F, 0.0F, false);

        Foot = new ModelRenderer(this);
        Foot.setRotationPoint(23.3976F, -2.2863F, 2.0F);
        LowerLegL.addChild(Foot);
        setRotationAngle(Foot, 0.0F, 0.0F, -1.309F);
        Foot.setTextureOffset(221, 0).addBox(-28.6183F, -18.6373F, -11.5F, 9.0F, 4.0F, 7.0F, 0.0F, false);

        Foot2 = new ModelRenderer(this);
        Foot2.setRotationPoint(-7.7353F, -5.1658F, 0.0F);
        Foot.addChild(Foot2);
        setRotationAngle(Foot2, 0.0F, 0.0F, 1.2217F);
        Foot2.setTextureOffset(178, 39).addBox(-16.7233F, 6.5589F, -11.5F, 11.0F, 2.0F, 7.0F, 0.0F, false);

        Claw = new ModelRenderer(this);
        Claw.setRotationPoint(3.9611F, 0.6578F, 0.0F);
        Foot2.addChild(Claw);
        setRotationAngle(Claw, 0.0F, 0.0F, -0.9599F);
        Claw.setTextureOffset(214, 39).addBox(-19.3886F, -5.5483F, -11.5F, 9.0F, 1.0F, 7.0F, 0.0F, false);

        LegL2 = new ModelRenderer(this);
        LegL2.setRotationPoint(33.0F, -15.5F, 35.5F);
        Muaka_Body.addChild(LegL2);
        LegL2.setTextureOffset(179, 0).addBox(-23.0F, -4.5F, -20.5F, 12.0F, 11.0F, 9.0F, 0.0F, false);

        LowerLegL2 = new ModelRenderer(this);
        LowerLegL2.setRotationPoint(-1.0F, 1.0F, -9.0F);
        LegL2.addChild(LowerLegL2);
        setRotationAngle(LowerLegL2, 0.0F, 0.0F, 0.6981F);
        LowerLegL2.setTextureOffset(178, 28).addBox(-13.3176F, 14.3546F, -10.5F, 19.0F, 4.0F, 7.0F, 0.0F, false);

        Foot3 = new ModelRenderer(this);
        Foot3.setRotationPoint(23.3976F, -2.2863F, 2.0F);
        LowerLegL2.addChild(Foot3);
        setRotationAngle(Foot3, 0.0F, 0.0F, -1.309F);
        Foot3.setTextureOffset(221, 0).addBox(-24.5226F, -15.7694F, -12.5F, 9.0F, 4.0F, 7.0F, 0.0F, false);

        Foot4 = new ModelRenderer(this);
        Foot4.setRotationPoint(-7.7353F, -5.1658F, 0.0F);
        Foot3.addChild(Foot4);
        setRotationAngle(Foot4, 0.0F, 0.0F, 1.2217F);
        Foot4.setTextureOffset(178, 39).addBox(-12.6276F, 3.691F, -12.5F, 11.0F, 2.0F, 7.0F, 0.0F, false);

        Claw2 = new ModelRenderer(this);
        Claw2.setRotationPoint(3.9611F, 0.6578F, 0.0F);
        Foot4.addChild(Claw2);
        setRotationAngle(Claw2, 0.0F, 0.0F, -0.9599F);
        Claw2.setTextureOffset(214, 39).addBox(-14.6902F, -3.8382F, -12.5F, 9.0F, 1.0F, 7.0F, 0.0F, false);

        TreadSpineConnector = new ModelRenderer(this);
        TreadSpineConnector.setRotationPoint(-14.0F, -31.5F, -0.5F);
        Muaka_Body.addChild(TreadSpineConnector);
        setRotationAngle(TreadSpineConnector, 0.0F, 0.0F, 0.4363F);
        TreadSpineConnector.setTextureOffset(142, 87).addBox(-20.1262F, 0.9524F, -10.5F, 4.0F, 31.0F, 2.0F, 0.0F, false);
        TreadSpineConnector.setTextureOffset(142, 87).addBox(-20.1262F, 0.9524F, 7.5F, 4.0F, 31.0F, 2.0F, 0.0F, false);

        Head2 = new ModelRenderer(this);
        Head2.setRotationPoint(46.0F, -30.0F, -1.0F);
        Muaka_Body.addChild(Head2);
        setRotationAngle(Head2, 0.0F, 0.0F, 0.2618F);
        Head2.setTextureOffset(0, 33).addBox(-12.3185F, -13.8236F, -9.0F, 35.0F, 24.0F, 20.0F, 0.0F, false);
        Head2.setTextureOffset(90, 43).addBox(-11.3849F, -19.7112F, -11.0F, 8.0F, 8.0F, 2.0F, 0.0F, false);
        Head2.setTextureOffset(90, 43).addBox(-11.3849F, -19.7112F, 11.0F, 8.0F, 8.0F, 2.0F, 0.0F, false);

        Spine = new ModelRenderer(this);
        Spine.setRotationPoint(6.8681F, -41.7588F, 5.0F);
        Muaka_Body.addChild(Spine);
        setRotationAngle(Spine, 0.0F, 0.0F, -0.2618F);
        Spine.setTextureOffset(110, 64).addBox(-43.0773F, -7.2105F, -9.0F, 41.0F, 6.0F, 6.0F, 0.0F, false);

        LowerSpine = new ModelRenderer(this);
        LowerSpine.setRotationPoint(-17.0341F, 0.2588F, 0.0F);
        Spine.addChild(LowerSpine);
        setRotationAngle(LowerSpine, 0.0F, 0.0F, -0.0873F);
        LowerSpine.setTextureOffset(102, 92).addBox(-25.2939F, -9.8404F, -3.0F, 13.0F, 8.0F, 5.0F, 0.0F, false);
        LowerSpine.setTextureOffset(102, 92).addBox(-25.2939F, -9.8404F, -14.0F, 13.0F, 8.0F, 5.0F, 0.0F, false);

        MaskPoles = new ModelRenderer(this);
        MaskPoles.setRotationPoint(34.0F, -22.0F, 3.0F);
        Muaka_Body.addChild(MaskPoles);


        Right = new ModelRenderer(this);
        Right.setRotationPoint(0.0F, 0.0F, -4.0F);
        MaskPoles.addChild(Right);
        Right.setTextureOffset(110, 76).addBox(-28.0F, -4.0F, -22.0F, 51.0F, 4.0F, 4.0F, 0.0F, false);

        FakeHeadR = new ModelRenderer(this);
        FakeHeadR.setRotationPoint(51.0F, 6.0F, -25.5F);
        Right.addChild(FakeHeadR);
        setRotationAngle(FakeHeadR, 0.0F, -1.5708F, 0.0F);
        FakeHeadR.setTextureOffset(0, 0).addBox(2.0F, -14.0F, 20.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        CorruptedMaskR = new ModelRenderer(this);
        CorruptedMaskR.setRotationPoint(9.0F, -6.0F, -9.5F);
        FakeHeadR.addChild(CorruptedMaskR);
        CorruptedMaskR.setTextureOffset(44, 7).addBox(-8.0F, -9.0F, 29.0F, 10.0F, 9.0F, 0.0F, 0.0F, false);
        CorruptedMaskR.setTextureOffset(44, 0).addBox(-8.0F, -9.0F, 29.0F, 10.0F, 0.0F, 10.0F, 0.0F, false);
        CorruptedMaskR.setTextureOffset(44, 0).addBox(2.0F, -9.0F, 29.0F, 0.0F, 9.0F, 10.0F, 0.0F, false);
        CorruptedMaskR.setTextureOffset(44, 0).addBox(-8.0F, -9.0F, 29.0F, 0.0F, 9.0F, 10.0F, 0.0F, false);

        Left = new ModelRenderer(this);
        Left.setRotationPoint(0.0F, 0.0F, 52.0F);
        MaskPoles.addChild(Left);
        Left.setTextureOffset(110, 76).addBox(-28.0F, -4.0F, -38.0F, 51.0F, 4.0F, 4.0F, 0.0F, false);

        FakeHeadL = new ModelRenderer(this);
        FakeHeadL.setRotationPoint(51.0F, 6.0F, -25.5F);
        Left.addChild(FakeHeadL);
        setRotationAngle(FakeHeadL, 0.0F, -1.5708F, 0.0F);
        FakeHeadL.setTextureOffset(0, 0).addBox(-14.0F, -14.0F, 20.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        CorruptedMaskL = new ModelRenderer(this);
        CorruptedMaskL.setRotationPoint(9.0F, -6.0F, -9.5F);
        FakeHeadL.addChild(CorruptedMaskL);
        CorruptedMaskL.setTextureOffset(44, 7).addBox(-24.0F, -9.0F, 29.0F, 10.0F, 9.0F, 0.0F, 0.0F, false);
        CorruptedMaskL.setTextureOffset(44, 0).addBox(-24.0F, -9.0F, 29.0F, 10.0F, 0.0F, 10.0F, 0.0F, false);
        CorruptedMaskL.setTextureOffset(44, 0).addBox(-14.0F, -9.0F, 29.0F, 0.0F, 9.0F, 10.0F, 0.0F, false);
        CorruptedMaskL.setTextureOffset(44, 0).addBox(-24.0F, -9.0F, 29.0F, 0.0F, 9.0F, 10.0F, 0.0F, false);

        Side_Left = new ModelRenderer(this);
        Side_Left.setRotationPoint(36.0F, -43.0F, 15.0F);
        Muaka_Body.addChild(Side_Left);
        setRotationAngle(Side_Left, -0.9599F, 0.0F, 0.0F);
        Side_Left.setTextureOffset(91, 0).addBox(-35.0F, 6.1125F, -10.9273F, 31.0F, 2.0F, 26.0F, 0.0F, false);

        Side_Right = new ModelRenderer(this);
        Side_Right.setRotationPoint(36.0F, -43.0F, -7.0F);
        Muaka_Body.addChild(Side_Right);
        setRotationAngle(Side_Right, 0.9599F, 0.0F, 0.0F);
        Side_Right.setTextureOffset(91, 0).addBox(-35.0F, -2.079F, -20.8085F, 31.0F, 2.0F, 26.0F, 0.0F, false);}


    @Override
    public void setRotationAngles(M entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Muaka_Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}