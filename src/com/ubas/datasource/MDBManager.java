package com.ubas.datasource;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.ubas.util.Constants;

public class MDBManager {
	public static Map<String, MDBPoll> mdbPollMap;
	
	static {
		mdbPollMap = new HashMap<String, MDBPoll>();
	}
	
	public static void initMDBPolls() {
		try {
			MDBPoll mdbPoll = new MDBPoll(Constants.MONGODB_ADDR, Constants.MONGODB_PORT);
			mdbPollMap.put(Constants.MONGODB_ADDR, mdbPoll);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
