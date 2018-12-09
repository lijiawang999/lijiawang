package pms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestFk {
     @Test
	public void testGo(){
        Configuration conf=new Configuration();
		
		try {
			//创建模板
			Template temp=conf.getTemplate("/FreeMarker.html");
			//创建数据
			Map<String, String> map=new HashMap<>();
			map.put("name", "娜娜");
			//创建HTML文件
			File file=new File("d:/hello100.html");
			//构建文件输出流，以utf-8的形式写入
			Writer out=new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			//将数据已模板的方式写入新的文件中
			temp.process(map, out);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}

