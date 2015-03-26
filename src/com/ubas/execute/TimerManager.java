package com.ubas.execute;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
	
	private static Timer timer;
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public static void initTimerManager() {
		
		Calendar calendar = (Calendar) Calendar.getInstance().clone();
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date date = calendar.getTime();
		System.out.println(date);
		System.out.println(SDF.format(date.getTime()));
		System.out.println(new Date());
		
		if (date.before(new Date())) {
			date = addDayTime(date, 1);
		}
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				SingleUserJob singleUserJob = new SingleUserJob();
				new Thread(singleUserJob).start();
			}
		};
		
		getTimer().schedule(task, date, PERIOD_DAY);
	}
	
	private static Date addDayTime(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public static Timer getTimer() {
		if (timer == null) {
			timer = new Timer();
		}
		return timer;
	}
}
