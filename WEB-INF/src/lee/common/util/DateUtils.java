/*****************************************************************************
 * 파일명 : DateUtils.java
 * 패키지 명 : com.opms.framework.common.util;
 * 생성일 : 2011. 03. 11.
 * 작성자 : Administrator
 * ===========================================================================
 * 변경이력:
 * DATE             AUTHOR      DESCRIPTION
 * ---------------------------------------------------------------------------
 * 변경 이력은 이곳에 추가 합니다.
 *****************************************************************************/
package lee.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 날짜 관련 유틸리티
 *  - 기본적으로 Joda Time API(http://joda-time.sourceforge.net)를 사용
 *  - 양력/음력 변환은 IBM 패키지를 사용 (icu4j.jar)
 *
 * @author
 */

public class DateUtils {

  // 하루(1일)의 Millisecond
  public static final long MILLIS_PER_DAY = 86400000L;
  private static TimeZone timeZone;

  private DateUtils() {
  }

  static {
    try {
      timeZone = TimeZone.getTimeZone( "GMT+09:00" );
    } catch(Exception e) {
    }
  }

  private static DateTime getDateTime(String dateStr, String format) {

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    return fmt.parseDateTime(dateStr);

  }

  public static String getOgMuppet() {

    return "adsf";
  }

  /**
   * 날짜를 Long타입으로 리턴한다.
   *
   * @param int year
   * @param int month
   * @param int date
   * @return long
   */
  public static long getMillis(int year,int month,int day) {

    return (new DateTime(year, month, day, 0, 0, 0, 0)).getMillis();
  }

  /**
   * 날짜를 Long 타입으로 리턴한다.
   *
   * @param String dateStr
   * @param int format
   * @return String
   */
  public static long getMillis(String dateStr, String format) {

    return getDateTime(dateStr, format).getMillis();

  }

  /**
   * 날짜를 Date타입으로 리턴한다.
   *
   * @param int year
   * @param int month
   * @param int date
   * @return Date
   */
  public static Date getDate(int year,int month,int day) {

    return (new DateTime(year, month, day, 0, 0, 0, 0)).toDate();
  }

  /**
   * 날짜를 Date 타입으로 리턴한다.
   *
   * @param String dateStr
   * @param int format
   * @return long
   */
  public static Date getDate(String dateStr, String format) {

    return getDateTime(dateStr, format).toDate();
  }


  /**
   * 날짜를 Calendar 타입으로 리턴한다.
   *
   * @param int year
   * @param int month
   * @param int date
   * @return Calendar
   */
  public static Calendar getCalendar(int year,int month,int date) {

    return (new DateTime(year, month, date, 0, 0, 0, 0)).toCalendar(null);
  }

  /**
   * 날짜를 Calendar 타입으로 리턴한다.
   *
   * @param String dateStr
   * @param int format
   * @return Calendar
   */
  public static Calendar getCalendar(String dateStr, String format) {

    return getDateTime(dateStr, format).toCalendar(null);
  }

  /**
   * 시간에 대한 셋팅
   *
   * @param String format
   * @return String
   */
  public static String getTime(String format) {

    if(format == null || format.length() == 0)
      return "";

    DateTime dt = new DateTime();
    DateTimeFormatter fmt = null;

    if(format.equals("A")) {
      fmt = DateTimeFormat.forPattern("a");
      fmt = fmt.withLocale(Locale.ENGLISH);
    } else if(format.equals("a")) {
      fmt = DateTimeFormat.forPattern("a");
      fmt = fmt.withLocale(Locale.KOREA);
    } else {

      dt = new DateTime();
      format = format.replace("M", "m");
      format = format.replace("S", "s");
      fmt = DateTimeFormat.forPattern(format);

    }

    return fmt.print(dt);

  }

  /**
   * 오늘날짜 리턴
   * 예) getToday("yyyy-MM-dd a hh:mm:ss") ==> 2007-06-28 오후 05:37:46
   * @param String format
   * @return String
   */
  public static String getToday(String format) {

    if(format == null || format.length() == 0)
      return "";

    DateTime dt = new DateTime();
    format = format.replace("Y", "y");
    format = format.replace("D", "d");
    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

    return fmt.print(dt);

  }

  /**
   * 오늘날짜 리턴(YYYYMMDD)
   * @return java.lang.String
   */
  public static String getToday() {

    return getToday("YYYYMMDD");
  }

  /**
   * 문자열 년월 포멧으로 리턴
   * 예) getStringToMonth("200909","yyyy-MM") ==> 2009-09
   * @param String date, String format
   * @return String
   */
  public static String getStringToMonth(String date, String format) {
    if(format == null || format.length() == 0)
      return "";

    if(date == null || date.equals("") || date.length() != 6)
      return date;

    int year = Integer.parseInt(date.substring(0,4));
    int month = Integer.parseInt(date.substring(4,6));

    DateTime dt = new DateTime(year, month, 1, 0, 0, 0, 0);

    format = format.replace("Y", "y");
    format = format.replace("D", "d");
    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

    return fmt.print(dt);
  }

  /**
   * 문자열 날짜 포멧으로 리턴
   * 예) getStringToDate("20090902","yyyy-MM-dd") ==> 2009-09-02
   * @param String date, String format
   * @return String
   */
  public static String getStringToDate(String date, String format) {
    if(format == null || format.length() == 0)
      return "";

    if(date == null || date.equals("") || date.length() != 8)
      return date;

    int year = Integer.parseInt(date.substring(0,4));
    int month = Integer.parseInt(date.substring(4,6));
    int day = Integer.parseInt(date.substring(6,8));

    DateTime dt = new DateTime(year, month, day, 0, 0, 0, 0);

    format = format.replace("Y", "y");
    format = format.replace("D", "d");
    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

    return fmt.print(dt);
  }

  /**
   * 문자열 날짜 포멧으로 리턴
   * 예) getStringToDate("2009-09-02","yyyy-MM-dd") ==> 2009-09-02
   * @param String date, String format
   * @return String
   */
  public static String getStringToDate2(String date, String format) {
    if(format == null || format.length() == 0)
      return "";

    if(date.length() != 10)
      return date;

    int year = Integer.parseInt(date.substring(0,4));
    int month = Integer.parseInt(date.substring(5,7));
    int day = Integer.parseInt(date.substring(8,10));

    DateTime dt = new DateTime(year, month, day, 0, 0, 0, 0);

    format = format.replace("Y", "y");
    format = format.replace("D", "d");
    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

    return fmt.print(dt);
  }

  /**
   * 문자열 날짜 포멧으로 리턴
   * 예) getStringToHanDate("20090902","yyyy년 MM월 dd일") ==> 2009년 09월 02일
   * @param String date
   * @return String
   */
  public static String getStringToHanDate(String date) {

    if(date.equals("")) date = getToday();

    StringBuffer sb = new StringBuffer();
    if(date.length() != 8)
      return date;

    String year = date.substring(0,4);
    String month = date.substring(4,6);
    String day = date.substring(6,8);

    sb.append(year).append("년 ").append(month).append("월 ").append(day).append("일");

    return sb.toString();
  }

  /**
   * 문자열 날짜 포멧으로 리턴
   * 예) getStringToHanDate(1) ==> 2009년 09월
   * @param String date
   * @return String
   */
  public static String getStringToHanMm(int value) {

    StringBuffer sb = new StringBuffer();
    String date = plusMonths(value);

    String year = date.substring(0,4);
    String month = date.substring(4,6);

    sb.append(year).append("년 ").append(month).append("월 ");

    return sb.toString();
  }

  /**
   * 현재요일을 리턴한다.
   *
   * @param Locale locale Locale.ENGLISH, Locale.KOREA
   * @param boolean flag true = 짤은 요일명, false = 긴 요일명
   * @return String
   */
  public static String getWeekText(Locale locale, boolean flag) {

    DateTime dt = new DateTime();

    DateTime.Property pDoW = dt.dayOfWeek();

    if(flag)
      return pDoW.getAsShortText(locale);
    else
      return pDoW.getAsText(locale);
  }


  /**
   * 지정된 날짜에 대한 현재요일을 리턴한다.
   * 지정된 날짜의 형식은 반드시 "yyyyMMdd"를 지켜야 한다.
   *
   * @param String date
   * @param Locale locale Locale.ENGLISH, Locale.KOREA
   * @param boolean flag true = 짤은 요일명, false = 긴 요일명
   * @return String
   */
  public static String getWeekText(String date, Locale locale, boolean flag) {
    DateTime dt = getDateTime(date, "yyyyMMdd");
    DateTime.Property pDoW = dt.dayOfWeek();

    if(flag)
      return pDoW.getAsShortText(locale);
    else
      return pDoW.getAsText(locale);
  }

  /**
   * 현재요일을 숫자를 리턴한다.
   *
   * @param String date
   * @return int [1.일 ... 7.토]
   */
  public static int getWeek(String date) {

    DateTime dt = getDateTime(date, "yyyyMMdd");

    return dt.getDayOfWeek()%7 +1;
  }

  /**
   * 현재요일을 한글로 리턴한다.
   *
   * @param String date
   * @return int [1.일 ... 7.토]
   */
  public static String getWeekHan(String date) {

    String str = "";

    date = StringUtil.replace(date, ".", "");
    date = StringUtil.replace(date, "-", "");

    DateTime dt = getDateTime(date, "yyyyMMdd");

    int val = dt.getDayOfWeek()%7 +1;

    if(val == 1) str = "일";
    if(val == 2) str = "월";
    if(val == 3) str = "화";
    if(val == 4) str = "수";
    if(val == 5) str = "목";
    if(val == 6) str = "금";
    if(val == 7) str = "토";

    return str;
  }

  /**
   * 오늘부터 year, month, day까지의 날짜 수
   *
   * @param int year
   * @param int month
   * @param int day
   * @return int
   */
  public static int daysBetween(int year, int month, int day)	{

    DateTime dt = new DateTime();
    DateTime sdt = new DateTime(year, month, day, 0, 0, 0, 0);

    return Days.daysBetween(sdt.toLocalDateTime(), dt.toLocalDateTime()).getDays();

  }

  /**
   * 두 날짜사이의 일수차이 구함
   *
   * @param int endDay
   * @param int startMonth
   * @param int startDay
   * @param int endYear
   * @param int endYear
   * @param int endYear
   * @return int
   */
  public static int daysBetween(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {

    DateTime sdt = new DateTime(startYear, startMonth, startDay, 0, 0, 0, 0);
    DateTime edt = new DateTime(endYear, endMonth, endDay, 0, 0, 0, 0);

    return Days.daysBetween(sdt.toLocalDateTime(), edt.toLocalDateTime()).getDays();
  }

  /**
   * 오늘부터 year, month, day까지의 날짜 수
   *
   * @param String date
   * @return int
   */
  public static int daysBetween(String date)  	{

    if(date == null || date.length() != 8)
      return -1;

    return daysBetween(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(4,6)), Integer.parseInt(date.substring(6)));
  }

  /**
   * 두 날짜사이의 일수차이 구함
   *
   * @param String startDate
   * @param String endDate
   * @return int
   */
  public static int daysBetween(String startDate, String endDate) {

    int year = Integer.parseInt(startDate.substring(0,4));
    int month = Integer.parseInt(startDate.substring(4,6));
    int day =  Integer.parseInt(startDate.substring(6,8));

    int year2 = Integer.parseInt(endDate.substring(0,4));
    int month2 = Integer.parseInt(endDate.substring(4,6));
    int day2 =  Integer.parseInt(endDate.substring(6,8));

    return daysBetween(year,month,day,year2,month2,day2);
  }

  /**
   * 달의 날 수를 계산한다.(해당년 해당월의 말일을 구해서 리턴)
   * @param int year
   * @param int month
   * @return int
   */
  public static int getMonthDays(int year, int month) {

    DateTime dt = new DateTime(year, month+1, 1, 0, 0, 0, 0);
    dt = dt.minusDays(1);
    return dt.getDayOfMonth();
  }


  /**
   * 오늘부터 year 만큼의 년을 뺀 날짜 리턴
   * @param int year
   * @return int
   */
  public static String minusYears(int year)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.minusYears(year);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 month 만큼의 월을 뺀 날짜 리턴
   * @param int month
   * @return int
   */
  public static String minusMonths(int month)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.minusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 month 만큼의 월을 뺀 날짜 리턴
   * @param int month
   * @return int
   */
  public static String minusMonths(int month, String format)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = new DateTime();
    dt = dt.minusMonths(month);
    return fmt.print(dt);
  }


  /**
   * 오늘부터 week 만큼의 주을 뺀 날짜 리턴
   * @param int week
   * @return int
   */
  public static String minusWeeks(int week)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.minusWeeks(week);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 day 만큼의 날짜를 뺀 날짜 리턴
   * @param int day
   * @return int
   */
  public static String minusDays(int day, String format)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = new DateTime();
    dt = dt.minusDays(day);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 day 만큼의 날짜를 뺀 날짜 리턴
   * @param int day
   * @return int
   */
  public static String minusDays(int day)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.minusDays(day);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 year 만큼의 년을 뺀 날짜 리턴
   * @param String date
   * @param int year
   * @return int
   */
  public static String minusYears(String date, int year)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.minusYears(year);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 month 만큼의 월을 뺀 날짜 리턴
   * @param String date
   * @param int month
   * @return int
   */
  public static String minusMonths(String date, int month)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.minusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 week 만큼의 주을 뺀 날짜 리턴
   * @param String date
   * @param int week
   * @return int
   */
  public static String minusWeeks(String date, int week)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.minusWeeks(week);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 day 만큼의 날짜를 뺀 날짜 리턴
   * @param String date
   * @param int day
   * @return int
   */
  public static String minusDays(String date, int day)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.minusDays(day);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 year 만큼의 년을 더한 날짜 리턴
   * @param int year
   * @return int
   */
  public static String plusYears(int year)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.plusYears(year);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 month 만큼의 월을 더한 날짜 리턴
   * @param int month
   * @return int
   */
  public static String plusMonths(int month)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.plusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 month 만큼의 월을 더한 날짜 리턴
   * @param int month
   * @return int
   */
  public static String plusMonths(int month, String format)	{

    format = format.replace("Y", "y");
    format = format.replace("D", "d");

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = new DateTime();
    dt = dt.plusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 해당일자부터 month 만큼의 월을 더한 날짜 리턴
   * @param String date
   * @param int month
   * @param String format
   * @return int
   */
  public static String plusMonths(String date, int month, String format)	{

    format = format.replace("Y", "y");
    format = format.replace("D", "d");

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 week 만큼의 주을 더한 날짜 리턴
   * @param int week
   * @return int
   */
  public static String plusWeeks(int week)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.plusWeeks(week);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 day 만큼의 날짜를 더한 날짜 리턴
   * @param int day
   * @return int
   */
  public static String plusDays(int day)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = new DateTime();
    dt = dt.plusDays(day);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 day 만큼의 날짜를 더한 날짜 리턴
   * @param String date
   * @param int day
   * @return int
   */
  public static String plusDays(int day, String format)	{

    format = format.replace("Y", "y");
    format = format.replace("D", "d");

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = new DateTime();
    dt = dt.plusDays(day);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 day 만큼의 날짜를 더한 날짜 리턴
   * @param String date
   * @param int day
   * @return int
   */
  public static String plusDays(String date, int day, String format)	{

    format = format.replace("Y", "y");
    format = format.replace("D", "d");

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusDays(day);
    return fmt.print(dt);
  }

  /**
   * 오늘부터 month 만큼의 월을 더한 날짜 리턴
   * @param int month
   * @return int
   */
  public static String plusMonthDays(String date, int month, String format)	{

    format = format.replace("Y", "y");
    format = format.replace("D", "d");

    DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 year 만큼의 년을 더한 날짜 리턴
   * @param String date
   * @param int year
   * @return int
   */
  public static String plusYears(String date, int year)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusYears(year);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 month 만큼의 월을 더한 날짜 리턴
   * @param String date
   * @param int year
   * @return int
   */
  public static String plusMonths(String date, int month)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusMonths(month);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 week 만큼의 주을 더한 날짜 리턴
   * @param String date
   * @param int year
   * @return int
   */
  public static String plusWeeks(String date, int week)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusWeeks(week);
    return fmt.print(dt);
  }

  /**
   * 특정날짜로부터 day 만큼의 날짜를 더한 날짜 리턴
   * @param String date
   * @param int day
   * @return int
   */
  public static String plusDays(String date, int day)	{

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    DateTime dt = getDateTime(date, "yyyyMMdd");
    dt = dt.plusDays(day);
    return fmt.print(dt);
  }

  /**
   * 특정년월의 마지막 날짜를 리턴
   * @param int year
   * @param int month
   * @return int
   */
  public static int lastDay(int year, int month)	{

    int arrMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    // 윤년
    if ( (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0) ) {
      arrMonth[2] = 29;
    } else {
      arrMonth[2] = 28;
    }

    return arrMonth[month];
  }

  public static String[] chkWeek()	{

    String str[] = new String[4];
    String low_sdate = "";
    String low_edate = "";
    String prev_sdate = "";
    String prev_edate = "";
    String date = getToday();

    low_edate = plusDays(date,-1);

    int week = getWeek(date);
    if(week <= 5) low_sdate = plusDays(date,-(week+2));
    else if (week == 6) low_sdate = plusDays(date,-1);
    else if (week == 7) low_sdate = plusDays(date,-2);

    prev_sdate = plusDays(low_sdate,-7);
    prev_edate = plusDays(low_edate,-7);

    str[0] = prev_sdate;
    str[1] = prev_edate;
    str[2] = low_sdate;
    str[3] = low_edate;

    return str;

  }

  public static String getCalSdate()	{

    String date = getToday("YYYYMM") + "01";
    return plusMonths(date, -1, "YYYY.MM.DD");
  }

  public static String getCalEdate()	{

    return getToday("YYYY.MM.DD");
  }

  /**
   * 현재 날짜를 반환한다(Date type).
   *
   * @return 현재 날짜
   */
  public static Date getDate() {

    Calendar cal = Calendar.getInstance( timeZone, Locale.KOREAN );

    return cal.getTime();
  }

  /**
   * 현재 날짜에서 offset(sec)만큼 더한 날짜를 반환한다.
   * offset은 초(sec)단위이며 이전 날짜를 계산할 경우 음수 값도 가능하다.
   *
   * @param offset 현재 날짜에서의 offset
   * @return 현재 날짜
   */
  public static Date getDate( long offset ) {

    Calendar cal = Calendar.getInstance( timeZone, Locale.KOREAN );
    cal.setTime( new Date( cal.getTime().getTime() + (offset*1000) ) );

    return cal.getTime();
  }

  /**
   * 특정 날짜에서 offset(sec)만큼 더한 날짜를 반환한다.
   * offset은 초(sec)단위이며 이전 날짜를 계산할 경우 음수 값도 가능하다.
   *
   * @param date 특정 날짜
   * @param offset 현재 날짜에서의 offset
   * @return 현재 날짜
   */
  public static Date getDate( Date date, long offset ) {

    return new Date( date.getTime() + (offset*1000) );
  }

  /**
   * format에 맞추어 현재 날짜를 String으로 반환한다.
   * (예 : "yyyy-MM-dd HH:mm:ss:SSS")
   *
   * @param format 날짜의 format
   * @return       현재 날짜
   */
  public static String getDateString( String format ) {

    SimpleDateFormat simpleFormat = new SimpleDateFormat( format );
    simpleFormat.setTimeZone( timeZone );
    return simpleFormat.format( getDate() );
  }

  /**
   * "yyyy-MM-dd HH:mm:ss"형식의 현재 날짜를 String으로 반환한다.
   *
   * @return       현재 날짜
   */
  public static String getDateString() {
    return getDateString( "yyyy-MM-dd HH:mm:ss" );
  }

  /**
   * format에 맞추어 현재 날짜를 long 타입 숫자로 반환한다.
   *
   * @param  format 날짜 형식
   * @return long   long형태의 현재 날짜
   */
  public static long getDateLong( String format ) {

    SimpleDateFormat simpleFormat = new SimpleDateFormat( format );
    simpleFormat.setTimeZone( timeZone );
    return Long.parseLong( simpleFormat.format( getDate() ) );
  }

  /**
   * "yyyyMMddHHmmss"형식의 현재 날짜를 long으로 반환한다.
   *
   * @return long   long형태의 현재 날짜
   */
  public static long getDateLong() {
    return getDateLong( "yyyyMMddHHmmss" );
  }

  /**
   * 특정 날짜를 반환한다(Date type).
   *
   * @param year   년
   * @param month  월
   * @param day    일
   * @param hour   시
   * @param minute 분
   * @param second 초
   * @return 현재 날짜 object
   */
  public static Date getDate( int year, int month, int day, int hour, int minute, int second ) {

    GregorianCalendar cal = new GregorianCalendar( timeZone, Locale.KOREAN );
    cal.set( year, month-1, day, hour, minute, second );
    return cal.getTime();
  }

  /**
   * format에 맞추어 주어진 날짜를 String으로 반환한다.(예 : "yyyy-MM-dd HH:mm:ss:SSS")
   *
   * @param date   String으로 표현할 날짜
   * @param format 날짜의 format
   * @return       String으로 변환된 날짜
   */
  public static String dateToString( Date date, String format ) {

    SimpleDateFormat simpleFormat = new SimpleDateFormat( format );
    simpleFormat.setTimeZone( timeZone );
    return simpleFormat.format( date );
  }

  /**
   * format에 맞추어 주어진 String을 Date로 변환한다.
   *
   * @param dateString String으로 표현된 날짜
   * @param format     날짜의 format
   * @return           Date로 변환된 날짜
   * @exception ParseException Sring parsing 에러 발생시
   */
  public static Date stringToDate( String dateString, String format ) throws Exception {

    SimpleDateFormat simpleFormat = new SimpleDateFormat( format );
    simpleFormat.setTimeZone( timeZone );
    return simpleFormat.parse( dateString );
  }

  /**
   * format에 맞추어 주어진 날짜를 long으로 반환한다.(예 : "yyyy-MM-dd HH:mm:ss:SSS")
   *
   * @param date   String으로 표현할 날짜
   * @param format 날짜의 format
   * @return       long으로 변환된 날짜
   */
  public static long dateToLong( Date date, String format ) {

    SimpleDateFormat simpleFormat = new SimpleDateFormat( format );
    simpleFormat.setTimeZone( timeZone );
    return Long.parseLong( simpleFormat.format( date ) );
  }

  /**
   * format에 맞추어 주어진 long 타입 숫자을 Date로 변환한다.
   *
   * @param     dateLong        Long으로 표현된 날짜
   * @param     format          날짜의 format
   * @return    Date            Date로 변환된 날짜
   * @exception ParseException  String parsing 에러 발생시
   */
  public static Date longToDate( long dateLong, String format ) throws Exception {

    SimpleDateFormat simpleFormat = new SimpleDateFormat( format );
    simpleFormat.setTimeZone( timeZone );
    return simpleFormat.parse( Long.toString( dateLong ) );
  }

  /**
   * format에 맞추어 주어진 long 타입 날짜(yyyyMMddHHmmss)를 String으로 변환한다.
   *
   * @param dateLong            Long으로 표현된 날짜
   * @param format              날짜의 format
   * @return String             String로 변환된 날짜
   * @throws ParseException
   */
  public static String longToString( long dateLong, String format)
  throws Exception {
    return dateToString( longToDate( dateLong, "yyyyMMddHHmmss"), format);
  }

  /**
   * 두 Date의 날짜 차이를 date1 - date2 방식으로 계산한다.
   *
   * @param date1 operand date 1
   * @param date2 operand date 2
   * @return      날짜 차이
   */
  public static int getAfterDays( Date date1, Date date2 ) {

    return (int)( ( date1.getTime()-date2.getTime() ) / 86400000 );
  }
  
  //날짜 형태 확인하기
  /**
   * 일자를 사용자가 정의한 형식에 의해 체크한다
   * @param s 체크하고자하는 date String
   * @param format 체크하고자 하는 형식 (예 : "yyyy-MM-dd")
   * @return boolean true 날짜 형식이 맞고, 존재하는 날짜일 때
   *                 false 날짜 형식이 맞지 않거나, 존재하지 않는 날짜일 때
   */
  public static boolean isValidDate(String s, String format) {
      java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
              format, java.util.Locale.KOREA);
              java.util.Date date = null;
              try {
                  date = formatter.parse(s);
              }
              catch (java.text.ParseException e) {
                  return false;
              }

              if (!formatter.format(date).equals(s))
                  return false;

              return true;
  }
  
  public static void main(String[] args) {
    //		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
    //		DateTime dt = fmt.parseDateTime("20070627");
    //    	System.out.println(dt.getMillis());
    //
    //		fmt = DateTimeFormat.forPattern("HH:mm:ss");
    //		dt = new DateTime();
    //		System.out.println("==>"+fmt.print(dt));
    //
    //    	System.out.println(StringUtils.leftPad("11", 2, "0"));
    //
    //		fmt = DateTimeFormat.forPattern("a");
    //		fmt = fmt.withLocale(Locale.KOREA);
    //		System.out.println(DateUtils.getTime("hh:MM:SS"));
    //
    //		String format = "YYYY";
    //		format = format.replace("Y", "y");
    //		format = format.replace("D", "d");
    //		fmt = DateTimeFormat.forPattern(format);
    //		System.out.println(format+":"+fmt.print(dt));
    //
    //		DateTime.Property pDoW = dt.dayOfWeek();
    //		System.out.println(pDoW.getAsShortText(Locale.KOREA)+":"+pDoW.getAsShortText(Locale.ENGLISH));
    //		System.out.println(pDoW.getAsText(Locale.KOREA)+":"+pDoW.getAsText(Locale.ENGLISH));
    //
    //		System.out.println("daysBetween-->"+daysBetween(2010, 8, 15));
    //		System.out.println(daysBetween(2010, 8, 11, 2010, 8, 12));
    //		System.out.println(daysBetween("20100812","20100815"));
    //
    //		DateTime tmp = new DateTime(2001, 1, 21, 0, 0, 0, 0);
    //		System.out.println((new DateTime()).getDayOfYear() - (new DateTime(2001, 1, 21, 0, 0, 0, 0)).getDayOfYear());
    //		System.out.println((new DateTime()).getDayOfYear() - (new DateTime(2001, 1, 21, 0, 0, 0, 0)).getDayOfYear());
    //
    //		System.out.println(Days.daysBetween(tmp.toLocalDateTime(), dt.toLocalDateTime()).getDays());
    //
    //		tmp = new DateTime(2009, 1, 1, 0, 0, 0, 0);
    //		System.out.println(tmp.getDayOfMonth());
    //		tmp = tmp.minusDays(1);
    //		System.out.println("-->"+tmp.getDayOfMonth());
    //
    //		System.out.println(DateUtils.minusDays(2));
    //		System.out.println(DateUtils.plusDays(3));
    //
    //
    //		System.out.println(DateUtils.minusDays("20070628", 2));
    //		System.out.println(DateUtils.plusDays("20070628", 3));
    //
    //
    //		tmp = new DateTime();
    System.out.println(getToday("yyyyMMddHHmmssSSS"));
    System.out.println(getToday("yyyy/MM/dd"));
    System.out.println( "====>" + daysBetween(getToday("yyyy/MM/dd"), "2011/08/31"));
    //
    //		System.out.println(getWeekText("20080601", Locale.KOREA, true));
    //		System.out.println(getWeek("20091001"));
    //		System.out.println(plusMonths("20090101",1));
    //		System.out.println(minusMonths("20090101",1));
    //		System.out.println(getStringToDate(plusMonths("20090101",1),"MM"));
    //		System.out.println(getStringToDate(minusMonths("20090101",1),"MM"));
    //    	String sdate = "20100321";
    //    	String edate = "20100501";
    //    	System.out.println(getStringToHanMm(1));
    //    	System.out.println(getStringToHanMm(5));
    //    	System.out.println(plusMonths(5,"YYYYMM"));
    //    	System.out.println(getWeekHan("20100830"));
    //    	System.out.println(getToday("yyyyMMddHHmmss"));

    //    	String date = "20100924";
    //
    //    	System.out.println(plusDays(date,-1));
    //
    //    	int week = getWeek("20100924");
    //
    //    	System.out.println(week);
    //    	System.out.println(getWeek("20100925"));
    //    	System.out.println(getWeek("20100926"));

    chkWeek();

  }

}
