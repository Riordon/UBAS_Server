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
			
			System.out.println(startkey);
			System.out.println(endkey);
			
			int sort = 0;
			int[] sorts = parameter.getSorts();
			if (sorts != null)
				sort = sorts[0];
			
			DB db = null;
			Object result = null;
			
			String account = parameter.getAccount();
			if (account == null) {
				db = mongo.getDatabase("db_muti_user");
				result = db.eval("clothing_epd_access_page(\"" + startTime + "\", \"" + endTime + "\", \"" + sort + "\")");
			}
			else {
			//	db = mongo.getDatabase("db_single_user");
			//	result = db.eval("clothing_epd_access_page(\"" + startTime + "\", \"" + endTime + "\", \"" + sort + "\")");
			}
			
			System.out.println(result);
			
			return result.toString();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
