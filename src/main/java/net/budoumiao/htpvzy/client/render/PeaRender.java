package net.budoumiao.htpvzy.client.render;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.client.model.PeaModel;
import net.budoumiao.htpvzy.common.entity.Pea;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class PeaRender extends Render<Pea> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.ID, "textures/entity/pea.png");
    private final ModelBase model = new PeaModel();

    public PeaRender(RenderManager manager) {
        super(manager);
        this.shadowSize = 0.125f;
    }

    @Override
    public void doRender(Pea entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(-1, -1, 1);
        this.bindEntityTexture(entity);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }
        float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        this.model.render(entity, 0, 0, 0, yaw, pitch, 0.0625f);
        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Pea entity) {
        return TEXTURE;
    }
}
