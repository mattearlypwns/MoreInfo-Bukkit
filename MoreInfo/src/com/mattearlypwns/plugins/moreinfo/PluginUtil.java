package com.mattearlypwns.plugins.moreinfo;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public class PluginUtil {

	public static String arrayToString(String[] args) {

		String out = " ";

		for (String s : args) {
			s.trim();
			out = out.concat(s + " ");
		}

		return out;
	}

	public static boolean sendDataToUser(ArrayList<String> array, CommandSender sender) {
		if (!array.isEmpty()) {
			sender.sendMessage(array.toArray(new String[array.size()]));
			return true;

		} else {
			sender.sendMessage("Nothing in the info file");
			return true;
		}
	}
}