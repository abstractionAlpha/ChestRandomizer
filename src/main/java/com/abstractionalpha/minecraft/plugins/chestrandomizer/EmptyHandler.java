package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.manager.ChestRandomizer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing hash maps which determine what method to execute in EmptyChests.
 *
 * @author abstractionAlpha
 * @version 1.0
 */
public class EmptyHandler implements Listener {

    private final ChestRandomizer chestRandomizer;

    public EmptyHandler(ChestRandomizer chestRandomizer) {
        this.chestRandomizer = chestRandomizer;
    }

    /**
     * Hash map handling input from user and redirecting to EmptyChests.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     * @param emptyChests instance of EmptyChests class
     */
    public void MainHandler(CommandSender sender, String[] args, EmptyChests emptyChests) {
        String methodArgument = IdTools.FillEmptyType(args[1]);

        List<String> chestList = new ArrayList<>();
        for (String key: chestRandomizer.getConfig().getConfigurationSection("chests").getKeys(false)) {
            chestList.add(key);
        }

        String chestToEmpty;
        switch (methodArgument) {
            case "allArg":
                for (int i=0; i < chestList.size(); i++) {
                    chestToEmpty = chestList.get(i);
                    emptyChests.empty(chestToEmpty);
                }
                sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chests emptied.");
                break;

            case "radiusArg":
                int radius = Integer.valueOf(args[1]);
                for (int i=0; i < chestList.size(); i++) {
                    chestToEmpty = chestList.get(i);
                    emptyChests.empty(chestToEmpty, radius, sender);
                }
                sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chests in radius " + radius + " emptied.");
                break;

            case "regionArg":
                for (int i=0; i < chestList.size(); i++) {
                    chestToEmpty = chestList.get(i);
                    emptyChests.empty(chestToEmpty, args[1]);
                }
                sender.sendMessage(ChatColor.GOLD + "[ChestRandomizer] Chests in region " + args[1] + " emptied.");
                break;

            default:
                sender.sendMessage(ChatColor.RED + "[ChestRandomizer] null");
                break;
        }

    }

}
