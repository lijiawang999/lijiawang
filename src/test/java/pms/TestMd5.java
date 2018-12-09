package pms;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;

import com.zs.pms.utils.MD5;


/*
 * 测试md5
 */

public class TestMd5 {
	@Test
	public void testmd5(){
		MD5 md5=new MD5();
		System.out.println(md5.getMD5ofStr("w123456"));
	}
	

}
