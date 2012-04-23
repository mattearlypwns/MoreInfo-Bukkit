package com.mattearlypwns.plugins.moreinfo.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mattearlypwns.plugins.moreinfo.MoreInfo;
import com.mattearlypwns.plugins.moreinfo.PluginUtil;

public class MoreInfoServerExecutor {

	private MoreInfo plugin;

	public MoreInfoServerExecutor(MoreInfo plugin) {
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

					plugin.log(plugin.getName() + " Reloaded", false);
					plugin.reload();
					return true;
				}

				else if (args[0].equalsIgnoreCase("savelog")) {

					plugin.log("Dumped log to log file", false);
					plugin.unloadLog();
					return true;
				}
			}
		}

		return false;
	}

}
