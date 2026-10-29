package net.budoumiao.htpvzy.common.event;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.common.capability.SunCapability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constants.ID)
public final class AttachCapabilitiesHandler {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation(Constants.ID, "sun"), new SunCapability.Provider(0, 0, 300));
        }
    }

    private AttachCapabilitiesHandler() {}
}
