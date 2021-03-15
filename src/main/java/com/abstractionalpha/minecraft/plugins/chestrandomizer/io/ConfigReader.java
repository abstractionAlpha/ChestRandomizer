package com.abstractionalpha.minecraft.plugins.chestrandomizer.io;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.manager.ChestRandomizer;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.ChestDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.RegionDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.TierDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.ChestInstance;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.Region;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.Tier;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

public class ConfigReader {

    private static FileConfiguration config;

    public static void loadChestRandomizer(ChestDirectory chests, RegionDirectory regions, TierDirectory tiers) {
        config = ChestRandomizer.getInstance().getConfig();

        createTierDirectory((ArrayList<String>) config.getStringList("tiers"), tiers);
        createChestDirectory((ArrayList<String>) config.getStringList("chests"), chests, regions, tiers);
    }

    private static void createChestDirectory(ArrayList<String> names, ChestDirectory chests,
                                             RegionDirectory regions, TierDirectory tiers) {
        ChestInstance toAdd;

        String name;
        int x;
        int y;
        int z;
        Tier tier;
        Region region;
        World world;

        while (names.size() > 0) {
            name = names.get(0);
            names.remove(0);
            x = config.getInt("chests." + name + ".x");
            y = config.getInt("chests." + name + ".y");
            z = config.getInt("chests." + name + ".z");
            region = regions.getRegionByName(config.getString("chests." + name + ".region", null));

            try {
                tier = tiers.getTierByName(config.getString("chests." + name + ".tier"));
            } catch (NoSuchObjectException e) {
                // TODO: Call error-handling method somewhere
                tier = null;
                continue;
            }

            try {
                world = ChestRandomizer.getInstance().getServer().getWorld(config.getString("chests." + name + ".world"));
            } catch (NullPointerException e) {
                world = ChestRandomizer.getInstance().getServer().getWorlds().get(0);
            }

            toAdd = new ChestInstance(name, tier, region, x, y, z, world);
            chests.addChestToDirectory(toAdd);
        }
    }

    private static void createTierDirectory(ArrayList<String> names, TierDirectory tiers) {
        Tier toAdd;

        String name;
        int min;
        int max;
        int stackLimit;
        ArrayList<String> typeNames;

        while (names.size() > 0) {
            name = names.get(0);
            names.remove(0);

            typeNames = (ArrayList<String>) config.getStringList("tiers." + name + ".items");

            min = config.getInt("tiers." + name + ".min-items", 1);
            max = config.getInt("tiers." + name + ".min-items", typeNames.size());

            stackLimit = config.getInt("tiers." + name + ".stack-limit", config.getInt("default-stack-limit"));

            toAdd = new Tier(name, min, max, stackLimit);

            for (String typeName : typeNames) {
                toAdd.addMaterialToTier(Material.getMaterial(typeName));
            }

            tiers.addTierToDirectory(toAdd);
        }
    }
}
