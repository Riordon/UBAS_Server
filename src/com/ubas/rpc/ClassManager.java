package com.ubas.rpc;

import com.ubas.dao.PageAccessAnalyDAO;

public class ClassManager {
	private static PageAccessAnalyDAO pageAccessAnalyDAO;
	
	public static PageAccessAnalyDAO getPageAccessAnalyDAO() {
		if (pageAccessAnalyDAO == null) {
			pageAccessAnalyDAO = new PageAccessAnalyDAO();
		}
		return pageAccessAnalyDAO;
	}
}
