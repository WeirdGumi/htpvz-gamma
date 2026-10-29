package net.budoumiao.htpvzy.client.event;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.api.capability.Sun;
import net.budoumiao.htpvzy.common.capability.Capabilities;
import net.budoumiao.htpvzy.util.RenderUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;

import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constants.ID)
public class RenderGameOverlayHandler {
    @SubscribeEvent
    public static void onRenderGameOverlayPost(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
            Minecraft minecraft = Minecraft.getMinecraft();
            Entity entity = minecraft.getRenderViewEntity();
            if (entity != null) {
                Sun sun = entity.getCapability(Capabilities.SUN, null);
                if (sun != null) {
                    ScaledResolution resolution = event.getResolution();
                    TextureManager manager = minecraft.getTextureManager();
                    int x = resolution.getScaledWidth() / 2 + 91;
                    int y = resolution.getScaledHeight() - GuiIngameForge.right_height;
                    int z = (int) minecraft.ingameGUI.zLevel;
                    if (sun.getMax() > 0) {
                        RenderUtils.drawSun(x, y, z, sun.get() / 25, sun.getMax() / 25, true);
                        GuiIngameForge.right_height += 10;
                        manager.bindTexture(Gui.ICONS);
                    }
                }
            }
        }
    }
}
