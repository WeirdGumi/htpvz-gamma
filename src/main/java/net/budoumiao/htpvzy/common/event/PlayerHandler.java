package net.budoumiao.htpvzy.common.event;

import net.budoumiao.htpvzy.Constants;
import net.budoumiao.htpvzy.api.capability.Sun;
import net.budoumiao.htpvzy.common.capability.Capabilities;
import net.budoumiao.htpvzy.common.network.NetworkHandler;
import net.budoumiao.htpvzy.common.network.SunSyncMessage;

import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Constants.ID)
public final class PlayerHandler {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP player) {
            Sun sun = player.getCapability(Capabilities.SUN, null);
            if (sun != null) {
                NetworkHandler.sendTo(new SunSyncMessage(SunSyncMessage.Type.MIN, sun.getMin()), player);
                NetworkHandler.sendTo(new SunSyncMessage(SunSyncMessage.Type.MAX, sun.getMax()), player);
                NetworkHandler.sendTo(new SunSyncMessage(SunSyncMessage.Type.VALUE, sun.get()), player);
            }
        }
    }

    public PlayerHandler() {}
}
