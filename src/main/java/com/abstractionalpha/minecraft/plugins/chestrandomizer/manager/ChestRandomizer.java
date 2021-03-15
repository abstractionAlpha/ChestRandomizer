package com.abstractionalpha.minecraft.plugins.chestrandomizer.manager;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.io.ConfigReader;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.io.ConfigWriter;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.ChestDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.RegionDirectory;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.object_stores.TierDirectory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class containing executables. Handles command processing and user interface.
 *
 * @version 2.0
 * @author abstractionAlpha
 */
public final class ChestRandomizer extends JavaPlugin {

    /** Singleton instance of ChestRandomizer plugin */
    private static final ChestRandomizer CHEST_RANDOMIZER = new ChestRandomizer();

    /** Private ChestDirectory object containing all of the chests loaded from the config. */
    public ChestDirectory chests;

    /** Private RegionDirectory object containing all of the regions found in the config. */
    public RegionDirectory regions;

    /** Private TierDirectory object containing all of the tiers found in the config. */
    public TierDirectory tiers;

    /** Private ChestRandomizer constructor allowing for only 1 creation of ChestRandomizer */
    private ChestRandomizer() {
        chests = new ChestDirectory();
        regions = new RegionDirectory();
        tiers = new TierDirectory();
    }

    /**
     * Static method returning singleton instance of ChestRandomizer
     *
     * @return singleton object
     */
    public static ChestRandomizer getInstance() {
        return CHEST_RANDOMIZER;
    }

    /**
     * Plugin start-up logic.
     */
    @Override
    public void onEnable() {
        ConfigReader.loadChestRandomizer(chests, regions, tiers);
        getCommand("cr").setExecutor(new ChestRandomizerCommandExecutor());
        saveDefaultConfig();
    }

    /**
     * Plugin shut-down logic.
     */
    @Override
    public void onDisable() {
        ConfigWriter.saveChestRandomizer();
        saveConfig();
    }
}
