package com.ubas.webservice.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.ubas.rpc.ClassManager;
import com.ubas.util.JSONUtil;
import com.ubas.webservice.interfaces.PageAccessAnaly;

@WebService(name = "PageAccessAnaly", serviceName = "PageAccessAnaly", targetNamespace = "targetNamespace")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class PageAccessAnalyImpl implements PageAccessAnaly {

	@Override
	public String getAccessPage(String parameter) {
		return ClassManager.getPageAccessAnalyDAO().getAccessPage(JSONUtil.jsonToObject(parameter));
	}

}
