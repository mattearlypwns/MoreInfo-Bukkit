package com.mattearlypwns.plugins.moreinfo.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mattearlypwns.plugins.moreinfo.MoreInfo;

public class WorldInfoPlayerExecutor {

	private MoreInfo plugin;

	public WorldInfoPlayerExecutor(MoreInfo plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player player = (Player) sender;
		String send;

		switch (args.length) {

		case 0:
			send = player.getWorld().toString();
			sender.sendMessage("World Name: "
					+ send.substring(send.indexOf("=") + 1).replace("}", ""));
			sender.sendMessage("Location x: "
					+ Math.round(player.getLocation().getX()));
			sender.sendMessage("Location y: "
					+ Math.round(player.getLocation().getY()));
			sender.sendMessage("Location x: "
					+ Math.round(player.getLocation().getZ()));
			return true;
		}

		return false;
	}

}
