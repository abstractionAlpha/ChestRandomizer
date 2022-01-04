package com.abstractionalpha.minecraft.plugin.chestrandomizer;

import org.bukkit.plugin.java.JavaPlugin;

import com.abstractionalpha.minecraft.plugin.chestrandomizer.command.ChestEditorCommandExecutor;

public class ChestRandomizer extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("[ChestRandomizer] Starting up...");
		getCommand("chesteditor").setExecutor(new ChestEditorCommandExecutor(this));
	}
	
	@Override
	public void onDisable() {
		getLogger().info("[ChestRandomizer] Shutting down...");
	}

}
