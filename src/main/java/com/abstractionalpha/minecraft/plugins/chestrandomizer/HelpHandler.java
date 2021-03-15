package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Hash map class for help commands
 *
 * @author abstractionAlpha
 * @version 1.0
 */
public class HelpHandler {

    /**
     * Main handler that checks arguments length to determine where to point next.
     *
     * @param sender player or server that executes command
     * @param args command-line arguments
     */
    public void MainHandler(CommandSender sender, String[] args, HelpOutputs helpOutputs) {
        switch (args.length) {
            case 1:
                helpOutputs.MainHelp(sender);
                break;

            case 2:
                DeepHandler(sender, args[1], helpOutputs);
                break;

            default:
                sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Too many arguments. Type `/cr help` for help.");
                break;
        }
    }

    /**
     * Secondary handler that handles elaborate help messages (`/cr help` + arguments)
     *
     * @param sender player or server that executes command
     * @param argument string representing type of help
     * @param helpOutputs instance of HelpOutputs class
     */
    public void DeepHandler(CommandSender sender, String argument, HelpOutputs helpOutputs) {
        switch (argument) {
            case "fill":
                helpOutputs.FillHelp(sender);
                break;

            case "empty":
                helpOutputs.EmptyHelp(sender);
                break;

            case "chest":
                helpOutputs.ChestHelp(sender);
                break;

            default:
                sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Argument " + argument + " not found. Type `/cr help` for help.");
                break;
        }
    }

}
