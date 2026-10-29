package net.budoumiao.htpvzy.common.network;

import net.budoumiao.htpvzy.Constants;

import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class NetworkHandler {
    private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.ID);
    private static int index = 0;

    public static void register() {
        INSTANCE.registerMessage(SunSyncMessage.Handler.INSTANCE, SunSyncMessage.class, index++, Side.CLIENT);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }

    private NetworkHandler() {}
}
