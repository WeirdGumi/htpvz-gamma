package net.budoumiao.htpvzy.common.capability;

import net.budoumiao.htpvzy.api.capability.Sun;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import org.jspecify.annotations.NonNull;

public class SunCapability implements Sun {
    private int min;
    private int max;
    private int value;

    @Override
    public int get() {
        return this.value;
    }

    @Override
    public int getMin() {
        return this.min;
    }

    @Override
    public int getMax() {
        return this.max;
    }

    @Override
    public void set(int value) {
        if (value < this.min || value > this.max) throw new IllegalArgumentException();
        this.value = value;
    }

    @Override
    public void setMin(int min) {
        if (min > 0) throw new IllegalArgumentException();
        this.min = min;
    }

    @Override
    public void setMax(int max) {
        if (max < 0) throw new IllegalArgumentException();
        this.max = max;
    }

    @Override
    public void add(int value) {
        this.value += value;
    }

    public enum Storage implements Capability.IStorage<Sun> {
        INSTANCE;

        @Override
        public NBTBase writeNBT(Capability<Sun> capability, Sun instance, EnumFacing side) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("min", instance.getMin());
            tag.setInteger("max", instance.getMax());
            tag.setInteger("value", instance.get());
            return tag;
        }

        @Override
        public void readNBT(Capability<Sun> capability, Sun instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound tag) {
                instance.setMin(tag.getInteger("min"));
                instance.setMax(tag.getInteger("max"));
                instance.set(tag.getInteger("value"));
            }
        }
    }

    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {
        private final Sun instance;

        public Provider(int value, int min, int max) {
            this.instance = new SunCapability();
            this.instance.set(value);
            this.instance.setMin(min);
            this.instance.setMax(max);
        }

        @Override
        public boolean hasCapability(@NonNull Capability<?> capability, EnumFacing facing) {
            return capability == Capabilities.SUN;
        }

        @Override
        public <T> T getCapability(@NonNull Capability<T> capability, EnumFacing facing) {
            return capability == Capabilities.SUN ? Capabilities.SUN.cast(this.instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return Capabilities.SUN.writeNBT(this.instance, null) instanceof NBTTagCompound tag ? tag : null;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            Capabilities.SUN.readNBT(this.instance, null, nbt);
        }
    }
}
