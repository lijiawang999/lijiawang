package com.zs.pms.service;

import java.util.Map;

public interface HtmlService {
	public void genHtml(String filename,String ftlname,Map<String, Object> data);

}
