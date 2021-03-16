package com.abstractionalpha.minecraft.plugins.chestrandomizer.cli;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.manager.ChestRandomizer;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.ChestInstance;
import com.abstractionalpha.minecraft.plugins.chestrandomizer.objects.Region;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Fill {

    public static void processArgs(String[] args, CommandSender sender) throws IllegalArgumentException {
        if (args.length == 1) {
            throw new IllegalArgumentException("Fill requires another parameter.");
        }

        if (args.length > 2) {
            throw new IllegalArgumentException("Incorrect usage of '/cr fill'.");
        }

        switch (validate(args[1])) {
            case "all":
                fill();
                break;
            case "radius":
                if (!(sender instanceof Player)) {
                    throw new IllegalArgumentException("Using '/cr fill <radius>' requires a player to execute.");
                }

                fill(Integer.parseInt(args[1]), (Player) sender);
                break;
            case "region":
                fill(args[1]);
                break;
            default:
                throw new IllegalArgumentException("Unexpected parameter '" + args[1] + "' with fill.");
        }
    }

    private static void fill() throws IllegalArgumentException {
        ArrayList<ChestInstance> chestsToFill = JavaPlugin.getPlugin(ChestRandomizer.class).chests.getDirectory();

        for (ChestInstance chest : chestsToFill) {
            fill(chest);
        }
    }

    private static void fill(int radius, Player player) throws IllegalArgumentException {
        ArrayList<ChestInstance> chestsToFill = JavaPlugin.getPlugin(ChestRandomizer.class).chests.getDirectory();

        Location loc = player.getLocation();

        int x;
        int z;
        World chestWorld;

        int radiusSquared = radius * radius;
        int distSquared;

        for (ChestInstance chest : chestsToFill) {
            chestWorld = chest.getWorld();

            if (chestWorld != loc.getWorld()) {
                continue;
            }

            x = chest.getCoordinates()[0];
            z = chest.getCoordinates()[2];

            distSquared = (x-loc.getBlockX()) * (x-loc.getBlockX()) + (z-loc.getBlockZ()) * (z-loc.getBlockZ());

            if (distSquared <= radiusSquared) {
                fill(chest);
            }
        }
    }

    private static void fill(String region) throws IllegalArgumentException {
        ArrayList<Region> regions = JavaPlugin.getPlugin(ChestRandomizer.class).regions.getDirectory();
        int idx = -1;

        for (int i = 0; i < regions.size(); i++) {
            if (region.equals(regions.get(i).getName())) {
                idx = i;
            }
        }

        if (idx == -1) {
            throw new IllegalArgumentException("Region '" + region +"' not found. (Note that this value is case-sensitive.");
        }

        ArrayList<ChestInstance> chestsToFill = regions.get(idx).getChests();

        for (ChestInstance chest : chestsToFill) {
            fill(chest);
        }
    }

    private static void fill(ChestInstance chest) {
        World w = chest.getWorld();
        Block b = w.getBlockAt(chest.getCoordinates()[0], chest.getCoordinates()[1], chest.getCoordinates()[2]);

        ArrayList<ItemStack> items;

        if (b != null && b.getType() == Material.CHEST) {
            Chest c = (Chest) b.getState();
            c.getBlockInventory().clear();

            items = chest.getTier().generateChest();

            for (int i = 0; i < items.size(); i++) {
                c.getBlockInventory().setItem(i, items.get(i));
            }
        }
    }

    private static String validate(String arg) {
        try {
            Integer.parseInt(arg);
            return "radius";
        } catch (NumberFormatException e) {
            ;
        }

        if (arg.equals("all")) {
            return "all";
        }

        return "region";
    }
}
