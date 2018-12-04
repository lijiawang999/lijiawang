package password;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TArtical;
import com.zs.pms.po.TChannel;
import com.zs.pms.po.TUser;
import com.zs.pms.service.ArticalService;
import com.zs.pms.vo.QueryArtical;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TestArtical {
  @Autowired
  ArticalService as;
  /**
   * 1.条件查询
   */
  
	public void testQuery(){
		QueryArtical query=new QueryArtical();
		query.setTitle("月下独酌1");
		
			List<TArtical> list=as.queryByCon(query);
			for(TArtical aa:list){
				System.out.println(aa.getContent());
			}
			
		
	}
  /**
   * 2.通过id查询
   */
 @Test 
  public void testQueryById(){
	    List<TArtical> ar=as.queryByChannel(5);
	    for (TArtical aa:ar) {
			System.out.println(aa.getTitle());
		}
	  
		//TArtical aa=as.queryById(2);
		//System.out.println(aa.getTitle()+aa.getChannel().getCname());
	
	  
	  
  }
  /**
   * 3.新增
   */
  
   public void testAdd(){
		TArtical artical=new TArtical();
		artical.setTitle("静夜思");
		artical.setContent("床前明月光，疑似地上霜");
		artical.setAuthor("李白");
		artical.setCtime(new Date());
		TChannel channel=new TChannel();
		channel.setId(5);
		artical.setChannel(channel);
		artical.setIsremod(1);
		artical.setIshot(-1);
		
			as.insert(artical);
		
		
   }
   /**
    * 4.修改
    */
  
   public void testUpdate(){
	   TArtical artical=new TArtical();
	   artical.setId(5);
	   artical.setAuthor("诗仙12");
	   artical.setUpdator("杜甫");
	   artical.setUptime(new Date());
	   TChannel channel=new TChannel();
	   channel.setId(5);
	   artical.setChannel(channel);
	   try {
		as.update(artical);
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   }
   /**
    * 5.删除一条
    */
  
   public void testDone(){
   	 try {
			as.deleteById(7);;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   /**
    * 6.分页查询和7.总页数
    */
 @Test
   public void testPage(){
	 QueryArtical query=new QueryArtical();
		 System.out.println("总页数"+as.queryCount(query));
		List<TArtical> list=as.queryByPage(query, 1);
		 for (TArtical aa:list) {
				System.out.println(aa.getTitle()+aa.getChannel().getCname());
			}
	
	  
   }
   /**
    * 8.批量删除
    */

   public void testDel(){
	   int [] ids={11};
	   try {
		as.deleteByIds(ids);
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
