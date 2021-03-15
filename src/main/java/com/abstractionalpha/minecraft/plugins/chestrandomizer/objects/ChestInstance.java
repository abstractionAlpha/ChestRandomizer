package com.abstractionalpha.minecraft.plugins.chestrandomizer.objects;

import org.bukkit.World;

public class ChestInstance {

    // Unique ID
    private String name;

    private Tier tier;

    private Region region;

    private int[] coordinates;

    private World world;

    public ChestInstance(String name, Tier tier, Region region, int x, int y, int z, World world) {
        this.name = name;
        setTier(tier);
        setRegion(region);
        setCoordinates(x, y, z);
    }

    public ChestInstance(String name, Tier tier, int x, int y, int z, World world) {
        this(name, tier, null, x, y, z, world);
    }

    public String getName() {
        return this.name;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    // We use individual int inputs so that the method is as literal as possible when converting to CLI
    public void setCoordinates(int x, int y, int z) {
        int[] array = new int[3];
        array[0] = x;
        array[1] = y;
        array[2] = z;
        coordinates = array;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
