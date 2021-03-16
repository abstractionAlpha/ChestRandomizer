package com.abstractionalpha.minecraft.plugins.chestrandomizer.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ChestRandomizerCommandExecutor implements CommandExecutor {

    /** Singleton instance of this Java plugin. */
    ChestRandomizer chestRandomizer = JavaPlugin.getPlugin(ChestRandomizer.class);

    /*
    This method is an override of the CommandExecutor interface's onCommand class. It is used to handle executions of
    the /cr commands in the Minecraft CLI.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Implement onCommand
        return false;
    }

}
