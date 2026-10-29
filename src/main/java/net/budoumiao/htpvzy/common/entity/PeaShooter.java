package net.budoumiao.htpvzy.common.entity;

import net.budoumiao.htpvzy.common.entity.ai.RangedPeaAttackEntityAI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PeaShooter extends EntityCreature implements IRangedAttackMob {
    public PeaShooter(World world) {
        super(world);
        this.setSize(0.625f, 1.375f);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new RangedPeaAttackEntityAI<>(this));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 10, true, false, IMob.MOB_SELECTOR));
    }

    @Override
    public float getEyeHeight() {
        return this.height * (18.5f / 22);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        Pea pea = new Pea(this.world, this);
        float yaw = (float) Math.toRadians(this.rotationYawHead);
        float pitch = (float) Math.toRadians(this.rotationPitch);
        float sinYaw = MathHelper.sin(yaw);
        float cosYaw = MathHelper.cos(yaw);
        float cosPitch = MathHelper.cos(pitch);
        float x = -sinYaw * cosPitch;
        float y = -MathHelper.sin(pitch);
        float z =  cosYaw * cosPitch;
        float forward = 5f / 16 + pea.width / 2;
        float height = 3.5f / 16;
        pea.setPosition(
                pea.posX + x * forward,
                pea.posY - height + y * forward,
                pea.posZ + z * forward
        );
        pea.shoot(x, y, z, 1, 1);
        this.world.spawnEntity(pea);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {}
}
