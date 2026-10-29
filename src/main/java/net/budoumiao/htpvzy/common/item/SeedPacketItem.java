package net.budoumiao.htpvzy.common.item;

import net.budoumiao.htpvzy.api.capability.Sun;
import net.budoumiao.htpvzy.common.capability.Capabilities;
import net.budoumiao.htpvzy.common.network.NetworkHandler;
import net.budoumiao.htpvzy.common.network.SunSyncMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SeedPacketItem extends Item {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            Sun sun = player.getCapability(Capabilities.SUN, null);
            if (sun != null) {
                sun.setMax(player.isSneaking() ? sun.getMax() - 25 : sun.getMax() + 25);
                sun.add(player.isSneaking() ? -25 : 25);
                player.sendMessage(new TextComponentString(String.valueOf(sun.getMax())));
                if (player instanceof EntityPlayerMP playerMP) {
                    NetworkHandler.sendTo(new SunSyncMessage(SunSyncMessage.Type.MAX, sun.getMax()), playerMP);
                    NetworkHandler.sendTo(new SunSyncMessage(SunSyncMessage.Type.VALUE, sun.get()), playerMP);
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
