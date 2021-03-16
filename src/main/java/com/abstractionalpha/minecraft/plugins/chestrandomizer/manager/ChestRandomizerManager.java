package com.abstractionalpha.minecraft.plugins.chestrandomizer.manager;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.io.ConfigReader;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.ChestDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.RegionDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.TierDirectory;

public class ChestRandomizerManager {

    /** Singleton instance of ChestRandomizer plugin */
    private static final ChestRandomizerManager CHEST_RANDOMIZER = new ChestRandomizerManager();

    /** Private ChestDirectory object containing all of the chests loaded from the config. */
    public ChestDirectory chests;

    /** Private RegionDirectory object containing all of the regions found in the config. */
    public RegionDirectory regions;

    /** Private TierDirectory object containing all of the tiers found in the config. */
    public TierDirectory tiers;

    /** Private ChestRandomizer constructor allowing for only 1 creation of ChestRandomizer */
    private ChestRandomizerManager() {
        chests = new ChestDirectory();
        regions = new RegionDirectory();
        tiers = new TierDirectory();

        ConfigReader.loadChestRandomizer(chests, regions, tiers);
    }

    /**
     * Static method returning singleton instance of ChestRandomizer
     *
     * @return singleton object
     */
    public static ChestRandomizerManager getInstance() {
        return CHEST_RANDOMIZER;
    }

}
