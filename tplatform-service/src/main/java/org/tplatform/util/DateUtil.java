package org.tplatform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * Created by Tianyi on 2014/11/20.
 */
public class DateUtil {

  public static final String FORMAT_DATE = "yyyy-MM-dd";
  public static final String FORMAT_TIME = "HH:mm:ss";
  public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
  public static final String FORMAT_DATE_SHORT = "yyyyMMdd";
  public static final String FORMAT_DATETIME_SHORT = "yyyyMMddHHmmss";

  public static final String VERSION_CODE = "yyyyMMddHHmmss";

  public static final String FORMAT_DATE_M = "yyyy-MM-dd HH:mm";

  public static final Calendar calendar = Calendar.getInstance();

  //工具类禁止实例化
  private DateUtil() {
  }

  public static String getCurrentDate(String style) {
    style = style == null ? "yyyy-MM-dd HH:mm:ss" : style;
    return new SimpleDateFormat(style).format(getCurrentDate());
  }

  public static String getYesterday(String style) {
    style = style == null ? "yyyy-MM-dd HH:mm:ss" : style;
    return new SimpleDateFormat(style).format(getDiffDay(-1));
  }

  public static Date getCurrentDate() {
    Date date = Calendar.getInstance().getTime();
    return date;
  }

  public static int getCurrentHour() {
    return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
  }

  public static int getHour(int hourDiff) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR_OF_DAY, hourDiff);
    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  public static Date getDiffHour(int hourDiff) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR_OF_DAY, hourDiff);
    return calendar.getTime();
  }

  public static Date getDiffSecond(int secondDiff) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.SECOND, secondDiff);
    return calendar.getTime();
  }

  public static Date getDiffDay(int dayDiff) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, dayDiff);
    return calendar.getTime();
  }

  public static Date getDiffMonth(int monthDiff) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, monthDiff);
    return calendar.getTime();
  }

  public static Date getDiffYear(int yearDiff) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, yearDiff);
    return calendar.getTime();
  }

  public static String getDiffMonth(int monthDiff, String style) {
    return new SimpleDateFormat(style).format(getDiffDay(monthDiff));
  }

  public static Date getTimeInMillis(Long millis) {
    Date date = new Date(Calendar.getInstance().getTimeInMillis() + millis);
    return date;
  }

  public static String format(Date date, String style) {
    return new SimpleDateFormat(style).format(date);
  }

  public static Date parse(String date, String style) throws ParseException {
    return new SimpleDateFormat(style).parse(date);
  }

  public static long getTimeInMillis() {
    return Calendar.getInstance().getTimeInMillis();
  }

  public static boolean isBefore(Date date) {
    return DateUtil.getCurrentDate().before(date);
  }

  public static boolean isAfter(Date date) {
    return DateUtil.getCurrentDate().after(date);
  }

  public static String getWeekDay(String date, String style) throws ParseException {
    Date datestyle = parse(date, style);
    Calendar ca = Calendar.getInstance();
    ca.setTime(datestyle);
    int weekDay = ca.get(Calendar.DAY_OF_WEEK);
    String week = "";
    switch (weekDay) {
      case 1:
        week += "星期日";
        break;
      case 2:
        week += "星期一";
        break;
      case 3:
        week += "星期二";
        break;
      case 4:
        week += "星期三";
        break;
      case 5:
        week += "星期四";
        break;
      case 6:
        week += "星期五";
        break;
      case 7:
        week += "星期六";
        break;
      default:
        week += "星期一";
    }
    return week;
  }

  /**
   * @return 系统当前时间  long
   */
  public static long getTime() {
    Date dt = new Date();
    return dt.getTime();
  }

}
