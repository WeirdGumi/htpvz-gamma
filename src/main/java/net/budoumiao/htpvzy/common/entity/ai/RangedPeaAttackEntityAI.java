package net.budoumiao.htpvzy.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class RangedPeaAttackEntityAI<T extends EntityLiving & IRangedAttackMob> extends EntityAIBase {
    private final T attacker;
    private EntityLivingBase target;
    private int cooldown = -1;

    public RangedPeaAttackEntityAI(T attacker) {
        this.attacker = attacker;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute() {
        this.target = this.attacker.getAttackTarget();
        return this.target != null;
    }

    @Override
    public void resetTask() {
        this.target = null;
        this.cooldown = -1;
    }

    @Override
    public void updateTask() {
        boolean canSee = this.attacker.getEntitySenses().canSee(this.target);
        if (canSee) {
            this.attacker.getLookHelper().setLookPositionWithEntity(this.target, 30, 30);
        }
        if (--this.cooldown == 0) {
            if (!canSee) {
                return;
            }
            double x = this.target.posX - this.attacker.posX;
            double z = this.target.posZ - this.attacker.posZ;
            double y = this.target.posY + this.target.getEyeHeight() - this.attacker.posY - this.attacker.getEyeHeight();
            float targetYaw = (float) Math.toDegrees(MathHelper.atan2(z, x)) - 90;
            float targetPitch = (float) -Math.toDegrees(MathHelper.atan2(y, MathHelper.sqrt(x * x + z * z)));
            if (
                    MathHelper.abs(MathHelper.wrapDegrees(targetYaw - this.attacker.rotationYawHead)) <= 10
                            && MathHelper.abs(MathHelper.wrapDegrees(targetPitch - this.attacker.rotationPitch)) <= 10
            ) {
                this.attacker.attackEntityWithRangedAttack(this.target, 0);
                this.cooldown = 20;
            }
        } else if (this.cooldown < 0) {
            this.cooldown = 20;
        }
    }
}
