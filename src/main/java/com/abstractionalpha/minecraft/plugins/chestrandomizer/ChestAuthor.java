package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChestAuthor {
    private final ChestRandomizer chestRandomizer;

    public ChestAuthor(ChestRandomizer chestRandomizer) {
        this.chestRandomizer = chestRandomizer;
    }

    /**
     * Method that adds a chest to the config.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     */
    public void AddChest(CommandSender sender, String[] args) {
        if (args.length != 7) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Incorrectly formatted arguments. Type `/cr help` for help.");
            return;
        }

        Player player = (Player) sender;
        World worldData = player.getWorld();
        String world = worldData.toString();
        world = world.substring(world.indexOf("=") + 1, world.indexOf("}"));

        String name = args[1];
        String tier = args[3];

        int x = 0;
        int y = 0;
        int z = 0;

        try {
            x = Integer.parseInt(args[4]);
            y = Integer.parseInt(args[5]);
            z = Integer.parseInt(args[6]);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Must input integers for coordinates.");
            return;
        }

        if (IdTools.isNumeric(name)) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Chest name must be non-integer value.");
            return;
        }

        if (chestRandomizer.getConfig().isSet("chests." + name)) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Chest of this name already exists.");
            return;
        }

        chestRandomizer.getConfig().createSection("chests." + name);
        chestRandomizer.getConfig().set("chests." + name + ".x", x);
        chestRandomizer.getConfig().set("chests." + name + ".y", y);
        chestRandomizer.getConfig().set("chests." + name + ".z", z);
        chestRandomizer.getConfig().set("chests." + name + ".tier", tier);
        chestRandomizer.getConfig().set("chests." + name + ".world", world);

        sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chest " + name + " added to config.");
    }

    /**
     * Method that deletes a chest from the config.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     */
    public void RemoveChest(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Incorrectly formatted arguments. Type `/cr help` for help.");
            return;
        }

        String name = args[1];

        if (chestRandomizer.getConfig().isSet("chests." + name)) {
            chestRandomizer.getConfig().set("chests." + name, null);
        }

        sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chest " + name + " removed from config.");
    }

    /**
     * Method that sets a chest's region.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     */
    public void SetRegion(CommandSender sender, String[] args) {
        String name = args[1];
        String region = args[3];
        if (!chestRandomizer.getConfig().isSet("chests." + name)) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Chest " + name + " not found in config.");
            return;
        }

        if (IdTools.isNumeric(region)) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Region must contain a letter.");
            return;
        }

        chestRandomizer.getConfig().set("chests." + name + ".region", region);

        sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Region " + region + " added to "+ name + ".");
    }

    /**
     * Method that removes a chest's region.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     */
    public void DeleteRegion(CommandSender sender, String[] args) {
        String name = args[1];
        if (!chestRandomizer.getConfig().isSet("chests." + name)) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Chest " + name + " not found in config.");
            return;
        }

        String region = chestRandomizer.getConfig().getString("chests." + name + ".region");
        chestRandomizer.getConfig().set("chests." + name + ".region", null);

        sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Region " + region + " removed from "+ name + ".");
    }

    public void Default(CommandSender sender, String argument) {
        sender.sendMessage(ChatColor.RED + "Argument " + argument + " not found. Type `/cr help` for help.");
    }
}
