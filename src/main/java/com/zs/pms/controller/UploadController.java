package com.zs.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.zs.pms.utils.Constants;

import net.fckeditor.response.UploadResponse;

@Controller
public class UploadController {
	/**
	 * 普通文件上传
	 * @param file
	 * @param req
	 * @return 新文件名
	 */
    @RequestMapping("/upload/common.do")
    @ResponseBody
	public String uploadFile(MultipartFile file,HttpServletRequest req){
    	//获得upload文件夹的物理路径
    	String path=req.getRealPath("/upload");
    	//创建新文件名 目标文件
    	//通过uuid算法 随机生成32码
    	//字段根据网卡时间等信息自动生成绝不重复的32位码
    	UUID uuid =UUID.randomUUID();
    	//目标文件名 32位码加上文件后缀（源文件的原生文件名）
    	String destfilename=uuid.toString()+file.getOriginalFilename();
    	//创建新文件 物理路径/新文件名
    	File destfile=new File(path+File.separator+destfilename);
    	try {
    		//将原始文件拷贝到新文件
			file.transferTo(destfile);
			//返回新文件名
			return destfilename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "MSG";
		}
		
		
	}
    
    /**
     * 上传图片服务器
     * 
     */
    @RequestMapping("/upload/service.do")
    @ResponseBody
    public String uploadService(MultipartFile file){
    	//创建新文件名 目标文件
    	//通过uuid算法 随机生成32码
    	//字段根据网卡时间等信息自动生成绝不重复的32位码
    	UUID uuid =UUID.randomUUID();
    	//目标文件名 32位码加上文件后缀（源文件的原生文件名）
    	String destfilename=uuid.toString()+file.getOriginalFilename();
    	//调用jersey服务
    	Client client=new Client();
    	//图片服务器路径+文件名
    	WebResource wr=client.resource(Constants.PICSERVICE+destfilename);
    	
    	try {
    		//利用webservice写入图片
			wr.put(String.class, file.getBytes());
			//返回完整路径
			return Constants.PICSERVICE+destfilename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} 
    	
    	
    }
    
    
    
    @RequestMapping("/upload/fck.do")
	/**
	 * 通过fckeditor上传到图片服务器上
	 * @param req
	 * @param resp
	 */
	public void uploadFCK(HttpServletRequest req,HttpServletResponse resp){
    	//强转request
    	MultipartHttpServletRequest mr=(MultipartHttpServletRequest) req;
		
		//获得上传文件
		Map<String, MultipartFile> map=mr.getFileMap();
		//遍历map
		Set keys=map.keySet();
	
		Iterator<String> its=keys.iterator();
		while(its.hasNext()){
			MultipartFile file=map.get(its.next());
			//上传文件
			String path=uploadService(file);
			//上传成功
			if (!"error".equals(path)) {
				UploadResponse ur=UploadResponse.getOK(path);
				try {
					resp.getWriter().print(ur);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	

}
