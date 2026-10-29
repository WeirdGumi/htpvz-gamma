package net.budoumiao.htpvzy.common.capability;

import net.budoumiao.htpvzy.api.capability.Sun;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class Capabilities {
    @CapabilityInject(Sun.class)
    public static Capability<Sun> SUN = null;

    private Capabilities() {}
}
