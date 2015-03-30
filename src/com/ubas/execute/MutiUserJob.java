package com.ubas.execute;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.ubas.datasource.MDBPoll;
import com.ubas.util.Constants;

public class MutiUserJob implements Runnable {
	
	private  long beginTime;
	private  long endTime;
	
	static int index = 1;
	
	public MutiUserJob(long beginTime, long endTime) {
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	
	public void run() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		MDBPoll mongo = null;
		try {
			mongo = new MDBPoll(Constants.MONGODB_ADDR, Constants.MONGODB_PORT);
		
			String type = "1";
			String channeltype = "0";
			String username = "";
	
			int page=0;
			int number=20;
			String customerType = "品牌公司";
			String areaNo = "广东深圳";
			String fasion = "时尚";
	
			String startKey = sdf.format(beginTime);
	//		String endKey = sdf.format(endTime);
			
			System.out.println(startKey);

			DB db = mongo.getDatabase("user_behavior_analysis");
			Object result = db.eval("clothing_epd_access_page(\"" + type + "\",\"" + channeltype + "\",\"" + username + "\",\""
					+ beginTime + "\",\"" + endTime + "\",\"" + page + "\",\""
					+ number + "\",\"" + customerType + "\",\"" + areaNo + "\",\"" + fasion + "\")");
			
			System.out.println(result);
			String[] splited = String.valueOf(result).split(",");
			
			String total_pv = splited[1].split(":")[1].replaceAll(" ", "");
			
			System.out.println(total_pv);
			
			if (total_pv.matches("0.0") || total_pv.isEmpty()) {
				System.out.println(startKey + " 之前的数据已经处理完成【注：如果截止时间是多天前，说明前几天无数据】");
				return;
			}
			
			if (!type.equals("1")) {
				System.out.println("not for all the people");
				return;
			}
			
			DB dbResult = mongo.getDatabase("db_muti_user");
			String tableName = "";
			if(channeltype.equals("5")) { // 全频道
				tableName = "t_access_page_all_channels";
			}
			else if(channeltype.equals("0")) { //男装频道
				tableName = "t_access_page_men_channel";
			}
			else if(channeltype.equals("1")) { //女装频道
				tableName = "t_access_page_women_channel";
			}
			else if(channeltype.equals("2")) { //时尚圈频道
				tableName = "t_access_page_fashtion_channel";
			}
			else {
				System.out.println("no find the channel ...");
			}

			DBCollection dbCollection = dbResult.getCollection(tableName);
			if (dbCollection == null) {
				dbCollection = dbResult.createCollection(tableName, null);
			}
			DBObject  dbObject = (DBObject) JSON.parse(result.toString());
			
			System.out.println(index + " times insert begin ...");
			dbCollection.insert(dbObject);
			System.out.println(index + " times insert end ...");
		
			index++;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
