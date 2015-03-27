package com.ubas.execute;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
	
	private static Timer timer;
	private static final long PERIOD_DAY = 20 * 1000;
	private static final long PERIOD_DAY_T = 24 * 60 * 60 * 1000;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public static long beginTimeT;
	public static long endTimeT;
	
	public static void initTimerManager() {
		
		Calendar calendar = (Calendar) Calendar.getInstance().clone();
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date date = calendar.getTime();
		System.out.println(date);
		System.out.println(SDF.format(date.getTime()));
		
		final long beginTime = date.getTime();
		final long endTime = beginTime + PERIOD_DAY_T;	
		
		beginTimeT = beginTime;
		endTimeT = endTime;
		
		if (date.before(new Date())) {
			date = addDayTime(date, 1);
		}
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
//				SingleUserJob singleUserJob = new SingleUserJob();
//				new Thread(singleUserJob).start();
				MutiUserJob mutiUserJob = new MutiUserJob(beginTimeT, endTimeT);
				
				new Thread(mutiUserJob).start();
				
				beginTimeT += PERIOD_DAY_T;
				endTimeT += PERIOD_DAY_T;
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
