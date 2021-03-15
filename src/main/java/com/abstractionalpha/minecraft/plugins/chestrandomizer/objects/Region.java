package com.abstractionalpha.minecraft.plugins.chestrandomizer.objects;

import java.util.ArrayList;

public class Region {

    private String name;

    private ArrayList<ChestInstance> chests;

    public Region(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addChestToRegion(ChestInstance chest) throws IllegalArgumentException {
        if (assertExistsInChests(chest)) {
            throw new IllegalArgumentException("This chest is already set to this region.");
        }

        chests.add(chest);
        chest.setRegion(getName());
    }

    public void removeChestFromRegion(ChestInstance chest) throws IllegalArgumentException {
        if (!assertExistsInChests(chest)) {
            throw new IllegalArgumentException("This chest is not defined in this region.");
        }

        chests.remove(chest);
        chest.setRegion(null);
    }

    private boolean assertExistsInChests(ChestInstance chest) {
        for (int i = 0; i < chests.size(); i++) {
            if (chest.getName().equals(chests.get(i).getName())) {
                return true;
            }
        }

        return false;
    }
}
