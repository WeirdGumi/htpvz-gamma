package net.budoumiao.htpvzy.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PeaModel extends ModelBase {
    private final ModelRenderer renderer;

    public PeaModel() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.renderer = new ModelRenderer(this).addBox(-2, -2, -2, 4, 4, 4);
        this.renderer.setRotationPoint(0, -2, 0);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.renderer.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        this.renderer.rotateAngleX = (float) Math.toRadians(headPitch);
        this.renderer.rotateAngleY = (float) Math.toRadians(netHeadYaw);
    }
}
