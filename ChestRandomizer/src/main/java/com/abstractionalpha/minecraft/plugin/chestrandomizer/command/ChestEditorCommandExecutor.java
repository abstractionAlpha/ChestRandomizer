package com.abstractionalpha.minecraft.plugin.chestrandomizer.command;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.abstractionalpha.minecraft.plugin.chestrandomizer.ChestRandomizer;

public class ChestEditorCommandExecutor implements CommandExecutor {
	
	private ChestRandomizer plugin;
	
	private boolean editorMode;
	
	private ItemStack isChestEditor;
	
	public static final String COMMAND_STRING = "chesteditor";
	
	public ChestEditorCommandExecutor(ChestRandomizer plugin) {
		this.plugin = plugin;
		editorMode = false;
		isChestEditor = generateItemStack();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase(COMMAND_STRING)) {
			if (editorMode) {
				clearEditor();
				editorMode = !editorMode;
			} else {
				generateEditor();
				editorMode = !editorMode;
			}
			return true;
		}
		return false;
	}
	
	private void generateEditor() {
		Iterator<Player> it = getOnlinePlayers();
		while (it.hasNext()) {
			Player p = it.next();
			if (p.hasPermission("chestrandomizer.admin")) {
				p.getInventory().addItem(isChestEditor);
				p.sendMessage(ChatColor.GREEN + "[ChestRandomizer] Editor mode enabled.");
			}
		}
	}
	
	private void clearEditor() {
		Iterator<Player> it = getOnlinePlayers();
		while (it.hasNext()) {
			Player p = it.next();
			if (p.hasPermission("chestrandomizer.admin")) {
				Iterator<ItemStack> invIterator = p.getInventory().iterator();
				while (invIterator.hasNext()) {
					ItemStack curr = invIterator.next();
					if (curr != null && curr.isSimilar(isChestEditor)) {
						p.getInventory().remove(curr);
					}
				}
				p.sendMessage(ChatColor.GREEN + "[ChestRandomizer] Editor mode disabled.");
			}
		}
	}
	
	private ItemStack generateItemStack() {
		ItemStack is = new ItemStack(Material.STICK);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Chest Editor");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Left-click chest to edit.");
		lore.add("Right-click to modify editor.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	@SuppressWarnings("unchecked")
	private Iterator<Player> getOnlinePlayers() {
		return (Iterator<Player>) plugin.getServer().getOnlinePlayers().iterator();
	}
	
	public boolean getEditorMode() {
		return editorMode;
	}
	
	public ItemStack getItemStack() {
		return isChestEditor.clone();
	}

}
