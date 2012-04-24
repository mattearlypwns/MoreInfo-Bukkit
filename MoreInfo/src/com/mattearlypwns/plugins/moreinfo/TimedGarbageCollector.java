package com.mattearlypwns.plugins.moreinfo;

public class TimedGarbageCollector implements Runnable {

	boolean shouldRun = true;

	@Override
	public void run() {

		while (shouldRun) {
			System.gc();
			try {
				wait(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopGC() {
		shouldRun = false;
	}

}
