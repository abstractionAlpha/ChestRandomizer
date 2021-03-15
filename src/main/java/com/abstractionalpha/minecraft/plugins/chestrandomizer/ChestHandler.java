package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

/**
 * Hash map class for chest commands
 *
 * @author abstractionAlpha
 * @version 1.0
 */
public class ChestHandler implements Listener {
    /**
     * Hash map handling user input for chest commands and redirecting to ChestAuthor
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     * @param chestWrite instance of ChestAuthor class
     */
    public void MainHandler(CommandSender sender, String[] args, ChestAuthor chestWrite) {
        switch (args[2].toLowerCase()) {
            case "add":
                chestWrite.AddChest(sender, args);
                break;
            case "del":
                chestWrite.RemoveChest(sender, args);
                break;
            case "setreg":
                chestWrite.SetRegion(sender, args);
                break;
            case "delreg":
                chestWrite.DeleteRegion(sender, args);
                break;
            default:
                chestWrite.Default(sender, args[2]);
                break;
        }
    }
}
