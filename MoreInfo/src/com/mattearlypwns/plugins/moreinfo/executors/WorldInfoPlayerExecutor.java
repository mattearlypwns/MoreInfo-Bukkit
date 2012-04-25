package com.mattearlypwns.plugins.moreinfo.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldInfoPlayerExecutor {

	ChatColor gray = ChatColor.GRAY;

	public WorldInfoPlayerExecutor() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;
		String send;

		switch (args.length) {

		case 0:
			send = player.getWorld().toString();
			sender.sendMessage(gray + "World Name: "
					+ send.substring(send.indexOf("=") + 1).replace("}", ""));
			sender.sendMessage(gray + "World Type: "
					+ player.getLocation().getWorld().getWorldType().toString());
			sender.sendMessage(gray + "Location x: "
					+ Math.round(player.getLocation().getX()));
			sender.sendMessage(gray + "Location y: "
					+ Math.round(player.getLocation().getY()));
			sender.sendMessage(gray + "Location x: "
					+ Math.round(player.getLocation().getZ()));
			sender.sendMessage(gray + "Total Experience: "
					+ player.getTotalExperience());
			if (player.getBedSpawnLocation() != null) {
				sender.sendMessage("Bed Spawn Location: "
						+ player.getBedSpawnLocation().toString());
			} else {
				sender.sendMessage(gray
						+ "Bed Spawn Location: No Registered Bed Location");
			}
			return true;
		}

		return false;
	}
}
