package com.ubas.dao;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import com.mongodb.DB;
import com.ubas.datasource.MDBPoll;
import com.ubas.util.Constants;
import com.ubas.util.Parameter;

public class PageAccessAnalyDAO {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getAccessPage(Parameter parameter) {
		
		MDBPoll mongo = null; 
		try {
			mongo = new MDBPoll(Constants.MONGODB_ADDR, Constants.MONGODB_PORT);
			
			long startTime = parameter.getStartTime();
			long endTime = parameter.getEndTime();
			
			String startkey = SDF.format(startTime);
			String endkey = SDF.format(endTime);
			
			int[] sorts = parameter.getSorts();
			
			int sort = sorts[0];
			
			System.out.println(startkey);
			System.out.println(endkey);
				
//			DB db = mongo.getDatabase("user_behavior_analysis_result");
//			Object result = db.eval("clothing_epd_access_page(\"" + startTime + "\", \"" + endTime + "\")");

			DB db = mongo.getDatabase("db_muti_user");
			Object result = db.eval("clothing_epd_access_page(\"" + startTime + "\", \"" + endTime + "\", \"" + sort + "\")");
			
			System.out.println(result);
			
			return result.toString();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
