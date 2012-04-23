package com.mattearlypwns.plugins.moreinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.bukkit.ChatColor;

public class MoreInfoAPI {

	private static int instances = 0;
	private static Calendar calendar = IDK.cal;

	// Available to this package only (basically the only plugin)

	static int getInstances() {
		return instances;
	}

	// Available to anyone that has an instance of this class

	public MoreInfoAPI() {
		++instances;
	}

	public String getDate() {
		return IDK.dateFormat.format(calendar.getTime());
	}

	/**
	 * Saves the contents of log to the outputFile
	 * 
	 * @author mattearlypwns
	 * @param append
	 *            weather or not the log should be appended to the file
	 * @param outputFile
	 *            is the file that the log will be unloaded to
	 * @param log
	 *            the list that all the log data is stored
	 */
	public void unloadLog(File outputFile, List<String> log, boolean append)
			throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile,
				append));
		for (String s : log) {
			bw.write(s);
			bw.newLine();
		}
		bw.close();
		log.clear();
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
	public void correctColorTags(List<String> list, String tagMarker) {

		int firstLoc, secondLoc;
		String color;
		String line;

		for (int i = 0; i < list.size(); i++) {

			line = list.get(i);

			while (line.contains(tagMarker)) {

				firstLoc = line.indexOf(tagMarker);
				secondLoc = line.indexOf(tagMarker, firstLoc + 1);

				// Getting the color of the tag
				color = line.substring(firstLoc, secondLoc).replaceAll(
						tagMarker, "");

				// Assigning the right value

				line = line.replace(tagMarker + color + tagMarker, ChatColor
						.valueOf(color.toUpperCase()).toString());

				// Saving the line
				list.set(i, line);

				// This gets the line to check for other tags
				line = list.get(i);

			}

		}

	}

}
