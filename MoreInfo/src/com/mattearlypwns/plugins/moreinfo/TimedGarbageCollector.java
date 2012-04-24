package com.mattearlypwns.plugins.moreinfo;

public class TimedGarbageCollector implements Runnable {

	private static boolean shouldRun = true;
	private MoreInfo plugin;

	public TimedGarbageCollector(MoreInfo plugin) {
		plugin = this.plugin;
	}

	@Override
	public void run() {

		while (shouldRun) {
			System.gc();
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

	public static boolean toggleDisposer() {

		shouldRun = !shouldRun;

		return shouldRun;

	}

}
