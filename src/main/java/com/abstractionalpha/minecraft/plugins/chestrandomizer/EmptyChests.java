package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.manager.ChestRandomizer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Class containing functionality for emptying chests. 3 methods depending on method argument.
 *
 * @version 1.4
 * @author abstractionAlpha
 */
public class EmptyChests implements Listener {
    private final ChestRandomizer chestRandomizer;

    public EmptyChests(ChestRandomizer chestRandomizer) {
        this.chestRandomizer = chestRandomizer;
    }

    /**
     * Method that finds chest to empty.
     *
     * @param chestName Name of chest being emptied.
     */
    public void empty(String chestName) {
        ConfigurationSection chestPath;
        int x;
        int y;
        int z;

        chestPath = chestRandomizer.getConfig().getConfigurationSection("chests." + chestName);
        if (chestPath == null) {
            Bukkit.getLogger().info("No configuration section found at chests." + chestName);
            return;
        }

        String chest = chestPath.getCurrentPath();

        x = chestRandomizer.getConfig().getInt(chest + ".x");
        y = chestRandomizer.getConfig().getInt(chest + ".y");
        z = chestRandomizer.getConfig().getInt(chest + ".z");

        String world;
        Boolean manualWorld = chestRandomizer.getConfig().isSet(chest + ".world");
        if (manualWorld) {
            world = chestRandomizer.getConfig().getString(chest + ".world");
        } else {
            world = chestRandomizer.getConfig().getString("default-world");
        }

        World w = Bukkit.getWorld(world);
        Block b = w.getBlockAt(x, y, z);

        if(b != null && b.getType() == Material.CHEST) {
            Chest c = (Chest) b.getState();
            c.getInventory().clear();
        } else {
            Bukkit.getLogger().info("Chest " + chestName + " at " + x + " / " + y + " / " + z + " in world " + world + " not found.");
        }
    }

    /**
     * Method that finds chest to empty within specified radius.
     *
     * @param chestName Name of chest being emptied.
     * @param radius Radius to filter chests by.
     */
    public void empty(String chestName, Integer radius, CommandSender sender) {
        ConfigurationSection chestPath;
        int x;
        int y;
        int z;

        chestPath = chestRandomizer.getConfig().getConfigurationSection("chests." + chestName);
        if (chestPath == null) {
            Bukkit.getLogger().info("No configuration section found at chests." + chestName);
            return;
        }

        String chest = chestPath.getCurrentPath();

        x = chestRandomizer.getConfig().getInt(chest + ".x");
        y = chestRandomizer.getConfig().getInt(chest + ".y");
        z = chestRandomizer.getConfig().getInt(chest + ".z");

        String world;
        Boolean manualWorld = chestRandomizer.getConfig().isSet(chest + ".world");
        if (manualWorld) {
            world = chestRandomizer.getConfig().getString(chest + ".world");
        } else {
            world = chestRandomizer.getConfig().getString("default-world");
        }

        int radiusSquared = radius * radius;
        double distSquared;

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            distSquared = (x-loc.getX()) * (x-loc.getX()) + (z-loc.getZ()) * (z-loc.getZ());
        } else {
            distSquared = x * x + z * z;
        }

        if (distSquared <= radiusSquared) {

            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);

            if(b != null && b.getType() == Material.CHEST) {
                Chest c = (Chest) b.getState();
                c.getInventory().clear();
            } else {
                Bukkit.getLogger().info("Chest " + chestName + " at " + x + " / " + y + " / " + z + " in world " + world + " not found.");
            }

        }

    }

    /**
     * Method that finds chest to empty within specified region.
     *
     * @param chestName Name of chest being emptied.
     * @param region Region to filter chests by.
     */
    public void empty(String chestName, String region) {
        ConfigurationSection chestPath;
        int x;
        int y;
        int z;

        chestPath = chestRandomizer.getConfig().getConfigurationSection("chests." + chestName);
        if (chestPath == null) {
            Bukkit.getLogger().info("No configuration section found at chests." + chestName);
            return;
        }

        String chest = chestPath.getCurrentPath();

        x = chestRandomizer.getConfig().getInt(chest + ".x");
        y = chestRandomizer.getConfig().getInt(chest + ".y");
        z = chestRandomizer.getConfig().getInt(chest + ".z");

        String world;
        Boolean manualWorld = chestRandomizer.getConfig().isSet(chest + ".world");
        if (manualWorld) {
            world = chestRandomizer.getConfig().getString(chest + ".world");
        } else {
            world = chestRandomizer.getConfig().getString("default-world");
        }

        String regionFound = chestRandomizer.getConfig().getString(chest + ".region");

        if (regionFound.equals(region)) {

            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);

            if(b != null && b.getType() == Material.CHEST) {
                Chest c = (Chest) b.getState();
                c.getInventory().clear();
            } else {
                Bukkit.getLogger().info("Chest " + chestName + " at " + x + " / " + y + " / " + z + " in world " + world + " not found.");
            }

        }

    }

}
