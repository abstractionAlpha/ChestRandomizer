package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.manager.ChestRandomizer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class containing functionality for filling chests. 3 main methods depending on method argument and two for function.
 *
 * @version 1.4
 * @author abstractionAlpha
 */
public class FillChests implements Listener {
    private final ChestRandomizer chestRandomizer;

    public FillChests(ChestRandomizer chestRandomizer) {
        this.chestRandomizer = chestRandomizer;
    }

    /**
     * Method that finds chest to fill.
     *
     * @param chestName Name of chest being filled.
     */
    public void fill(String chestName) {
        int x;
        int y;
        int z;
        String tier;

        String chest = "chests." + chestName;

        x = chestRandomizer.getConfig().getInt(chest + ".x");
        y = chestRandomizer.getConfig().getInt(chest + ".y");
        z = chestRandomizer.getConfig().getInt(chest + ".z");

        String world;
        world = chestRandomizer.getConfig().getString(chest + ".world", chestRandomizer.getConfig().getString("default-world"));

        tier = chestRandomizer.getConfig().getString(chest + ".tier");

        World w = Bukkit.getWorld(world);
        Block b = w.getBlockAt(x, y, z);

        if(b != null && b.getType() == Material.CHEST) {
            Chest c = (Chest) b.getState();
            c.getInventory().clear();

            if (tier.toLowerCase().contains("random")) {
                tier = randomTier(tier);
            }

            doFill(c, tier);

        } else {
            Bukkit.getLogger().info("Chest " + chestName + " at " + x + " / " + y + " / " + z + " in world " + world + " not found.");
        }
    }

    /**
     * Method that finds chest to fill within specified radius.
     *
     * @param chestName Name of chest being filled.
     * @param radius Radius to filter chests by.
     */
    public void fill(String chestName, Integer radius, CommandSender sender) {
        int x;
        int y;
        int z;
        String tier;

        String chest = "chests." + chestName;

        x = chestRandomizer.getConfig().getInt(chest + ".x");
        y = chestRandomizer.getConfig().getInt(chest + ".y");
        z = chestRandomizer.getConfig().getInt(chest + ".z");

        String world;
        world = chestRandomizer.getConfig().getString(chest + ".world", chestRandomizer.getConfig().getString("default-world"));

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

            tier = chestRandomizer.getConfig().getString(chest + ".tier");

            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);

            if(b != null && b.getType() == Material.CHEST) {
                Chest c = (Chest) b.getState();
                c.getInventory().clear();

                if (tier.toLowerCase().contains("random")) {
                    tier = randomTier(tier);
                }

                doFill(c, tier);

            } else {
                Bukkit.getLogger().info("Chest " + chestName + " at " + x + " / " + y + " / " + z + " in world " + world + " not found.");
            }

        }

    }

    /**
     * Method that finds chest to fill within specified region.
     *
     * @param chestName Name of chest being filled.
     * @param region Region to filter chests by.
     */
    public void fill(String chestName, String region) {
        int x;
        int y;
        int z;
        String tier;

        String chest = "chests." + chestName;

        x = chestRandomizer.getConfig().getInt(chest + ".x");
        y = chestRandomizer.getConfig().getInt(chest + ".y");
        z = chestRandomizer.getConfig().getInt(chest + ".z");

        String world;
        world = chestRandomizer.getConfig().getString(chest + ".world", chestRandomizer.getConfig().getString("default-world"));

        String regionFound = chestRandomizer.getConfig().getString(chest + ".region");

        if (regionFound.equals(region)) {

            tier = chestRandomizer.getConfig().getString(chest + ".tier");

            World w = Bukkit.getWorld(world);
            Block b = w.getBlockAt(x, y, z);

            if(b != null && b.getType() == Material.CHEST) {
                Chest c = (Chest) b.getState();
                c.getInventory().clear();

                if (tier.toLowerCase().contains("random")) {
                    tier = randomTier(tier);
                }

                doFill(c, tier);

            } else {
                Bukkit.getLogger().info("Chest " + chestName + " at " + x + " / " + y + " / " + z + " in world " + world + " not found.");
            }

        }
    }

    /**
     * Method that generates a random tier from the "random" list in config.
     *
     * @param randTier Name of random tier inputted by a fill command
     * @return Randomly generated tier
     */
    public String randomTier(String randTier) {

        ArrayList<String> randtiers = (ArrayList<String>) chestRandomizer.getConfig().getStringList("tiers." + randTier + ".list");
        ArrayList<String> tierprobs = new ArrayList<>();
        for (int j = 0; j < randtiers.size(); j++) {
            for (int k = 0; k < chestRandomizer.getConfig().getInt("tiers." + randTier + "." + randtiers.get(j) + ".percent"); k++) {
                tierprobs.add(randtiers.get(j));
            }
        }
        Random rand = new Random();
        return tierprobs.get(rand.nextInt(tierprobs.size()));
    }

    /**
     * Method that puts items in chests.
     *
     * @param c Chest found by fill()
     * @param tier Tier of chest c
     */
    public void doFill(Chest c, String tier) {
        ArrayList<String> tieritems = (ArrayList<String>) chestRandomizer.getConfig().getStringList("tiers." + tier + ".items");
        Integer min = chestRandomizer.getConfig().getInt("tiers." + tier + ".min-items");
        Integer max = chestRandomizer.getConfig().getInt("tiers." + tier + ".max-items") + 1;
        ArrayList<Integer> itemcounts = new ArrayList<Integer>();
        for (int j = min; j < max; j++) {
            itemcounts.add(j);
        }
        Integer itemcount = (int) IdTools.getRandomElement(itemcounts);
        for (int j = 0; j < itemcount; j++) {
            Random rand = new Random();
            String item = tieritems.get(rand.nextInt(tieritems.size()));

            Integer count;
            if (IdTools.Stackable(item)) {
                count = rand.nextInt(chestRandomizer.getConfig().getInt("tiers." + tier + ".stack-limit", chestRandomizer.getConfig().getInt("default-stack-limit"))) + 1;
            } else {
                count = 1;
            }

            for (int amountOfItems = 0; amountOfItems < count; amountOfItems++) {
                c.getBlockInventory().addItem(new ItemStack(Material.valueOf(item)));
            }
        }
    }
}
