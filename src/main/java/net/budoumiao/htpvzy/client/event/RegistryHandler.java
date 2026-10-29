package net.budoumiao.htpvzy.client.event;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.client.render.PeaRender;
import net.budoumiao.htpvzy.client.render.PeaShooterRender;
import net.budoumiao.htpvzy.common.entity.Pea;
import net.budoumiao.htpvzy.common.entity.PeaShooter;
import net.budoumiao.htpvzy.common.item.Items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constants.ID)
public final class RegistryHandler {
    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(Pea.class, PeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PeaShooter.class, PeaShooterRender::new);
        ModelLoader.setCustomModelResourceLocation(Items.PEA, 0, new ModelResourceLocation(Items.PEA.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.PEA_SHOOTER, 0, new ModelResourceLocation(Items.PEA_SHOOTER.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.SEED_PACKET, 0, new ModelResourceLocation(Items.SEED_PACKET.getRegistryName(), "inventory"));
    }

    private RegistryHandler() {}
}
