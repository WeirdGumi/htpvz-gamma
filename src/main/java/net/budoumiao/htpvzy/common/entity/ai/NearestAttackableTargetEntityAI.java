package net.budoumiao.htpvzy.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class NearestAttackableTargetEntityAI extends EntityAIBase {
    private final EntityLiving host;

    public NearestAttackableTargetEntityAI(EntityLiving host) {
        this.host = host;
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }
}
