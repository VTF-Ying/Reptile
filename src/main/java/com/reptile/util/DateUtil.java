package com.reptile.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: Reptile
 * @ClassName: DateUtil
 * @Description: TODO(时间日期工具类)
 * @Author: VTF
 * @create: 2020-03-18 18:55
 */
public class DateUtil {

    private static void DateUtils() {
        }

        /**
         * 标准日期格式
         */
        private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        /**
         * 标准时间格式
         */
        private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        public static final String Date_YMD_HMS_Pattern = "yyyy-MM-dd HH:mm:ss";

        public static final String Date_YMD_HM_Pattern = "yyyy-MM-dd HH:mm";

        /**
         * ORA标准日期格式
         */
        private static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
        /**
         * ORA标准时间格式
         */
        private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");

        /**
         * 年-月日期格式
         */
        private static final SimpleDateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");

        /**
         * 带时分秒的ORA标准时间格式
         */
        private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        private static SimpleDateFormat sdf = new SimpleDateFormat();

        private final static String UTC_TIME_FORMATE="yyyy-MM-dd'T'HH:mm:ss'Z'";
        /**
         * 把日期字符串转换成日期
         *
         * @param dateString
         *            日期字符串 如："2004-02-12 12:30:10"
         * @param partten
         *            日期表达式 如："yyyy-MM-dd HH:mm:ss"
         * @return java.util.Date
         */
        public static Date StringToDate(String dateString, String partten) {
            SimpleDateFormat df = new SimpleDateFormat(partten);
            return df.parse(dateString, new ParsePosition(0));
        }


