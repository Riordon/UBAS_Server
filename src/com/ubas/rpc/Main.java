package com.ubas.rpc;

import javax.xml.ws.Endpoint;

import com.ubas.util.Constants;
import com.ubas.webservice.impl.PageAccessAnalyImpl;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://" + Constants.WEBSERVICE_ADDR + ":"
				+ Constants.WEBSERVICE_PORT
				+ "/UbasServer/ClothingEPD/PageAccessAnaly",
				new PageAccessAnalyImpl());
	}

}
