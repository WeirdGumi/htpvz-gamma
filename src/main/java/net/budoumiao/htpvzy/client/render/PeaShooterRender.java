package net.budoumiao.htpvzy.client.render;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.client.model.PeaShooterModel;
import net.budoumiao.htpvzy.common.entity.PeaShooter;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class PeaShooterRender extends RenderLiving<PeaShooter> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.ID, "textures/entity/pea_shooter.png");

    public PeaShooterRender(RenderManager manager) {
        super(manager, new PeaShooterModel(), 0.3125f);
    }

    @Override
    protected ResourceLocation getEntityTexture(PeaShooter entity) {
        return TEXTURE;
    }
}
