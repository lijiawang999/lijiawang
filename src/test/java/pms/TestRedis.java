package pms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TUser;

import redis.clients.jedis.Jedis;
@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TestRedis {
   @Autowired
   RedisTemplate rs;
	 public void testGo(){
		 Jedis jedis=new Jedis("127.0.0.1",6379);
		 //jedis.set("J2", "李佳旺");
		 System.out.println(jedis.get("J2"));
	 }
	@Test
	 public void testObj(){
		 //村对象
		 TUser user=new TUser();
		 user.setId(365);
		 user.setRealname("zhaoyun");
		 //设置对象
		 rs.opsForValue().set("tank", user);
		 //输出
		 TUser user2=(TUser) rs.opsForValue().get("tank");
		 System.out.println(user2);
		 System.out.println(user2.getRealname());
		 
	 }
}
