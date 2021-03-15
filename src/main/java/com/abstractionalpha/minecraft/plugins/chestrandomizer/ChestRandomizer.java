package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class containing executables. Handles command processing and user interface.
 *
 * @version 1.5
 * @author abstractionAlpha
 */
public final class ChestRandomizer extends JavaPlugin {

    ChestAuthor chestWrite;
    ChestHandler chestHandle;
    FillHandler fillHandle;
    FillChests fillChests;
    EmptyHandler emptyHandle;
    EmptyChests emptyChests;
    HelpOutputs helpOutputs;

    HelpHandler helpHandle = new HelpHandler();

    /**
     * Plugin start-up logic.
     */
    @Override
    public void onEnable() {
        chestWrite = new ChestAuthor(this);
        chestHandle = new ChestHandler();
        fillHandle = new FillHandler(this);
        fillChests = new FillChests(this);
        emptyHandle = new EmptyHandler(this);
        emptyChests = new EmptyChests(this);
        helpOutputs = new HelpOutputs(this);
        getServer().getPluginManager().registerEvents(fillChests, this);
        getServer().getPluginManager().registerEvents(emptyChests, this);
        saveDefaultConfig();
    }

    /**
     * Plugin shut-down logic.
     */
    @Override
    public void onDisable() {
        saveConfig();
    }

    /**
     * Handles command inputs from user.
     *
     * @param cmd Command issued (looks for '/cr')
     * @param sender Player or console who issued command
     * @param args Command-line arguments
     * @return Boolean representing successful or unsuccessful execution of command
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cr")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Missing arguments. Type `/cr help` for help.");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "fill":
                    fillHandle.MainHandler(sender, args, fillChests);
                    break;

                case "empty":
                    emptyHandle.MainHandler(sender, args, emptyChests);
                    break;

                case "chest":
                    chestHandle.MainHandler(sender, args, chestWrite);
                    break;

                case "help":
                    helpHandle.MainHandler(sender, args, helpOutputs);
                    break;

                default:
                    sender.sendMessage(ChatColor.RED + "[ChestRandomizer] Command not found. Type `/cr help` for help.");
            }

        } return true;
    }
}
