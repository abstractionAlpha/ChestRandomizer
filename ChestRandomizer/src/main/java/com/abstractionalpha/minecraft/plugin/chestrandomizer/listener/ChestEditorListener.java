package com.abstractionalpha.minecraft.plugin.chestrandomizer.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.BlockBreakEvent;

import com.abstractionalpha.minecraft.plugin.chestrandomizer.ChestRandomizer;
import com.abstractionalpha.minecraft.plugin.chestrandomizer.command.ChestEditorCommandExecutor;

public class ChestEditorListener implements Listener {
	
	private ChestEditorCommandExecutor exec;
	
	public ChestEditorListener(ChestRandomizer plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		exec = (ChestEditorCommandExecutor) plugin.getCommand(ChestEditorCommandExecutor.COMMAND_STRING).getExecutor();
	}
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent pje) {
		Player p = pje.getPlayer();
		if (exec.getEditorMode()) {
			if (p.hasPermission("chestrandomizer.admin")) {
				p.getInventory().addItem(exec.getItemStack());
			}
		}
	}
	
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent pqe) {
		Player p = pqe.getPlayer();
		if (exec.getEditorMode()) {
			if (p.hasPermission("chestrandomizer.admin")) {
				p.getInventory().remove(exec.getItemStack());
			}
		}
	}
	
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent bbe) {
		if (exec.getEditorMode()) {
			Player p = bbe.getPlayer();
			ItemStack is = p.getInventory().getItem(p.getInventory().getHeldItemSlot());
			if (is.isSimilar(exec.getItemStack()) && p.hasPermission("chestrandomizer.admin")) {
				bbe.setCancelled(true);
				// TODO Add handling for chests here
			}
		}
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent pie) {
		if (exec.getEditorMode()) {
			Player p = pie.getPlayer();
			ItemStack is = p.getInventory().getItem(p.getInventory().getHeldItemSlot());
			if (is.isSimilar(exec.getItemStack()) && p.hasPermission("chestrandomizer.admin")) {
				pie.setCancelled(true);
				// TODO Add GUI for editor here
			}
		}
	}

}
