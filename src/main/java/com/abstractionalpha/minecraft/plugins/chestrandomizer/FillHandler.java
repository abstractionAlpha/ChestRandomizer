package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing hash maps which determine what method to execute in FillChests.
 *
 * @author abstractionAlpha
 * @version 1.0
 */
public class FillHandler implements Listener {

    private final ChestRandomizer chestRandomizer;

    public FillHandler(ChestRandomizer chestRandomizer) {
        this.chestRandomizer = chestRandomizer;
    }

    /**
     * Hash map handling input from user and redirecting to EmptyChests.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     * @param fillChests instance of FillChests class
     */
    public void MainHandler(CommandSender sender, String[] args, FillChests fillChests) {
        String methodArgument = IdTools.FillEmptyType(args[1]);

        List<String> chestList = new ArrayList<>();
        for (String key: chestRandomizer.getConfig().getConfigurationSection("chests").getKeys(false)) {
            chestList.add(key);
        }

        String chestToFill;
        switch (methodArgument) {
            case "allArg":
                for (int i=0; i < chestList.size(); i++) {
                    chestToFill = chestList.get(i);
                    fillChests.fill(chestToFill);
                }
                sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chests filled.");
                break;

            case "radiusArg":
                int radius = Integer.valueOf(args[1]);
                for (int i=0; i < chestList.size(); i++) {
                    chestToFill = chestList.get(i);
                    fillChests.fill(chestToFill, radius, sender);
                }
                sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chests in radius " + radius + " filled.");
                break;

            case "regionArg":
                for (int i=0; i < chestList.size(); i++) {
                    chestToFill = chestList.get(i);
                    fillChests.fill(chestToFill, args[1]);
                }
                sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chests in region " + args[1] + " filled.");
                break;

            default:
                sender.sendMessage(ChatColor.RED + "[ChestRandomizer] null");
                break;
        }

    }

}
