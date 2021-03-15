package com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.ChestInstance;

import java.util.ArrayList;

public class ChestDirectory {

    private ArrayList<ChestInstance> dir;

    public ChestDirectory() {
        dir = new ArrayList<ChestInstance>();
    }

    public void addChestToDirectory(ChestInstance chest) throws IllegalArgumentException {
        if (assertExistsInDirectory(chest)) {
            throw new IllegalArgumentException("A chest of this name already exists.");
        }

        dir.add(chest);
    }

    public void removeChestFromDirectory(ChestInstance chest) throws IllegalArgumentException {
        if (!assertExistsInDirectory(chest)) {
            throw new IllegalArgumentException("A chest of this name does not exist.");
        }

        dir.remove(chest);
    }

    public ArrayList<ChestInstance> getDirectory() {
        return dir;
    }

    private boolean assertExistsInDirectory(ChestInstance chest) {
        for (int i = 0; i < dir.size(); i++) {
            if (chest.getName().equals(dir.get(i).getName())) {
                return true;
            }
        }

        return false;
    }

}
