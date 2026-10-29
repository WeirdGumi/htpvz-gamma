package net.budoumiao.htpvzy.common.event;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.common.entity.Entities;
import net.budoumiao.htpvzy.common.item.Items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber(modid = Constants.ID)
public final class RegistryHandler {
    @SubscribeEvent
    public static void onEntityRegister(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(Entities.PEA);
        event.getRegistry().register(Entities.PEA_SHOOTER);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(Items.PEA.setRegistryName(new ResourceLocation(Constants.ID, "pea")));
        event.getRegistry().register(Items.PEA_SHOOTER.setRegistryName(new ResourceLocation(Constants.ID, "pea_shooter")));
        event.getRegistry().register(Items.SEED_PACKET.setRegistryName(new ResourceLocation(Constants.ID, "seed_packet")));
    }

    private RegistryHandler() {}
}
