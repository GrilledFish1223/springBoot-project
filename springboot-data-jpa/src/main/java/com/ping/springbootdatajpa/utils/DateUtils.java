package com.ping.springbootdatajpa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 带时区的时间日期格式
     **/
    public static final String DEFAULT_UTC_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * 每天小时数
     */
    public static final long HOURS_PER_DAY = 24;
    /**
     * 每小时分钟数
     */
    public static final long MINUTES_PER_HOUR = 60;
    /**
     * 每分钟秒数
     */
    public static final long SECONDS_PER_MINUTE = 60;
    /**
     * 每秒的毫秒数
     */
    public static final long MILLIONSECONDS_PER_SECOND = 1000;
    /**
     * 每分钟毫秒数
     */
    public static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * 每天毫秒数
     */
    public static final long MILLIONSECONDS_SECOND_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLIONSECONDS_PER_SECOND;

    /**
     * UTC timezone
     **/
    public static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    private DateUtils() {
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
     *
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDateTime(String date) {
        return getDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   待转换字符串
     * @param format 日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   日期对象
     * @param format 日期格式
     * @param defVal 转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = defVal;
        }
        return d;
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String forDatetime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    public static String forDatetimeToday() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT, null);
    }

    public static String formatDateToday() {
        return formatDate(new Date(), DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date   待格式化日期对象
     * @param format 格式化格式
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }

    /**
     * 带时区的格式化时间
     *
     * @param date
     * @param format
     * @param timeZone
     * @return
     */
    public static String formatDateTimeZone(Date date, String format, TimeZone timeZone) {
        String ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(timeZone);
            ret = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date   待格式化日期对象
     * @param format 格式化格式
     * @param defVal 格式化失败时的默认返回空
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String format, String defVal) {
        String ret;
        try {
            ret = new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            ret = defVal;
        }
        return ret;
    }

    /**
     * 返回指定日期加上days天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, int days) {
        return changeDays(date, days);
    }

    public static Date plusDaysToday(int days) {
        return plusDays(getToday(), days);
    }

    public static Date minusDaysToday(int days) {
        return minusDays(getToday(), days);
    }

    /**
     * 返回指定日期减去days天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, int days) {
        return changeDays(date, -days);
    }

    public static Date changeDate(Date date, int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }

    public static Date changeDays(Date date, int days) {
        return changeDate(date, Calendar.DAY_OF_YEAR, days);
    }

    /**
     * 获取当前日期加时间
     *
     * @return
     */
    public static Date getToday() {
        return new Date();
    }

    /**
     * 获取今天日期, 格式: YYYY-MM-DD
     *
     * @return
     */
    public static String getTodayStr() {
        return formatDate(getToday(), DEFAULT_DATE_FORMAT);
    }

    /**
     * 比较传入日期与当前日期相差的天数
     *
     * @param d
     * @return
     */
    public static int intervalDay(Date d) {
        return intervalDay(getToday(), d);
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int intervalDay(Date d1, Date d2) {
        long intervalMillSecond = truncDate(d1).getTime() - truncDate(d2).getTime();
        //相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
        return (int) (intervalMillSecond / MILLIONSECONDS_SECOND_PER_DAY);
    }

    /**
     * 获得两个日期之间相差的分钟数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的分钟数值
     */
    public static int intervalMinutes(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        //相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE
                + (intervalMillSecond % MILLIONSECONDS_PER_MINUTE > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的秒数差（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int intervalSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND
                + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    public static int getAge(Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        //取得生日年份
        int year = birth.get(Calendar.YEAR);
        //年龄
        int age = now.get(Calendar.YEAR) - year;
        //修正
        now.set(Calendar.YEAR, year);
        age = (now.before(birth)) ? age - 1 : age;
        return age;
    }

    /**
     * d1 和 d2 是同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断是否d2是d1的后一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isContinueDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        return intervalDay(d1, d2) == 1;
    }

    /**
     * 得到没有时间的日期
     *
     * @param date
     * @return
     */
    public static Date truncDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 把时间截断到分钟
     *
     * @param
     * @return
     */
    public static Date truncMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 把时间阶段到小时
     *
     * @param date
     * @return
     */
    public static Date truncHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
