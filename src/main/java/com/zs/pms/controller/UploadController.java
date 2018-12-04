package com.zs.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

}