        public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
            SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
            utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gpsUTCDate = null;
            try {
                gpsUTCDate = utcFormater.parse(utcTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
            localFormater.setTimeZone(TimeZone.getDefault());
            String localTime = localFormater.format(gpsUTCDate.getTime());
            return localTime;
        }

        public static Date utc2LocalDate(String utcTime) {
            SimpleDateFormat utcFormater = new SimpleDateFormat(UTC_TIME_FORMATE);
            utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gpsUTCDate = null;
            try {
                gpsUTCDate = utcFormater.parse(utcTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return gpsUTCDate;
        }

        /**
         * 将日期字符串转换成java.sql.Date
         *
         * @param dateString
         *            日期字符串 如："2004-02-12 12:30:10"
         * @param partten
         *            日期表达式 如："yyyy-MM-dd HH:mm:ss"
         * @return java.sql.Date
         */
        public static java.sql.Date toSQLDate(String dateString, String partten) {
            SimpleDateFormat df = new SimpleDateFormat(partten);
            return DateToSQLDate(df.parse(dateString, new ParsePosition(0)));
        }

        /**
         * 将java.util.Date转换成java.sql.Date
         *
         * @param date
         *            java.util.Date
         * @return java.sql.Date
         */
        public static java.sql.Date DateToSQLDate(Date date) {
            return new java.sql.Date(date.getTime());
        }

        /**
         * 将Date类型日期转换成指定格式字符串
         *
         * @param date
         *            Date对象，如 new Date(107,7,30,11,11,11)
         * @param partten
         *            日期表达式 如："yyyy-MM-dd HH:mm:ss"
         * @return 日期的字符串表示，如"2007-08-30 11:11:11"
         */
        public static String toString(Date date, String partten) {
            if (date == null)
                return "";
            SimpleDateFormat df = new SimpleDateFormat(partten);
            return df.format(date);
        }

        /**
         * 获得Calendar对象
         *
         * @return Calendar对象
         */
        public static Calendar getCalendar() {
            return GregorianCalendar.getInstance();
        }

        /**
         * 获得精确到毫秒级的日期的字符串表示，格式为yyyy-MM-dd HH:mm:ss,SSS
         *
         * @return String
         */
        public static String getDateMilliFormat() {
            Calendar cal = Calendar.getInstance();
            return getDateMilliFormat(cal);
        }

        /**
         * 获得精确到毫秒级的日期的字符串表示，格式为yyyy-MM-dd HH:mm:ss,SSS
         *
         * @param cal
         *            Calendar对象
         * @return String 格式为yyyy-MM-dd HH:mm:ss,SSS
         */
        public static String getDateMilliFormat(java.util.Calendar cal) {
            String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
            return getDateFormat(cal, pattern);
        }

        /**
         * 获得精确到毫秒级的日期的字符串表示，格式为yyyy-MM-dd HH:mm:ss,SSS
         *
         * @param date
         *            Date对象
         * @return String 格式为yyyy-MM-dd HH:mm:ss,SSS
         */
        public static String getDateMilliFormat(java.util.Date date) {
            String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
            return getDateFormat(date, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd HH:mm:ss,SSS的日期字符串解析为Calendar类型
         *
         * @param strDate
         *            日期字符串,如"2007-08-25 12:10:45,300"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarMilliFormat(String strDate) {
            String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
            return parseCalendarFormat(strDate, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd HH:mm:ss,SSS的日期字符串解析为Date类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12 12:30:10 100"
         * @return java.util.Date
         */
        public static Date parseDateMilliFormat(String strDate) {
            String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
            return parseDateFormat(strDate, pattern);
        }

        /**
         * 获得精确到秒的日期的字符串表示，格式为yyyy-MM-dd HH:mm:ss
         *
         * @return String
         */
        public static String getDateSecondFormat() {
            Calendar cal = Calendar.getInstance();
            return getDateSecondFormat(cal);
        }

        /**
         * 获得精确到秒的日期的字符串表示，格式为yyyy-MM-dd HH:mm:ss
         *
         * @param cal
         *            Calendar对象
         * @return String 格式为yyyy-MM-dd HH:mm:ss
         */
        public static String getDateSecondFormat(java.util.Calendar cal) {
            return getDateFormat(cal, Date_YMD_HMS_Pattern);
        }

        /**
         * 获得精确到秒的日期的字符串表示，格式为yyyy-MM-dd HH:mm:ss
         *
         * @param date
         *            Date对象
         * @return String 格式为yyyy-MM-dd HH:mm:ss
         */
        public static String getDateSecondFormat(java.util.Date date) {
            return getDateFormat(date, Date_YMD_HMS_Pattern);
        }

        /**
         * 将格式为yyyy-MM-dd HH:mm:ss的日期字符串解析为Calendar类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12 12:30:10"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarSecondFormat(String strDate) {
            return parseCalendarFormat(strDate, Date_YMD_HMS_Pattern);
        }

        /**
         * 将格式为yyyy-MM-dd HH:mm:ss的日期字符串解析为Date类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12 12:30:10"
         * @return java.util.Date
         */
        public static Date parseDateSecondFormat(String strDate) {
            return parseDateFormat(strDate, Date_YMD_HMS_Pattern);
        }

        /**
         * 获得日期的字符串表示，格式为yyyy-MM-dd HH:mm
         *
         * @return String 格式为yyyy-MM-dd HH:mm
         */
        public static String getDateMinuteFormat() {
            Calendar cal = Calendar.getInstance();
            return getDateMinuteFormat(cal);
        }

        /**
         * 获得日期的字符串表示，格式为yyyy-MM-dd HH:mm
         *
         * @param cal
         *            Calendar对象
         * @return String 格式为yyyy-MM-dd HH:mm
         */
        public static String getDateMinuteFormat(java.util.Calendar cal) {
            String pattern = "yyyy-MM-dd HH:mm";
            return getDateFormat(cal, pattern);
        }

        /**
         * 获得日期的字符串表示，格式为yyyy-MM-dd HH:mm
         *
         * @param date
         *            Date对象
         * @return String 格式为yyyy-MM-dd HH:mm
         */
        public static String getDateMinuteFormat(java.util.Date date) {
            String pattern = "yyyy-MM-dd HH:mm";
            return getDateFormat(date, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd HH:mm的日期字符串解析为Calendar类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12 12:30"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarMinuteFormat(String strDate) {
            String pattern = "yyyy-MM-dd HH:mm";
            return parseCalendarFormat(strDate, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd HH:mm的日期字符串解析为Date类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12 12:30"
         * @return java.util.Date
         */
        public static Date parseDateMinuteFormat(String strDate) {
            String pattern = "yyyy-MM-dd HH:mm";
            return parseDateFormat(strDate, pattern);
        }

        /**
         * 获得日期的字符串表示，格式为yyyy-MM-dd
         *
         * @return String 格式为yyyy-MM-dd
         */
        public static String getDateDayFormat() {
            Calendar cal = Calendar.getInstance();
            return getDateDayFormat(cal);
        }

        /**
         * 获得日期的字符串表示，格式为yyyy-MM-dd
         *
         * @param cal
         *            Calendar对象
         * @return String 格式为yyyy-MM-dd
         */
        public static String getDateDayFormat(java.util.Calendar cal) {
            String pattern = "yyyy-MM-dd";
            return getDateFormat(cal, pattern);
        }

        /**
         * 获得日期的字符串表示，格式为yyyy-MM-dd
         *
         * @param date
         *            Date对象
         * @return String 格式为yyyy-MM-dd
         */
        public static String getDateDayFormat(java.util.Date date) {
            String pattern = "yyyy-MM-dd";
            return getDateFormat(date, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd的日期字符串解析为Calendar类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarDayFormat(String strDate) {
            String pattern = "yyyy-MM-dd";
            return parseCalendarFormat(strDate, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd的日期字符串解析为Date类型
         *
         * @param strDate
         *            日期字符串 如："2004-02-12"
         * @return java.util.Date
         */
        public static Date parseDateDayFormat(String strDate) {
            String pattern = "yyyy-MM-dd";
            return parseDateFormat(strDate, pattern);
        }

        /**
         * 转换为日期字符串，格式为yyyy-MM-dd_HH-mm-ss
         *
         * @return String 格式为yyyy-MM-dd_HH-mm-ss
         */
        public static String getDateFileFormat() {
            Calendar cal = Calendar.getInstance();
            return getDateFileFormat(cal);
        }

        /**
         * 将Calendar类型日期转换成日期字符串，格式为yyyy-MM-dd_HH-mm-ss
         *
         * @param cal
         *            Calendar对象
         * @return String 格式为yyyy-MM-dd_HH-mm-ss
         */
        public static String getDateFileFormat(java.util.Calendar cal) {
            String pattern = "yyyy-MM-dd_HH-mm-ss";
            return getDateFormat(cal, pattern);
        }

        /**
         * 将Date类型日期转换成日期字符串，格式为yyyy-MM-dd_HH-mm-ss
         *
         * @param date
         *            Date对象，如 new Date(109,11,22,11,22,33)
         * @return String 格式为yyyy-MM-dd_HH-mm-ss
         */
        public static String getDateFileFormat(java.util.Date date) {
            String pattern = "yyyy-MM-dd_HH-mm-ss";
            return getDateFormat(date, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd_HH-mm-ss的日期字符串解析为Calendar类型
         *
         * @param strDate
         *            日期字符串，如 "2009-12-22_11-22-33"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarFileFormat(String strDate) {
            String pattern = "yyyy-MM-dd_HH-mm-ss";
            return parseCalendarFormat(strDate, pattern);
        }

        /**
         * 将格式为yyyy-MM-dd_HH-mm-ss的日期字符串解析为Date类型
         *
         * @param strDate
         *            日期字符串，如 "2009-12-22_11-22-33"
         * @return java.util.Date
         */
        public static Date parseDateFileFormat(String strDate) {
            String pattern = "yyyy-MM-dd_HH-mm-ss";
            return parseDateFormat(strDate, pattern);
        }

        /**
         * 获得符合W3C规范的日期格式
         *
         * @return String 格式为yyyy-MM-dd HH:mm:ss
         */
        public static String getDateW3CFormat() {
            Calendar cal = Calendar.getInstance();
            return getDateW3CFormat(cal);
        }

        /**
         * 获得符合W3C规范的日期格式，格式为yyyy-MM-dd HH:mm:ss
         *
         * @param cal
         *            Calendar对象
         * @return String 格式为yyyy-MM-dd HH:mm:ss
         */
        public static String getDateW3CFormat(java.util.Calendar cal) {
            return getDateFormat(cal, Date_YMD_HMS_Pattern);
        }

        /**
         * 获得符合W3C规范的日期格式，格式为yyyy-MM-dd HH:mm:ss
         *
         * @param date
         *            Date对象
         * @return String 格式为yyyy-MM-dd HH:mm:ss
         */
        public static String getDateW3CFormat(java.util.Date date) {
            return getDateFormat(date, Date_YMD_HMS_Pattern);
        }

        /**
         * 将yyyy-MM-dd HH:mm:ss格式的日期字符串解析为Calendar类型
         *
         * @param strDate
         *            日期字符串，如 "2004-03-02 01:12:12"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarW3CFormat(String strDate) {
            return parseCalendarFormat(strDate, Date_YMD_HMS_Pattern);
        }

        /**
         * 将yyyy-MM-dd HH:mm:ss格式的日期字符串解析为Date类型
         *
         * @param strDate
         *            日期字符串，如 "2004-03-02 01:12:12"
         * @return java.util.Date
         */
        public static Date parseDateW3CFormat(String strDate) {
            return parseDateFormat(strDate, Date_YMD_HMS_Pattern);
        }

        /**
         * 获得yyyy-MM-dd HH:mm:ss格式的日期字符串
         *
         * @param cal
         *            Calendar对象
         * @return String yyyy-MM-dd HH:mm:ss格式的日期字符串
         */
        public static String getDateFormat(java.util.Calendar cal) {
            return getDateFormat(cal, Date_YMD_HMS_Pattern);
        }

        /**
         * 获得yyyy-MM-dd HH:mm:ss格式的日期字符串
         *
         * @param date
         *            Date对象
         * @return String yyyy-MM-dd HH:mm:ss格式的日期字符串
         */
        public static String getDateFormat(java.util.Date date) {
            return getDateFormat(date, Date_YMD_HMS_Pattern);
        }

        /**
         * 将yyyy-MM-dd HH:mm:ss格式的日期字符串解析为Calendar对象
         *
         * @param strDate
         *            日期字符串，如 "2004-03-02 01:12:12"
         * @return java.util.Calendar
         */
        public static Calendar parseCalendarFormat(String strDate) {
            return parseCalendarFormat(strDate, Date_YMD_HMS_Pattern);
        }

        /**
         * 将yyyy-MM-dd HH:mm:ss格式的日期字符串解析为Date对象
         *
         * @param strDate
         *            日期字符串，如 "2004-03-02 01:12:12"
         * @return java.util.Date
         */
        public static Date parseDateFormat(String strDate) {
            return parseDateFormat(strDate, Date_YMD_HMS_Pattern);
        }

        /**
         * 获得日期的字符串表示
         *
         * @param cal
         *            Calendar对象
         * @param pattern
         *            日期表达式
         * @return String
         */
        public static String getDateFormat(java.util.Calendar cal, String pattern) {
            return getDateFormat(cal.getTime(), pattern);
        }

        /**
         * 获得日期的字符串表示
         *
         * @param date
         *            Date对象
         * @param pattern
         *            日期表达式
         * @return String
         */
        public static synchronized String getDateFormat(java.util.Date date, String pattern) {
            synchronized (sdf) {
                String str = null;
                sdf.applyPattern(pattern);
                str = sdf.format(date);
                return str;
            }
        }

        /**
         * 将日期字符串解析成Calendar类型
         *
         * @param strDate
         *            日期字符串
         * @param pattern
         *            日期表达式
         * @return java.util.Calendar
         */
        public static synchronized Calendar parseCalendarFormat(String strDate, String pattern) {
            synchronized (sdf) {
                Calendar cal = null;
                sdf.applyPattern(pattern);
                try {
                    sdf.parse(strDate);
                    cal = sdf.getCalendar();
                } catch (ParseException e) {
                }
                return cal;
            }
        }

        /**
         * 将日期字符串解析成Date类型
         *
         * @param strDate
         *            日期字符串
         * @param pattern
         *            日期表达式
         * @return java.util.Date
         */
        public static synchronized Date parseDateFormat(String strDate, String pattern) {
            synchronized (sdf) {
                Date date = null;
                sdf.applyPattern(pattern);
                try {
                    date = sdf.parse(strDate);
                } catch (ParseException e) {
                }

                return date;
            }
        }

        /**
         * 获得本年该月的最后一天
         *
         * @param month
         * @return 本年该月的最后一天
         */
        public static int getLastDayOfMonth(int month) {
            if (month < 1 || month > 12) {
                return -1;
            }
            int retn = 0;
            if (month == 2) {
                if (isLeapYear()) {
                    retn = 29;
                } else {
                    retn = dayArray[month - 1];
                }
            } else {
                retn = dayArray[month - 1];
            }
            return retn;
        }

        /**
         * 获得指定年指定月的最后一天
         *
         * @param year
         * @param month
         * @return 指定年或月的最后一天
         */
        public static int getLastDayOfMonth(int year, int month) {
            if (month < 1 || month > 12) {
                return -1;
            }
            int retn = 0;
            if (month == 2) {
                if (isLeapYear(year)) {
                    retn = 29;
                } else {
                    retn = dayArray[month - 1];
                }
            } else {
                retn = dayArray[month - 1];
            }
            return retn;
        }

        /**
         * 本年是平年还是闰年
         *
         * @return true：闰年，false：平年
         */
        public static boolean isLeapYear() {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            return isLeapYear(year);
        }

        /**
         * 指定年为平年还是闰年
         *
         * @param year
         * @return true：闰年，false：平年
         */
        public static boolean isLeapYear(int year) {
            /**
             * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则是平年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则是平年
             */
            if ((year % 400) == 0)
                return true;
            else if ((year % 4) == 0) {
                if ((year % 100) == 0)
                    return false;
                else
                    return true;
            } else
                return false;
        }

        /**
         * 判断指定日期的年份是否是闰年
         *
         * @param date
         *            指定日期。
         * @return 是否闰年
         */
        public static boolean isLeapYear(java.util.Date date) {
            /**
             * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则不是闰年
             */
            // int year = date.getYear();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            int year = gc.get(Calendar.YEAR);
            return isLeapYear(year);
        }

        /**
         * 判断指定日期的年份是否是闰年
         *
         * @param gc
         *            指定日期
         * @return 是否闰年
         */
        public static boolean isLeapYear(java.util.Calendar gc) {
            /**
             * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则不是闰年
             */
            int year = gc.get(Calendar.YEAR);
            return isLeapYear(year);
        }

        /**
         * 得到指定日期的前一个工作日
         *
         * @param date
         *            指定日期。
         * @return 指定日期的前一个工作日
         */
        public static java.util.Date getPreviousWeekDay(java.util.Date date) {
            {
                /**
                 * 详细设计： 1.如果date是星期一，则减3天 2.如果date是星期日，则减2天 3.否则减1天
                 */
                GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
                gc.setTime(date);
                return getPreviousWeekDay(gc);
            }
        }

        /**
         * 得到指定日期的前一个工作日
         *
         * @param gc
         *            指定日期
         * @return 指定日期的前一个工作日
         */
        public static java.util.Date getPreviousWeekDay(java.util.Calendar gc) {
            {
                /**
                 * 详细设计： 1.如果date是星期一，则减3天 2.如果date是星期日，则减2天 3.否则减1天
                 */
                switch (gc.get(Calendar.DAY_OF_WEEK)) {
                    case (Calendar.MONDAY):
                        gc.add(Calendar.DATE, -3);
                        break;
                    case (Calendar.SUNDAY):
                        gc.add(Calendar.DATE, -2);
                        break;
                    default:
                        gc.add(Calendar.DATE, -1);
                        break;
                }
                return gc.getTime();
            }
        }

        /**
         * 得到指定日期的后一个工作日
         *
         * @param date
         *            指定日期。
         * @return 指定日期的后一个工作日
         */
        public static java.util.Date getNextWeekDay(java.util.Date date) {
            /**
             * 详细设计： 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case (Calendar.FRIDAY):
                    gc.add(Calendar.DATE, 3);
                    break;
                case (Calendar.SATURDAY):
                    gc.add(Calendar.DATE, 2);
                    break;
                default:
                    gc.add(Calendar.DATE, 1);
                    break;
            }
            return gc.getTime();
        }

        /**
         * 得到指定日期的后一个工作日
         *
         * @param gc
         *            指定日期
         * @return 指定日期的后一个工作日
         */
        public static java.util.Calendar getNextWeekDay(java.util.Calendar gc) {
            /**
             * 详细设计： 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
             */
            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case (Calendar.FRIDAY):
                    gc.add(Calendar.DATE, 3);
                    break;
                case (Calendar.SATURDAY):
                    gc.add(Calendar.DATE, 2);
                    break;
                default:
                    gc.add(Calendar.DATE, 1);
                    break;
            }
            return gc;
        }

        /**
         * 取得指定日期的下一个月的最后一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一个月的最后一天
         */
        public static java.util.Date getLastDayOfNextMonth(java.util.Date date) {
            /**
             * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getLastDayOfMonth
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.setTime(getNextMonth(gc.getTime()));
            gc.setTime(getLastDayOfMonth(gc.getTime()));
            return gc.getTime();
        }

        /**
         * 取得指定日期的下一个星期的最后一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一个星期的最后一天
         */
        public static java.util.Date getLastDayOfNextWeek(java.util.Date date) {
            /**
             * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getLastDayOfWeek
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.setTime(getNextWeek(gc.getTime()));
            gc.setTime(getLastDayOfWeek(gc.getTime()));
            return gc.getTime();
        }

        /**
         * 取得指定日期的下一个月的第一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一个月的第一天
         */
        public static java.util.Date getFirstDayOfNextMonth(java.util.Date date) {
            /**
             * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.setTime(getNextMonth(gc.getTime()));
            gc.setTime(getFirstDayOfMonth(gc.getTime()));
            return gc.getTime();
        }

        public static java.util.Calendar getFirstDayOfNextMonth(java.util.Calendar gc) {
            /**
             * 详细设计： 1.调用getNextMonth设置当前时间 2.以1为基础，调用getFirstDayOfMonth
             */
            gc.setTime(getNextMonth(gc.getTime()));
            gc.setTime(getFirstDayOfMonth(gc.getTime()));
            return gc;
        }

        /**
         * 取得指定日期的下一个星期的第一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一个星期的第一天
         */
        public static java.util.Date getFirstDayOfNextWeek(java.util.Date date) {
            /**
             * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.setTime(getNextWeek(gc.getTime()));
            gc.setTime(getFirstDayOfWeek(gc.getTime()));
            return gc.getTime();
        }

        public static java.util.Calendar getFirstDayOfNextWeek(java.util.Calendar gc) {
            /**
             * 详细设计： 1.调用getNextWeek设置当前时间 2.以1为基础，调用getFirstDayOfWeek
             */
            gc.setTime(getNextWeek(gc.getTime()));
            gc.setTime(getFirstDayOfWeek(gc.getTime()));
            return gc;
        }

        /**
         * 取得指定日期的下一个月
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一个月
         */
        public static java.util.Date getNextMonth(java.util.Date date) {
            /**
             * 详细设计： 1.指定日期的月份加1
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.add(Calendar.MONTH, 1);
            return gc.getTime();
        }

        /**
         * 取得指定日期的下一个月
         *
         * @param gc
         *            指定日期。
         * @return 指定日期的下一个月
         */
        public static java.util.Calendar getNextMonth(java.util.Calendar gc) {
            /**
             * 详细设计： 1.指定日期的月份加1
             */
            gc.add(Calendar.MONTH, 1);
            return gc;
        }

        /**
         * 取得指定日期的下一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一天
         */
        public static java.util.Date getNextDay(java.util.Date date) {
            /**
             * 详细设计： 1.指定日期加1天
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.add(Calendar.DATE, 1);
            return gc.getTime();
        }

        /**
         * 取得指定日期的下一天
         *
         * @return 指定日期的下一天
         */
        public static java.util.Calendar getNextDay(java.util.Calendar gc) {
            /**
             * 详细设计： 1.指定日期加1天
             */
            gc.add(Calendar.DATE, 1);
            return gc;
        }

        /**
         * 取得指定日期的下一个星期
         *
         * @param date
         *            指定日期。
         * @return 指定日期的下一个星期
         */
        public static java.util.Date getNextWeek(java.util.Date date) {
            /**
             * 详细设计： 1.指定日期加7天
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.add(Calendar.DATE, 7);
            return gc.getTime();
        }

        /**
         * 取得指定日期的下一个星期
         *
         * @param gc
         *            指定日期。
         * @return 指定日期的下一个星期
         */
        public static java.util.Calendar getNextWeek(java.util.Calendar gc) {
            /**
             * 详细设计： 1.指定日期加7天
             */
            gc.add(Calendar.DATE, 7);
            return gc;
        }

        /**
         * 取得指定日期的所处星期的最后一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的所处星期的最后一天
         */
        public static java.util.Date getLastDayOfWeek(java.util.Date date) {
            /**
             * 详细设计： 1.如果date是星期日，则加6天 2.如果date是星期一，则加5天 3.如果date是星期二，则加4天 4.如果date是星期三，则加3天 5.如果date是星期四，则加2天
             * 6.如果date是星期五，则加1天 7.如果date是星期六，则加0天
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case (Calendar.SUNDAY):
                    gc.add(Calendar.DATE, 6);
                    break;
                case (Calendar.MONDAY):
                    gc.add(Calendar.DATE, 5);
                    break;
                case (Calendar.TUESDAY):
                    gc.add(Calendar.DATE, 4);
                    break;
                case (Calendar.WEDNESDAY):
                    gc.add(Calendar.DATE, 3);
                    break;
                case (Calendar.THURSDAY):
                    gc.add(Calendar.DATE, 2);
                    break;
                case (Calendar.FRIDAY):
                    gc.add(Calendar.DATE, 1);
                    break;
                case (Calendar.SATURDAY):
                    gc.add(Calendar.DATE, 0);
                    break;
            }
            return gc.getTime();
        }

        /**
         * 取得指定日期的所处星期的第一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的所处星期的第一天
         */
        public static java.util.Date getFirstDayOfWeek(java.util.Date date) {
            /**
             * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天
             * 6.如果date是星期五，则减5天 7.如果date是星期六，则减6天
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case (Calendar.SUNDAY):
                    gc.add(Calendar.DATE, 0);
                    break;
                case (Calendar.MONDAY):
                    gc.add(Calendar.DATE, -1);
                    break;
                case (Calendar.TUESDAY):
                    gc.add(Calendar.DATE, -2);
                    break;
                case (Calendar.WEDNESDAY):
                    gc.add(Calendar.DATE, -3);
                    break;
                case (Calendar.THURSDAY):
                    gc.add(Calendar.DATE, -4);
                    break;
                case (Calendar.FRIDAY):
                    gc.add(Calendar.DATE, -5);
                    break;
                case (Calendar.SATURDAY):
                    gc.add(Calendar.DATE, -6);
                    break;
            }
            return gc.getTime();
        }

        /**
         * 取得指定日期的所处星期的第一天
         *
         * @param gc
         *            指定日期。
         * @return 指定日期的所处星期的第一天
         */
        public static java.util.Calendar getFirstDayOfWeek(java.util.Calendar gc) {
            /**
             * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天
             * 6.如果date是星期五，则减5天 7.如果date是星期六，则减6天
             */
            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case (Calendar.SUNDAY):
                    gc.add(Calendar.DATE, 0);
                    break;
                case (Calendar.MONDAY):
                    gc.add(Calendar.DATE, -1);
                    break;
                case (Calendar.TUESDAY):
                    gc.add(Calendar.DATE, -2);
                    break;
                case (Calendar.WEDNESDAY):
                    gc.add(Calendar.DATE, -3);
                    break;
                case (Calendar.THURSDAY):
                    gc.add(Calendar.DATE, -4);
                    break;
                case (Calendar.FRIDAY):
                    gc.add(Calendar.DATE, -5);
                    break;
                case (Calendar.SATURDAY):
                    gc.add(Calendar.DATE, -6);
                    break;
            }
            return gc;
        }

        /**
         * 取得指定日期的所处月份的最后一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的所处月份的最后一天
         */
        public static java.util.Date getLastDayOfMonth(java.util.Date date) {
            /**
             * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日 4.如果date在4月，则为30日 5.如果date在5月，则为31日
             * 6.如果date在6月，则为30日 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日 10.如果date在10月，则为31日
             * 11.如果date在11月，则为30日 12.如果date在12月，则为31日 1.如果date在闰年的2月，则为29日
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            switch (gc.get(Calendar.MONTH)) {
                case 0:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 1:
                    gc.set(Calendar.DAY_OF_MONTH, 28);
                    break;
                case 2:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 3:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 4:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 5:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 6:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 7:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 8:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 9:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 10:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 11:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
            }
            // 检查闰年
            if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR)))) {
                gc.set(Calendar.DAY_OF_MONTH, 29);
            }
            return gc.getTime();
        }

        /**
         * 取得指定日期的所处月份的最后一天
         *
         * @param gc
         *            指定日期。
         * @return 指定日期的所处月份的最后一天
         */
        public static java.util.Calendar getLastDayOfMonth(java.util.Calendar gc) {
            /**
             * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日 4.如果date在4月，则为30日 5.如果date在5月，则为31日
             * 6.如果date在6月，则为30日 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日 10.如果date在10月，则为31日
             * 11.如果date在11月，则为30日 12.如果date在12月，则为31日 1.如果date在闰年的2月，则为29日
             */
            switch (gc.get(Calendar.MONTH)) {
                case 0:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 1:
                    gc.set(Calendar.DAY_OF_MONTH, 28);
                    break;
                case 2:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 3:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 4:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 5:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 6:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 7:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 8:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 9:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
                case 10:
                    gc.set(Calendar.DAY_OF_MONTH, 30);
                    break;
                case 11:
                    gc.set(Calendar.DAY_OF_MONTH, 31);
                    break;
            }
            // 检查闰年
            if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY) && (isLeapYear(gc.get(Calendar.YEAR)))) {
                gc.set(Calendar.DAY_OF_MONTH, 29);
            }
            return gc;
        }

        /**
         * 取得指定日期的所处月份的第一天
         *
         * @param date
         *            指定日期。
         * @return 指定日期的所处月份的第一天
         */
        public static java.util.Date getFirstDayOfMonth(java.util.Date date) {
            /**
             * 详细设计： 1.设置为1号
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.set(Calendar.DAY_OF_MONTH, 1);
            return gc.getTime();
        }

        /**
         * 取得指定日期的所处月份的第一天
         *
         * @param gc
         *            指定日期。
         * @return 指定日期的所处月份的第一天
         */
        public static java.util.Calendar getFirstDayOfMonth(java.util.Calendar gc) {
            /**
             * 详细设计： 1.设置为1号
             */
            gc.set(Calendar.DAY_OF_MONTH, 1);
            return gc;
        }

        /**
         * 将日期对象转换成为指定ORA日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
         *
         * @param theDate
         *            将要转换为字符串的日期对象。
         * @param hasTime
         *            如果返回的字符串带时间则为true
         * @return 转换的结果
         */
        public static String toOraString(Date theDate, boolean hasTime) {
            /**
             * 详细设计： 1.如果有时间，则设置格式为getOraDateTimeFormat()的返回值 2.否则设置格式为getOraDateFormat()的返回值 3.调用toString(Date theDate,
             * DateFormat theDateFormat)
             */
            DateFormat theFormat;
            if (hasTime) {
                theFormat = getOraDateTimeFormat();
            } else {
                theFormat = getOraDateFormat();
            }
            return toString(theDate, theFormat);
        }

        /**
         * 将日期对象转换成为指定日期、时间格式的字符串形式。如果日期对象为空，返回一个空字符串对象，而不是一个空对象。
         *
         * @param theDate
         *            将要转换为字符串的日期对象。
         * @param hasTime
         *            如果返回的字符串带时间则为true
         * @return 转换的结果
         */
        public static String toString(Date theDate, boolean hasTime) {
            /**
             * 详细设计： 1.如果有时间，则设置格式为getDateTimeFormat的返回值 2.否则设置格式为getDateFormat的返回值 3.调用toString(Date theDate, DateFormat
             * theDateFormat)
             */
            DateFormat theFormat;
            if (hasTime) {
                theFormat = getDateTimeFormat();
            } else {
                theFormat = getDateFormat();
            }
            return toString(theDate, theFormat);
        }

        /**
         * 创建一个标准日期格式的克隆
         *
         * @return 标准日期格式的克隆
         */
        public static DateFormat getDateFormat() {
            /**
             * 详细设计： 1.返回DATE_FORMAT
             */
            SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
            theDateFormat.setLenient(false);
            return theDateFormat;
        }

        /**
         * 创建一个标准时间格式的克隆
         *
         * @return 标准时间格式的克隆
         */
        public static DateFormat getDateTimeFormat() {
            /**
             * 详细设计： 1.返回DATE_TIME_FORMAT
             */
            SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT.clone();
            theDateTimeFormat.setLenient(false);
            return theDateTimeFormat;
        }

        /**
         * 创建一个标准ORA日期格式的克隆
         *
         * @return 标准ORA日期格式的克隆
         */
        public static DateFormat getOraDateFormat() {
            /**
             * 详细设计： 1.返回ORA_DATE_FORMAT
             */
            SimpleDateFormat theDateFormat = (SimpleDateFormat) ORA_DATE_FORMAT.clone();
            theDateFormat.setLenient(false);
            return theDateFormat;
        }

        /**
         * 创建一个标准ORA时间格式的克隆
         *
         * @return 标准ORA时间格式的克隆
         */
        public static DateFormat getOraDateTimeFormat() {
            /**
             * 详细设计： 1.返回ORA_DATE_TIME_FORMAT
             */
            SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT.clone();
            theDateTimeFormat.setLenient(false);
            return theDateTimeFormat;
        }

        public static DateFormat getYearMonthFormat() {
            SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) YEAR_MONTH_FORMAT.clone();
            theDateTimeFormat.setLenient(false);
            return theDateTimeFormat;
        }

        /**
         * 将一个日期对象转换成为指定日期、时间格式的字符串。 如果日期对象为空，返回一个空字符串，而不是一个空对象。
         *
         * @param theDate
         *            要转换的日期对象
         * @param theDateFormat
         *            返回的日期字符串的格式
         * @return 转换结果
         */
        public static String toString(Date theDate, DateFormat theDateFormat) {
            /**
             * 详细设计： 1.theDate为空，则返回"" 2.否则使用theDateFormat格式化
             */
            if (theDate == null)
                return "";
            return theDateFormat.format(theDate);
        }

        /**
         * 取Java虚拟机系统时间, 返回当前日期和时间
         *
         * @return 返回 date 格式的日期和时间, YYYY-MM-DD HH24:MI:SS， 长19位
         */
        public static Date getNowDateTime() {
            return new Timestamp(System.currentTimeMillis());
        }

        /**
         * 生成时间序列号
         *
         * @return
         */
        public static String getOrderNum() {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            return df.format(date);
        }

        /**
         * 得到昨天日期
         */
        public static Date getYesterDay() {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);// 得到昨天的日期
            java.util.Date dat = cal.getTime();
            return dat;
        }

        /**
         * 得到昨天日期
         */
        public static String getYesterDayString() {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);// 得到昨天的日期
            java.util.Date dat = cal.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(dat);
        }

        /**
         * 取得指定日期的上一个月
         *
         * @param date
         *            指定日期。
         * @return 指定日期的上一个月
         */
        public static java.util.Date getPreMonth(java.util.Date date) {
            /**
             * 详细设计： 1.指定日期的月份减1
             */
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            gc.add(Calendar.MONTH, -1);
            return gc.getTime();
        }

        // 产生随机的8位数
        public static String get8() {
            Random rad = new Random();
            return rad.nextInt(100000000) + "";
        }

        /**
         * 日期转化为字符串
         *
         * @param date
         * @param format
         * @return
         */
        public static String dateToStr(Date date, String format) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }

        /**
         * 判断日期是否在两个日期中间
         *
         * @param date
         * @return
         */
        public static boolean  checkModdleDate(Date date,Date startdate,Date enddate) {
            if(date.getTime()>=startdate.getTime()&&date.getTime()<=enddate.getTime()){
                return false;
            }else{
                return true;
            }
        }
        /**
         * 获取当天时间开始时间
         * @return
         */
        public static Long getStartTime(){
            Calendar todayStart = Calendar.getInstance();
            todayStart.set(Calendar.HOUR, 0);
            todayStart.set(Calendar.MINUTE, 0);
            todayStart.set(Calendar.SECOND, 0);
            todayStart.set(Calendar.MILLISECOND, 0);
            return todayStart.getTime().getTime();
        }
        /**
         * 获取当天时间结束时间
         * @return
         */
        public static Long getEndTime(){
            Calendar todayEnd = Calendar.getInstance();
            todayEnd.set(Calendar.HOUR, 23);
            todayEnd.set(Calendar.MINUTE, 59);
            todayEnd.set(Calendar.SECOND, 59);
            todayEnd.set(Calendar.MILLISECOND, 999);
            return todayEnd.getTime().getTime();
        }
        /**
         * 获取oss路径字符串
         */
        public static String getOSSstr() {
            return dateToStr(new Date(),"yyyy/MM/dd");
        }

        /**
         * 给一个日期增加天数
         * @return
         */
        public static Date addDate(Date startDate,Integer days ){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, days);
            return cal.getTime();
        }
        /**
         * 给一个日期增加月数
         * @return
         */
        public static Date addMonth(Date startDate,Integer months ){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.MONTH, months);
            return cal.getTime();
        }
        /**
         * 给一个日期增加年数
         * @return
         */
        public static Date addYear(Date startDate,Integer years ){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.YEAR, years);
            return cal.getTime();
        }
    }


