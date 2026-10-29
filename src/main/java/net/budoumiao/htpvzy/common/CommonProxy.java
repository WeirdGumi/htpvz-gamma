package net.budoumiao.htpvzy.common;

import net.budoumiao.htpvzy.api.capability.Sun;
import net.budoumiao.htpvzy.common.capability.SunCapability;
import net.budoumiao.htpvzy.common.network.NetworkHandler;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(Sun.class, SunCapability.Storage.INSTANCE, SunCapability::new);
        NetworkHandler.register();
    }
}
