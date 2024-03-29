package com.mattearlypwns.plugins.moreinfo.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mattearlypwns.plugins.moreinfo.MoreInfo;
import com.mattearlypwns.plugins.moreinfo.PluginUtil;
import com.mattearlypwns.plugins.moreinfo.TimedGarbageCollector;

public class MoreInfoPlayerExecutor {

	private MoreInfo plugin;

	public MoreInfoPlayerExecutor(MoreInfo plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (cmd.getName().equalsIgnoreCase("moreinfo")) {

			switch (args.length) {

			case 0:
				return PluginUtil.sendDataToUser(MoreInfo.fileContent, sender);

			case 1:

				if (args[0].equalsIgnoreCase("reload")) {

					if (sender.hasPermission("moreinfo.reload")
							|| sender.hasPermission("moreinfo.*")) {
						plugin.reload();
						sender.sendMessage(ChatColor.GREEN + plugin.getName()
								+ " Reload Complete");
						return true;

					} else {

						sender.sendMessage(ChatColor.RED
								+ "You do not have the right permissions");
						return true;
					}

				} else if (args[0].equalsIgnoreCase("savelog")) {

					if (sender.hasPermission("moreinfo.savelog")
							|| sender.hasPermission("moreinfo.*")) {

						sender.sendMessage(ChatColor.GREEN
								+ "Dumped log to log file");

						plugin.log("Dumped log to log file", true);

						plugin.unloadLog();

						return true;
					}

				} else if (args[0].equalsIgnoreCase("toggleDisposer")
						|| args[0].equalsIgnoreCase("tDisp")
						&& (sender.hasPermission("moreinfo.toggleDisposer") || sender
								.hasPermission("moreinfo.*"))) {

					plugin.log("Toggling Garbage Collector", false);
					plugin.log("State of Garbage Collector: "
							+ TimedGarbageCollector.toggleDisposer(), false);
					return true;

				} else {
					sender.sendMessage(ChatColor.RED
							+ "You do not have the right permissions");
					return true;
				}

			}

		}

		return false;
	}
}
