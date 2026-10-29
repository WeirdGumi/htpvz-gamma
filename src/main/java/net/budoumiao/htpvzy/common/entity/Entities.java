package net.budoumiao.htpvzy.common.entity;

import net.budoumiao.htpvzy.Constants;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class Entities {
    private static int index = 0;

    public static final EntityEntry PEA = EntityEntryBuilder.create().entity(Pea.class).id(new ResourceLocation(Constants.ID, "pea"), index++).name(String.format("%s.%s", Constants.ID, "pea")).tracker(64, 20, false).build();
    public static final EntityEntry PEA_SHOOTER = EntityEntryBuilder.create().entity(PeaShooter.class).id(new ResourceLocation(Constants.ID, "pea_shooter"), index++).name(String.format("%s.%s", Constants.ID, "pea_shooter")).tracker(80, 3, true).build();
}
