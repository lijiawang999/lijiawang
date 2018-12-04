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

import com.zs.pms.po.TChannel;


import com.zs.pms.service.ChannelService;

import com.zs.pms.vo.QueryChannel;


@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TestChannel {
  @Autowired
  ChannelService cs;
  /**
   * 1.条件查询
   */
 
	public void testQuery(){
          QueryChannel query=new QueryChannel();
          query.setPid(1);
          List<TChannel> channel=cs.queryByCon(query);
          for (TChannel channel1:channel ) {
			System.out.println(channel1.getCname());
			
		}
          
	}
	/**
	    * 6.分页查询和7.总页数
	    */
	
	   public void testPage(){
		QueryChannel query=new QueryChannel();
		//query.setCname("体育栏目");
		 System.out.println("总页数"+cs.queryCount(query));
		List<TChannel> channel=cs.queryByPage(query,1);
			 for (TChannel aa:channel) {			
					 System.out.println(aa.getCname()+aa.getChannel().getCname());					
				}
		
		  
	   }
	   
	   /**
	    * 9.删除一条
	    */
	  @Test
	    public void testDone(){
	    	 try {
				cs.deleteById(1);				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getErrMsg());
			}
	    }
}
