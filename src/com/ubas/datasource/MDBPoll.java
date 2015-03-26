package com.ubas.datasource;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoOptions;

public class MDBPoll {
	public final String host;
	public final int port;
	public final MongoClient mongoClient;
	
	public MDBPoll(String host, int port) throws UnknownHostException {
		this.host = host;
		this.port = port;
		this.mongoClient = new MongoClient(host, port);
		
		MongoOptions opt = mongoClient.getMongoOptions();
		opt.connectionsPerHost = 10;
		opt.threadsAllowedToBlockForConnectionMultiplier = 10;
		opt.socketKeepAlive = true;
		opt.autoConnectRetry = true;
	}
	
	public DB getDatabase(String dbname) {
		return mongoClient.getDB(dbname);
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
}
