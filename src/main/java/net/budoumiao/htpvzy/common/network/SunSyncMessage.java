package net.budoumiao.htpvzy.common.network;

import io.netty.buffer.ByteBuf;

import net.budoumiao.htpvzy.api.capability.Sun;
import net.budoumiao.htpvzy.common.capability.Capabilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SunSyncMessage implements IMessage {
    private Type type;
    private int value;

    public SunSyncMessage() {}

    public SunSyncMessage(Type type, int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.type = Type.values()[buffer.readByte()];
        this.value = ByteBufUtils.readVarInt(buffer, 5);
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeByte(this.type.ordinal());
        ByteBufUtils.writeVarInt(buffer, this.value, 5);
    }

    public enum Type { MIN, MAX, VALUE }

    public enum Handler implements IMessageHandler<SunSyncMessage, IMessage> {
        INSTANCE;

        @Override
        public IMessage onMessage(SunSyncMessage message, MessageContext context) {
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                EntityPlayer player = minecraft.player;
                Sun sun = player.getCapability(Capabilities.SUN, null);
                if (sun != null) {
                    switch (message.type) {
                        case VALUE -> sun.set(message.value);
                        case MIN -> sun.setMin(message.value);
                        case MAX -> {
                            sun.setMax(message.value);
                            player.sendMessage(new TextComponentString(String.valueOf(sun.getMax())));
                        }
                    }
                }
            });
            return null;
        }
    }
}
