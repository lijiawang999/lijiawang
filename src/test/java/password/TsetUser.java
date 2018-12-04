package password;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

import redis.clients.jedis.Tuple;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TsetUser {
	@Autowired
	UserService us;
	 /**
	  * 1.检测登录
	  */

   public void testLogin(){
	   QueryUser query=new QueryUser();
	   query.setLoginname("libai999");
	   query.setPassword("w123456");
	try {
		TUser user = us.chkLogin(query);
		System.out.println(user.getId());
		 System.out.println(user.getRealname()+"==部门为"+user.getDept().getDname());
		 for(TPermission per:user.getPermission()){
			
			 System.out.println(per.getPname());
		 }
		 System.out.println("---------------------------");
		 //遍历一级菜单 容器是TUser里的meau
		 for (TPermission per1:user.getMeau()) {
			System.out.println(per1.getPname());
			//遍历二级菜单 容器是TPermission里的Children
			for (TPermission per2:per1.getChildren()) {
				System.out.println("\t"+per2.getPname());
			}
		}
	} catch (AppException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getErrMsg());
	}
	  

   }
  /**
   * 2.条件查询
   */
 
   public void testQuery(){
	   QueryUser query=new QueryUser();
	   query.setLoginname("test1234");
	   query.setPassword("81DC9BDB52D04DC20036DBD8313ED055");	  
	   List<TUser> list;
	
		list = us.queryByCon(query);
		 for (TUser user:list) {
				System.out.println(user.getRealname());
			}
	
	  
	   
	   
   }
    /**
     * 3.批量删除
     */
   @Test
   public void testDel(){
	   int [] ids={};		
			try {
				us.deleteByIds(ids);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getErrMsg());
			}
		
	
   }

/**
 * 4.修改
 */  
   public void testUpdate(){
	   TUser user =new TUser();
	   user.setId(163);
	  // TDep dep=new TDep();
	   //dep.setId(6);
	   //user.setDept(dep);
	  user.setPassword("163");
	   try {
		us.update(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	   	
   }
   /**
    * 5.新增
    */
   
   public void testAdd(){
	   TUser user =new TUser();
	   user.setLoginname("adhmin");
	   user.setPassword("1111");
	   user.setSex("女");
	   user.setBirthday(new Date());
	   user.setEmail("libai@163.com");
	   TDep dep=new TDep();
	   dep.setId(6);
	   user.setDept(dep);
	   user.setRealname("李白白");
	   user.setCreator(1000);
	   user.setPic("xxx.jsp");
	  try {
		us.insert(user);
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   /**
    * 6.分页查询
    */
  
   public void testPage(){
	   QueryUser query=new QueryUser();
	     
	   List<TUser> list;
	
		list = us.queryByPage(query, 2);
		 for (TUser user:list) {
				System.out.println(user.getRealname());
			}
	
	  
   }
   /**
    * 7.查询总页数
    * 
    */
  
   public void testCount(){
	   QueryUser query=new QueryUser();
	  
		System.out.println("总页数"+us.queryCount(query));
	
   }
   /**
    * 8.查询一条
    */
 
   public void testQone(){
	  
	
		TUser  user = us.queryById(909);
		if (user!=null) {
			 System.out.println(user.getRealname());
		} else {
           System.out.println("没找到");
		}
		
	
	  
   }
   /**
    * 9.删除一条
    */
  
    public void testDone(){
    	 try {
			us.deleteById(164);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
}
