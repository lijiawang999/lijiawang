package com.zs.pms.utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.print.CancelablePrintJob;
import javax.sound.midi.MidiChannel;

public class DateUtil {
	public static void showToday(){
		//日历类，获得当前时间，使用获得实例的方法来获得实例
		Calendar ca=Calendar.getInstance();
		//年，月 ，日，时。分，秒
		int yy=ca.get(Calendar.YEAR);
		int mm=ca.get(Calendar.MONTH)+1;
		int dd=ca.get(Calendar.DATE);
		int hh=ca.get(Calendar.HOUR_OF_DAY);
		int m=ca.get(Calendar.MINUTE);
		int ss=ca.get(Calendar.SECOND);
		//显示格式
		System.out.println(yy+"-"+mm+"-"+dd+"\t"+hh+":"+m+":"+ss);
		
		
	}
	/**
	 *  把Date转化字符串数据的方法 
	 * @param time  需要转化的 Date类数据
	 * @param pattern yy-MM-dd HH:mm:ss     y年 M 月 d 日  H 24小时制时间 h 12小时制时间  m分 s 秒
	 * @return 转化后的字符串
	 */
	
	public static String getDateToStr(Date time,String pattern){
		//创建一个日期格式化的对象，构造函数（格式化成什么样子）
	  DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	  return df.format(time);
	}
	/**
	 * 把字符串转成Date的方法
	 * @param date 需要转化的字符创类型数据
	 * @param pattern 字符串的格式
	 * @return 转化后的Date数据
	 * @throws ParseException 因为字符串不一定能转化成时间所以可能会产生异常
	 */
	public static Date getStrToDate(String date,String pattern) throws ParseException{
		//创建一个日期格式化的恩对象，构造函数
		DateFormat df=new SimpleDateFormat(pattern);
		//调用parse的方法可以把字符串数据转化Date类
		return df.parse(date);
	}	
	
	//调用显示方法
public static void main(String[] args) {
	DateUtil.showToday();
}
}
