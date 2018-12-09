package com.zs.pms.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.zs.pms.service.HtmlService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

/**
 * 页面生成服务实现 实现 ServletContextAware 获得web环境
 * 
 * @author Administrator
 *
 */
public class HtmlServiceImpl implements HtmlService, ServletContextAware {

	// freemarker模版
	private FreeMarkerConfigurer fmc;

	// 获得web环境
	private ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public FreeMarkerConfigurer getFmc() {
		return fmc;
	}

	public void setFmc(FreeMarkerConfigurer fmc) {
		this.fmc = fmc;
	}

	@Override
	/**
	 * 生成页面 filename : 生成的文件名 ftlname: 使用的模版名 data:传入的数据
	 */
	public void genHtml(String filename, String ftlname, Map<String, Object> data) {
		// TODO Auto-generated method stub
		
		try {
			//获得模版
			Template template=fmc.getConfiguration().getTemplate(ftlname);
			//html生成位置
			String path=servletContext.getRealPath("/front");
			//新的html文件
			File newHtml=new File(path+File.separator+filename);
			// 以utf-8的形式构建文件输出流
			Writer out = new OutputStreamWriter(new FileOutputStream(newHtml), "utf-8");
			//将数据写入到文件输出流中
			template.process(data, out);
			//关闭输出流
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	

	}

}
