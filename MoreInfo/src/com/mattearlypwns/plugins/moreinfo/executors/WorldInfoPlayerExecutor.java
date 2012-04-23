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
		switch (args.length) {

		case 0:
			sender.sendMessage("World Name: " + player.getWorld().toString());
			/*
			 * TODO Test this
			 */
		}

		return false;
	}

}
