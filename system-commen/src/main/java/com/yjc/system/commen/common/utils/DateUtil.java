/*
 *  Copyright (c) 2014 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.yjc.system.commen.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	
	private static final Logger logger = LoggerFactory
            .getLogger(DateUtil.class);

    public static final int DEFAULT = 0;

    public static final int YM = 1;

    public static final int YMR_SLASH = 11;

    public static final int NO_SLASH = 2;

    public static final int YM_NO_SLASH = 3;

    public static final int DATE_TIME = 4;

    public static final int DATE_TIME_NO_SLASH = 5;

    public static final int DATE_HM = 6;

    public static final int TIME = 7;

    public static final int HM = 8;

    public static final int LONG_TIME = 9;

    public static final int SHORT_TIME = 10;

    public static final int DATE_TIME_LINE = 12;

    /**
     * 时间格式化格式
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式化格式
     */
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

    /**
     * 使用线程  对象独享
     * 
     */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    /**
     * 当前时间
     */
    private static Calendar calendar = Calendar.getInstance();

    /**
     * 取当前时间的下N个小时
     * 
     * @param date
     */
    public static String getNextHourse(String date, Integer hourse) {

        calendar.setTime(parse(date));
        calendar.add(Calendar.HOUR, hourse);

        return formatDate(calendar.getTime());
    }

    /**
     * 获取当前时间的上N个小时时间
     * 
     * @param date
     * @return
     */
    public static String getLastHourse(String date, Integer hourse) {

        calendar.setTime(parse(date));
        calendar.add(Calendar.HOUR, -hourse);

        return formatDate(calendar.getTime());
    }

    /**
     * 获取当前时间的下N天时间
     * 
     * @param date
     * @return
     */
    public static String getNextDay(String date, Integer day) {

        calendar.setTime(parse(date));
        calendar.add(Calendar.DAY_OF_WEEK, day);

        return formatDate(calendar.getTime());
    }

    /**
     * 获取当前时间的上N天时间
     * 
     * @param date
     * @return
     */
    public static String getLastDay(String date, Integer day) {

        calendar.setTime(parse(date));
        calendar.add(Calendar.DAY_OF_WEEK, -day);

        return formatDate(calendar.getTime());
    }

    /**
     * 获取当前时间的下N月时间
     * 
     * @param date
     * @return
     */
    public static String getNextMonth(String date, Integer month) {

        calendar.setTime(parse(date));
        calendar.add(Calendar.MONTH, month);

        return formatDate(calendar.getTime());
    }

    /**
     * 获取当前时间的上N月时间
     * 
     * @param date
     * @return
     */
    public static String getLastMonth(String date, Integer month) {

        calendar.setTime(parse(date));
        calendar.add(Calendar.MONTH, -month);

        return formatDate(calendar.getTime());
    }

    public static String covertDateStr(Integer day) {

        String dayStr = "";
        if (day == 5) {
            dayStr = "星期五";
        } else if (day == 4) {
            dayStr = "星期四";
        } else if (day == 3) {
            dayStr = "星期三";
        } else if (day == 2) {
            dayStr = "星期二";
        } else if (day == 1) {
            dayStr = "星期一";
        } else if (day == 0) {
            dayStr = "星期日";
        } else if (day == 6) {
            dayStr = "星期六";
        }

        return dayStr;
    }

    /**
     * 获取时间格式化对象
     * @return
     */
    public static DateFormat getDateFormat() {

        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(df);
        }
        return new SimpleDateFormat(DATE_FORMAT);
    }

    /**
     * 根据指定格式格式化时间
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) {

        return getDateFormat().format(date);
    }

    /**
     * 根据指定格式解析时间
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) {

        try {
            return getDateFormat().parse(strDate);
        } catch (ParseException e) {
        	logger.error("转换异常");
        }
        return null;
    }

    public static String dateToStr(Date date, String pattern) {

        if ((date == null) ){
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String dateToStr(Date date) {

        return dateToStr(date, "yyyy/MM/dd");
    }

    public static String dateToStr(Date date, Integer type) {

        switch (type) {
            case 0:
                return dateToStr(date);
            case 1:
                return dateToStr(date, "yyyy/MM");
            case 2:
                return dateToStr(date, "yyyyMMdd");
            case 11:
                return dateToStr(date, "yyyy-MM-dd");
            case 3:
                return dateToStr(date, "yyyyMM");
            case 4:
                return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
            case 5:
                return dateToStr(date, "yyyyMMddHHmmss");
            case 6:
                return dateToStr(date, "yyyy/MM/dd HH:mm");
            case 7:
                return dateToStr(date, "HH:mm:ss");
            case 8:
                return dateToStr(date, "HH:mm");
            case 9:
                return dateToStr(date, "HHmmss");
            case 10:
                return dateToStr(date, "HHmm");
            case 12:
                return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
            case 13:
                return dateToStr(date, "yyyy-MM-dd HH:mm");
            case 14:
                return dateToStr(date, "yyyyMMddHHmmssSSS");
            default:
                break;
        }
        throw new IllegalArgumentException("Type undefined : " + type);
    }

    public static String getNowOfString() {

        return MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", System.currentTimeMillis());
    }

    public static String getToday() {

        return MessageFormat.format("{0,date,yyyy-MM-dd}", System.currentTimeMillis());
    }

    public static String getDateTimeByCurrentTime(Long currentTimeMills) {

        return MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", currentTimeMills);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getDate() {

        return new Date();
    }

    public static String getAllDate() {

        Calendar cd = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.CHINA);
        SimpleDateFormat sdf = new SimpleDateFormat("'GMT'+8  yyyy-MM-dd HH:mm", Locale.CHINA);
        // 设置时区为GMT
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = sdf.format(cd.getTime());
        return str;
    }

    public static Date setDateWithDateAndTime(String date, String time) {

        return parse(date + " " + time);
    }

    public static String fmtLong(Date date) {
        if (date != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        } else {
            return null;
        }
    }
    
    /**
     * 获取两个日期间隔分钟数，日期格式默认为yyyymmdd
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getIntervalForMins(String startDate,String endDate){
    	return getIntervalForMins(startDate,endDate,"yyyyMMdd");
    }
    
    /**
     * 获取两个日期间隔天数，
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getIntervalForMins(String startDate,String endDate,String pattern){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(strToDate(startDate, pattern));
    	long a = cal.getTimeInMillis();
    	cal.setTime(strToDate(endDate, pattern));
    	long b = cal.getTimeInMillis();
    	double mins = Math.ceil((double)(b-a)/(1000*60));
    	return (int)mins;
    }
    
    /**
     * 获取两个日期间隔天数，日期格式默认为yyyymmdd
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getIntervalForDays(String startDate,String endDate){
    	return getIntervalForDays(startDate,endDate,"yyyyMMdd");
    }
    
    /**
     * 获取两个日期间隔天数，
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getIntervalForDays(Date startDate,Date endDate,String pattern){
    	return getIntervalForDays(dateToStr(startDate, pattern), dateToStr(endDate, pattern), pattern);
    }

    /**
     * 获取两个日期间隔天数，
     * @param startDate
     * @param endDate
     * @return
     */
    public static double getIntervalForDaysDou(Date startDate,Date endDate,String pattern){
        return getIntervalForDaysDou(dateToStr(startDate, pattern), dateToStr(endDate, pattern), pattern);
    }
    
    /**
     * 获取两个日期间隔天数，
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getIntervalForDays(String startDate,String endDate,String pattern){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(strToDate(startDate, pattern));
    	long a = cal.getTimeInMillis();
    	cal.setTime(strToDate(endDate, pattern));
    	long b = cal.getTimeInMillis();
    	double days = Math.ceil((double)(b-a)/(1000*3600*24));
    	return (int)days;
    }

    /**
     * 获取两个日期间隔天数，
     * @param startDate
     * @param endDate
     * @return
     */
    public static double getIntervalForDaysDou(String startDate,String endDate,String pattern){
        Calendar cal = Calendar.getInstance();
        cal.setTime(strToDate(startDate, pattern));
        long a = cal.getTimeInMillis();
        cal.setTime(strToDate(endDate, pattern));
        long b = cal.getTimeInMillis();
        double days = Math.ceil((double)(b-a)/(1000*3600*24));
        return days;
    }


    /**
     * 根据日期及格式 转换成date类型
     * @param date
     * @param pattern
     * @return
     */
    public static Date strToDate(String date,String pattern){
    	try {
    		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        	return formatter.parse(date);
		} catch (Exception e) {
			logger.error("转换异常");
			return null;
		}
    }
    
    /**
     * 指定格式的日期 offset偏移量后的日期 
     * @param date
     * @param pattern
     * @param offset
     * @return
     */
    public static String offsetDay(String date,String pattern,int offset){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(strToDate(date, pattern));
    	cal.add(Calendar.DAY_OF_MONTH, offset);
    	return dateToStr(cal.getTime(), pattern);
    }
    
    /**
     * 指定格式的日期 offset偏移量后的日期 
     * @param date
     * @param pattern
     * @param offset
     * @return
     */
    public static String offsetMonth(Date date,String pattern,int offset){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, offset);
    	return dateToStr(cal.getTime(), pattern);
    }
    /**
     * 指定格式的日期 offset偏移量后的日期 
     * @param date
     * @param pattern
     * @param offset
     * @return
     */
    public static String offsetYear(Date date,String pattern,int offset){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.YEAR, offset);
    	return dateToStr(cal.getTime(), pattern);
    }
    
    /**
     * 获取一年中的第一天
     * @param date
     * @param
     * @param
     * @return
     */
    public static Date getYearFristDay(Date date){
    	String str = dateToStr(date, "yyyy")+"-01-01";
    	return strToDate(str, "yyyy-MM-dd");
    }
    /**
     * 获取一年中的第一月末
     * @param date
     * @param
     * @param
     * @return
     */
    public static Date getYearFristMonEnd(Date date){
    	String str = dateToStr(date, "yyyy")+"-01-31";
    	return strToDate(str, "yyyy-MM-dd");
    }

    /**
     * 时间转化
     * @param timeMillis
     * @return
     */
    public static String fmtLong(long timeMillis){
       return timeMillis==0?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeMillis);
    }

    /**
     * LocalDateTime 转 date
     * @param localDateTime
     * @return
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}