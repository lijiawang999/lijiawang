package com.zs.pms.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TProduct;
import com.zs.pms.po.TSku;
import com.zs.pms.po.TUser;
import com.zs.pms.service.HtmlService;
import com.zs.pms.service.ProductService;
import com.zs.pms.service.RedisService;
import com.zs.pms.service.SkuService;

@Controller
public class ProductController {
  @Autowired
  RedisService rs;
  @Autowired
  ProductService ps;
  @Autowired
  SkuService ss;
  @Autowired
	HtmlService htmlService; 
  //// 按照ac.xml 的bean的id 找到对象
 
 
  /**
   * 新增页面 从redis中取出放松到页面
   * @param map
   * @return
   */
  @RequestMapping("/product/toadd.do")
  public String toAdd(ModelMap map){
	  //商品类型上衣
	  map.addAttribute("TYPES", rs.getTypes(1));
	  //品牌
	  map.addAttribute("BRANDS", rs.getBrands());
	  //材质
	  map.addAttribute("FEATURES", rs.getTCodes("F"));
	  //颜色
	  map.addAttribute("COLORS", rs.getTCodes("C"));
	  //尺码
	  map.addAttribute("SIZES", rs.getTCodes("S"));
	  return "product/add";
	  
  }
  /**
   * 查询列表
   * @param map
   * @return
   */
  @RequestMapping("/product/list.do")
  public String list(ModelMap map){
	 List<TProduct> product=ps.queryAll();
	  map.addAttribute("PRODUCT", product);
	  return "product/list";
  }
	
  /**
   * 新增
   */
  @RequestMapping("/product/add.do")
  public String add(TProduct product,ModelMap map,HttpSession session){
	  try {
		  TUser cu=(TUser) session.getAttribute("USER");
		  product.setCreator(cu.getId());
		  product.setStatus("");
		ps.addProduct(product);
		return "redirect:list.do";
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return toAdd(map);
	}
	
	  
  }
  
  /**
   * 库存表
   */
  @RequestMapping("/product/sku.do")
  public String listsku(int pid,ModelMap map){
	 List<TSku> list=ss.queryByPid(pid);
	 for(TSku sku:list){
		 //设置颜色中文
		sku.setColorTxt(rs.getCodeName("C", sku.getColor()));
		 sku.setSizTxt(rs.getCodeName("S", sku.getSiz()));
		 sku.setFeatureTxt(rs.getCodeName("F", sku.getFeature()));
	 }
	 map.addAttribute("LIST", list);
	  return "product/sku";
  }
  /**
   * 修改
   */
  @RequestMapping("/product/supdate.do")
  @ResponseBody
 public String updateSku(TSku sku){
	 ss.update(sku);
	 return "suc";
 }
  /**
   * 上架
   * @param id
   * @return
   */
  @RequestMapping("product/up.do")
  @ResponseBody
  public String up(int id){
	  //创建map对象
	  Map<String, Object> map = new HashMap<>(); 
	  //获得指定产品
	  TProduct product=ps.queryById(id);
	  //获得指定产品的sku
	  List<TSku> list1=ss.queryByPid(id);
	  //创建集合存放符合条件的sku
	  List<TSku> list2=new ArrayList<>();
	  
	  for (TSku sku:list1) {
		//售价不为0并且库存大于安全库存 存放点新集合中
		  if (sku.getSellcost()>0&&sku.getRecont()>sku.getSafcont()) {
			  //获得颜色名称
			sku.setColorTxt(rs.getCodeName("C", sku.getColor()));
			//添加到集合2中
			list2.add(sku);
			
		}
	}
	  //传商品信息 用于所在地
	  map.put("product", product);
	  //符合条件的sku列表
	  map.put("SLIST", list2);
	  //符合条件的sku列表第一个
	  map.put("sku", list2.get(0));
	        //文件名   模板名  数据 
	  htmlService.genHtml(String.valueOf(id)+".html", "product.html", map);
	  
	return "suc";
	  
  }
}
