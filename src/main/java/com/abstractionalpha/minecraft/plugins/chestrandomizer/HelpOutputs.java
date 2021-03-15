package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import com.abstractionalpha.minecraft.plugins.chestrandomizer.manager.ChestRandomizer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Class that outputs help messages as prompted by a user.
 *
 * @author abstractionAlpha
 * @version 1.0
 */
public class HelpOutputs {

    private final ChestRandomizer chestRandomizer;

    public HelpOutputs(ChestRandomizer chestRandomizer) {
        this.chestRandomizer = chestRandomizer;
    }

    /**
     * Method that sends a command sender the general help format
     *
     * @param sender player or server that executes command
     */
    public void MainHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "Type `/cr help <argument>` where argument is...");
        sender.sendMessage(ChatColor.YELLOW + "* fill: Help with `/cr fill` command that refills chests");
        sender.sendMessage(ChatColor.YELLOW + "* empty: Help with `/cr empty` command that empties chests");
        sender.sendMessage(ChatColor.YELLOW + "* chest: Help with `/cr chest` commands that add chests to the config");
    }

    /**
     * Method that sends a command sender the help text for fill methods
     *
     * @param sender player or server that executes command
     */
    public void FillHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "Usage: `/cr fill <region|radius|all>`");
        sender.sendMessage(ChatColor.YELLOW + "* region: A region you have defined for chest(s)");
        sender.sendMessage(ChatColor.YELLOW + "* radius: A radius from command user. Player command only");
        sender.sendMessage(ChatColor.YELLOW + "* all: Every chest defined in the config");
    }

    /**
     * Method that sends a command sender the help text for empty methods
     *
     * @param sender player or server that executes command
     */
    public void EmptyHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "Usage: `/cr empty <region|radius|all>`");
        sender.sendMessage(ChatColor.YELLOW + "* region: A region you have defined for chest(s)");
        sender.sendMessage(ChatColor.YELLOW + "* radius: A radius from command user. Player command only");
        sender.sendMessage(ChatColor.YELLOW + "* all: Every chest defined in the config");
    }

    /**
     * Method that sends a command sender the help text for chest methods
     *
     * @param sender player or server that executes command
     */
    public void ChestHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "All `/cr chest <arguments>` commands:");
        sender.sendMessage(ChatColor.YELLOW + "* Add Chest: `/cr chest <name> add <tier> <x> <y> <z>`");
        sender.sendMessage(ChatColor.YELLOW + "* Remove Chest: `/cr chest <name> del`");
        sender.sendMessage(ChatColor.YELLOW + "* Set Region: `/cr chest <name> setreg <region>`");
        sender.sendMessage(ChatColor.YELLOW + "* Remove Region: `/cr chest <name> delreg`");
    }
}
