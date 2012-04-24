package com.mattearlypwns.plugins.moreinfo;

public class TimedGarbageCollector implements Runnable {

	static boolean shouldRun = true;
	MoreInfo plugin;

	public TimedGarbageCollector(MoreInfo plugin) {
		plugin = this.plugin;
	}

	@Override
	public void run() {

		while (shouldRun) {
			System.gc();
			plugin.log("Ran the garbage collector...", false);
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void stopGC() {
		shouldRun = false;

	}

}
