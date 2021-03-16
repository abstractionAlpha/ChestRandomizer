package com.abstractionalpha.minecraft.plugins.chestrandomizer.manager;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.cli.Fill;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChestRandomizerCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("The /cr command requires arguments.");
            }

            switch (args[0].toLowerCase()) {
                case "fill":
                    Fill.processArgs(args, sender);
                    break;
                case "empty":
                    // TODO: Implement empty features
                    break;
                case "chest":
                    // TODO: Implement chest features
                    break;
                case "help":
                    // TODO: Implement help features
                    break;
                case "?":
                    // TODO: Implement ? features
                    break;
                default:
                    throw new IllegalArgumentException("Argument '" + args[0] + "' not recognized.");
            }
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Error: " + e.getMessage());
            sender.sendMessage(ChatColor.RED + "Try typing '/cr help' for help.");
        }

        return true;
    }

}
