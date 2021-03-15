package com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.Tier;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

public class TierDirectory {

    private ArrayList<Tier> dir;

    public TierDirectory() {
        dir = new ArrayList<Tier>();
    }

    public void addTierToDirectory(Tier tier) throws IllegalArgumentException {
        if (assertExistsInDirectory(tier)) {
            throw new IllegalArgumentException("A tier of this name already exists.");
        }

        dir.add(tier);
    }

    public void removeTierFromDirectory(Tier tier) throws IllegalArgumentException {
        if (!assertExistsInDirectory(tier)) {
            throw new IllegalArgumentException("A tier of this name does not exist.");
        }

        dir.remove(tier);
    }

    public Tier getTierByName(String name) throws NoSuchObjectException {
        if (name == null) {
            throw new NoSuchObjectException("A tier of this name does not exist.");
        }

        for (int i = 0; i < dir.size(); i++) {
            if (name.equals(dir.get(i).getName())) {
                dir.get(i);
            }
        }

        throw new NoSuchObjectException("A tier of this name does not exist.");
    }

    public ArrayList<Tier> getDirectory() {
        return dir;
    }

    private boolean assertExistsInDirectory(Tier tier) {
        for (int i = 0; i < dir.size(); i++) {
            if (tier.getName().equals(dir.get(i).getName())) {
                return true;
            }
        }

        return false;
    }

}
