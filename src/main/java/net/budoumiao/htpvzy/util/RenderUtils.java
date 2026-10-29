package net.budoumiao.htpvzy.util;

import net.budoumiao.htpvzy.Constants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public final class RenderUtils {
    public static final ResourceLocation ICONS = new ResourceLocation(Constants.ID, "textures/gui/icons.png");

    public static void drawSun(int startX, int y, int z, int value, int max, boolean left) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(ICONS);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        int count = (max + 3) / 4;
        int fullCount = value / 4;
        int partial = value % 4;
        int offset = left ? 9 : 0;
        int direction = left ? 8 : -8;
        for (int i = 0; i < count; i++) {
            int x = startX - offset - i * direction;
            RenderUtils.quad(buffer, x, y, z, 0, 0, 9, 9);
            if (i < fullCount) {
                RenderUtils.quad(buffer, x, y, z, 27, left ? 18 : 9, 9, 9);
            } else if (i == fullCount && partial > 0) {
                RenderUtils.quad(buffer, x, y, z, (partial - 1) * 9, left ? 18 : 9, 9, 9);
            }
        }
        tessellator.draw();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
    }

    public static void quad(BufferBuilder buffer, int x, int y, int z, int u, int v, int width, int height) {
        float u0 = u / 64f;
        float v0 = v / 64f;
        float u1 = (u + width) / 64f;
        float v1 = (v + height) / 64f;
        buffer.pos(x, y + height, z).tex(u0, v1).endVertex();
        buffer.pos(x + width, y + height, z).tex(u1, v1).endVertex();
        buffer.pos(x + width, y, z).tex(u1, v0).endVertex();
        buffer.pos(x, y, z).tex(u0, v0).endVertex();
    }

    private RenderUtils() {}
}
