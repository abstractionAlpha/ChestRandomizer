package com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.Region;

import java.util.ArrayList;

public class RegionDirectory {

    private ArrayList<Region> dir;

    public RegionDirectory() {
        dir = new ArrayList<Region>();
    }

    public void addRegionToDirectory(Region region) throws IllegalArgumentException {
        if (assertExistsInDirectory(region)) {
            throw new IllegalArgumentException("A region of this name already exists.");
        }

        dir.add(region);
    }

    public void removeRegionFromDirectory(Region region) throws IllegalArgumentException {
        if (!assertExistsInDirectory(region)) {
            throw new IllegalArgumentException("A region of this name does not exist.");
        }

        dir.remove(region);
    }

    public Region getRegionByName(String name) {
        if (name == null) {
            return null;
        }

        for (int i = 0; i < dir.size(); i++) {
            if (name.equals(dir.get(i).getName())) {
                return dir.get(i);
            }
        }

        Region toAdd = new Region(name);
        dir.add(toAdd);
        return toAdd;
    }

    public ArrayList<Region> getDirectory() {
        return dir;
    }

    private boolean assertExistsInDirectory(Region region) {
        for (int i = 0; i < dir.size(); i++) {
            if (region.getName().equals(dir.get(i).getName())) {
                return true;
            }
        }

        return false;
    }

}
