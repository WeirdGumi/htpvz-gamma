package net.budoumiao.htpvzy.common.entity;

import io.netty.buffer.ByteBuf;

import net.budoumiao.htpvzy.common.item.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBreaking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class Pea extends Entity implements IProjectile, IEntityAdditionalSpawnData {
    private Entity shooter;

    public Pea(World world) {
        super(world);
        this.setSize(0.25f, 0.25f);
    }

    public Pea(World world, Entity shooter) {
        this(world);
        this.shooter = shooter;
        this.setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight() - this.height / 2, shooter.posZ);
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.world.isRemote) {
            RayTraceResult result = ProjectileHelper.forwardsRaycast(
                    this,
                    true,
                    false,
                    this.shooter
            );
            if (result != null && !(result.entityHit instanceof PeaShooter)) this.onImpact(result);
        }
        if (this.isInWater()) {
            if (this.world.isRemote) {
                for (int i = 0; i < 4; i++) {
                    this.world.spawnParticle(
                            EnumParticleTypes.WATER_BUBBLE,
                            this.posX - this.motionX * 0.25,
                            this.posY - this.motionY * 0.25,
                            this.posZ - this.motionZ * 0.25,
                            this.motionX, this.motionY, this.motionZ
                    );
                }
            }
        }
        this.setPosition(
                this.posX + this.motionX,
                this.posY + this.motionY,
                this.posZ + this.motionZ
        );
        ProjectileHelper.rotateTowardsMovement(this, 0.2f);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {}

    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; i++) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleBreaking(
                        this.world,
                        this.posX, this.posY, this.posZ,
                        Items.PEA
                ));
            }
        }
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.5f;
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float magnitude = MathHelper.sqrt(x * x + y * y + z * z);
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        if (inaccuracy > 0) {
            x += this.rand.nextGaussian() * 0.0075 * inaccuracy;
            y += this.rand.nextGaussian() * 0.0075 * inaccuracy;
            z += this.rand.nextGaussian() * 0.0075 * inaccuracy;
        }
        this.motionX = x * velocity;
        this.motionY = y * velocity;
        this.motionZ = z * velocity;
        this.rotationYaw = (float) Math.toDegrees(MathHelper.atan2(z, x)) + 90;
        this.rotationPitch = (float) -Math.toDegrees(Math.asin(y));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeDouble(this.motionX);
        buffer.writeDouble(this.motionY);
        buffer.writeDouble(this.motionZ);
        buffer.writeFloat(this.rotationYaw);
        buffer.writeFloat(this.rotationPitch);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        this.motionX = buffer.readDouble();
        this.motionY = buffer.readDouble();
        this.motionZ = buffer.readDouble();
        this.rotationYaw = buffer.readFloat();
        this.rotationPitch = buffer.readFloat();
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    public void onImpact(RayTraceResult result) {
        int savedHurtResistantTime = 0;
        if (result.entityHit != null) {
            if (result.entityHit instanceof EntityLivingBase entity && entity.hurtResistantTime > 0) {
                savedHurtResistantTime = entity.hurtResistantTime;
                entity.hurtResistantTime = 0;
            }
            result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("pea", this, this.shooter).setProjectile(), 5);
            if (result.entityHit instanceof EntityLivingBase entity && savedHurtResistantTime > 0) {
                entity.hurtResistantTime = savedHurtResistantTime;
            }
        }
        this.world.setEntityState(this, (byte) 3);
        this.setDead();
    }
}
