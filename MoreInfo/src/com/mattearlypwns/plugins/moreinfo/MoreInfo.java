package com.mattearlypwns.plugins.moreinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreInfo extends JavaPlugin {

	File info;
	File logFile;

	private MoreInfoPlayerExecutor player;
	private MoreInfoServerExecutor server;

	private static ArrayList<String> log = new ArrayList<String>();

	static Logger logger;
	static Calendar cal = Calendar.getInstance();
	static ArrayList<String> fileContent = new ArrayList<String>();
	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Override
	public void onEnable() {

		log("Starting MoreInfo", true);
		long start = System.nanoTime();

		info = new File(getDataFolder().getAbsolutePath() + "/info.txt");
		logFile = new File(getDataFolder().getAbsolutePath() + "/log.txt");
		server = new MoreInfoServerExecutor(this);
		player = new MoreInfoPlayerExecutor(this);
		logger = getLogger();

		if (!getDataFolder().exists()) {

			getDataFolder().mkdirs();
		}

		if (info.exists()) {
			readInfo();

		} else {

			createFile(info);
			log("Created Info", true);
		}

		if (!logFile.exists()) {

			createFile(logFile);
			log("Created Log", true);

		}

		long end = System.nanoTime();
		log("Loaded <" + String.valueOf(end - start) + "> nanos", false);
		log("Plugins using MoreInfo API: " + MoreInfoAPI.getInstances(), false);
	}

	@Override
	public void onDisable() {

		log(getName() + " Shutting Down", true);
		unloadLog();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (cmd.getName().equalsIgnoreCase("moreinfo")) {

			if (sender instanceof Player) {

				log("[Player Command] " + sender.getName() + " sent /"
						+ cmd.getName() + PluginUtil.arrayToString(args), false);

				return player.onCommand(sender, cmd, label, args);

			} else if (sender instanceof ConsoleCommandSender) {

				log("[Server Command] /" + cmd.getName()
						+ PluginUtil.arrayToString(args), true);

				return server.onCommand(sender, cmd, label, args);

			} else {

				log("Command recieved from an unknown place", false);
				return true;
			}
		}
		return false;
	}

	public void reload() {

		log("Reloading MoreInfo", true);

		fileContent.clear();
		onEnable();

		log("Reloaded MoreInfo", true);
	}

	public static void createFile(File f) {
		try {

			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void log(String msg, boolean fileOnly) {

		if (fileOnly) {
			log.add(getDate() + " " + msg);
		}

		else {
			getLogger().info(msg);
			log.add(getDate() + " " + msg);
		}
	}

	public void readInfo() {
		try {

			BufferedReader br = new BufferedReader(new FileReader(info));
			String input;

			while ((input = br.readLine()) != null) {
				fileContent.add(input);
			}
			correctColorTags();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unloadLog() {

		try {

			BufferedWriter bw = new BufferedWriter(
					new FileWriter(logFile, true));
			for (String s : log) {
				bw.write(s);
				bw.newLine();
			}
			bw.close();
			log.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Changes all color tags to the corresponding color in list Works with
	 * every color in org.bukkit.ChatColor
	 * 
	 * @see org.bukkit.ChatColor
	 * 
	 * @param ArrayList
	 *            of type String
	 * @author mattearlypwns
	 * 
	 */
	public void correctColorTags() {

		int firstLoc, secondLoc;
		String color;
		String line;

		for (int i = 0; i < fileContent.size(); i++) {

			line = fileContent.get(i);

			while (line.contains("%")) {

				firstLoc = line.indexOf("%");
				secondLoc = line.indexOf("%", firstLoc + 1);

				color = line.substring(firstLoc, secondLoc).replaceAll("%", "");

				line = line.replace("%" + color + "%",
						ChatColor.valueOf(color.toUpperCase()).toString());

				fileContent.set(i, line);

				line = fileContent.get(i);

			}

		}
	}

	public static String getDate() {
		return dateFormat.format(cal.getTime());
	}
}
