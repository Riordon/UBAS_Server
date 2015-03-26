package com.ubas.execute;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.ubas.datasource.MDBPoll;
import com.ubas.util.Constants;

public class SingleUserJob implements Runnable {

	public void run() {

		MDBPoll mongo = null;
		try {
			mongo = new MDBPoll(Constants.MONGODB_ADDR, Constants.MONGODB_PORT);
			
			DB db = mongo.getDatabase("user_behavior_analysis");
			DBCollection dbCollection = db.getCollection("table1");
			
//			BasicDBObject searchQuery = new BasicDBObject();
//			searchQuery.put("username", "baise2014");
//			DBCursor dbCursor = dbCollection.find(searchQuery);
//			while (dbCursor.hasNext()) {
//				System.out.println(dbCursor.next());
//			}
			
			BasicDBObject searchQuery = new BasicDBObject();
			DBCursor dbCursor;
			
			String username;
			List list = dbCollection.distinct("username");
			int size = list.size();
			for (int i = 0; i <  size; i++) {
				username = list.get(i).toString();
				searchQuery.put("username", username);
				dbCursor = dbCollection.find(searchQuery);
				compute(username, dbCursor);
			}
			
			System.out.println("insert finished...");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private void compute(String username, DBCursor dbCursor) {
//		while (dbCursor.hasNext()) {
//			System.out.println(dbCursor.next());
//		}
		
		String result  = "{'database' : 'mkyongDB','table' : 'hosting'," +
			    			"'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

		try {
			MDBPoll mongo  = new MDBPoll(Constants.MONGODB_ADDR, Constants.MONGODB_PORT);
			DB db = mongo.getDatabase("result");
			
			DBObject dbObject = (DBObject) JSON.parse(result);
			if ((username == null) || (username == "")) return ;
			
			DBCollection dbCollection = db.getCollection(username);
			if (dbCollection == null) {
				db.createCollection(username, null);
			}
			
			dbCollection.insert(dbObject);
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
