package com.abstractionalpha.minecraft.plugins.chestrandomizer.objects;

import org.bukkit.Material;

import java.util.ArrayList;

public class Tier {

    private String name;

    // Minimum amount of items generated
    private int min;

    // Maximum amount of items generated
    private int max;

    // Maximum number of items to stack (for legal items)
    private int stackLimit;

    // Items that this tier might output
    private ArrayList<Material> vanillaItems;

    public Tier(String name, int min, int max, int stackLimit) {
        setName(name);
        setMin(min);
        setMax(max);
        setStackLimit(stackLimit);
        vanillaItems = new ArrayList<Material>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStackLimit() {
        return stackLimit;
    }

    public void setStackLimit(int stackLimit) {
        this.stackLimit = stackLimit;
    }

    public void addMaterialToTier(Material type) throws IllegalArgumentException {
         if (assertExistsInTier(type)) {
             throw new IllegalArgumentException("This Material is already in the Tier.");
         }

         vanillaItems.add(type);
    }

    public void removeMaterialFromTier(Material type) throws IllegalArgumentException {
        if (!assertExistsInTier(type)) {
            throw new IllegalArgumentException("This Material was not found in the Tier.");
        }

        vanillaItems.remove(type);
    }

    private boolean assertExistsInTier(Material type) {
        for (int i = 0; i < vanillaItems.size(); i++) {
            if (vanillaItems.get(i).equals(type)) {
                return true;
            }
        }

        return false;
    }

    private class RandomTier {
        // TODO: Implement random tier
    }
}
