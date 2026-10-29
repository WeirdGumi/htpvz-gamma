package net.budoumiao.htpvzy.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PeaShooterModel extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leaf;

    public PeaShooterModel() {
        this.textureHeight = 64;
        this.head = new ModelRenderer(this).addBox(-5, -5, -5, 10, 10, 10)
                .setTextureOffset(0, 20).addBox(-2, 5, -2, 4, 3, 4);
        var hair = new ModelRenderer(this).addBox(-1, -0.5f, 0, 2, 1, 3)
                .setTextureOffset(0, 4).addBox(-2, -0.5f, 3, 4, 6, 0);
        hair.setRotationPoint(0, -0.5f, 5);
        hair.rotateAngleX = (float) Math.toRadians(10);
        this.head.addChild(hair);
        var mouth = new ModelRenderer(this, 40, 4).addBox(-2, 0, -9, 4, 4, 4)
                .setTextureOffset(40, 12).addBox(-3, -1, -9, 6, 6, 2);
        this.head.addChild(mouth);
        this.head.setRotationPoint(0, 7, 0);
        this.body = new ModelRenderer(this, 0, 27).addBox(-1, 12, -1, 2, 12, 2);
        this.leaf = new ModelRenderer(this);
        this.leaf.setRotationPoint(0, 24, 0);
        this.leaf.rotateAngleY = (float) Math.toRadians(45);
        for (int i = 0; i < 4; i++) {
            var leaf = new ModelRenderer(this, 2, 27).addBox(-2, 0, -7, 4, 0, 6);
            leaf.rotateAngleX = (float) Math.toRadians(-10);
            leaf.rotateAngleY = (float) Math.toRadians(i * 90);
            this.leaf.addChild(leaf);
        }
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.body.render(scale);
        this.leaf.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        this.head.rotateAngleX = (float) Math.toRadians(headPitch);
        this.head.rotateAngleY = (float) Math.toRadians(netHeadYaw);
    }
}
