package com.github.cherry.framework.common.utils;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class DateUtils {

    public static final String GENERAL_FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String GENERAL_SMALL_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 得到当前时间
     * 
     * @return
     */
    public static Date getNowDate() {
        return (Date) new Date().clone();
    }

    /**
     * 
     * 根据秒滚动当前日期 <功能详细描述>
     * 
     * @param date
     * @param seconds
     * @return
     */
    public static Date rollDateBySeconds(Date date, int seconds) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.add(Calendar.SECOND, seconds);
        return d.getTime();
    }

    /**
     * 
     * 根据分钟滚动当前日期 <功能详细描述>
     * 
     * @param date
     * @param minutes
     * @return
     */
    public static Date rollDateByMins(Date date, int minutes) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.add(Calendar.MINUTE, minutes);
        return d.getTime();
    }

    /**
     * 
     * 根据天滚动当前日期 <功能详细描述>
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date rollDateByDays(Date date, int days) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.add(Calendar.DAY_OF_YEAR, days);
        return d.getTime();
    }

    /**
     * 
     * 根据月份滚动当前日期 <功能详细描述>
     * 
     * @param date
     * @param month
     * @return
     */
    public static Date rollDateByMonth(Date date, int month) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.add(Calendar.MONTH, month);
        return d.getTime();
    }

    /**
     * 
     * 得到当天开始时间 <功能详细描述>
     * 
     * @param date
     * @return
     */
    public static Date getBeginTime(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.set(Calendar.HOUR_OF_DAY, 0);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        d.set(Calendar.MILLISECOND, 0);
        return d.getTime();
    }

    /**
     * 得到当天结束时间 <一句话功能简述> <功能详细描述>
     * 
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.set(Calendar.HOUR_OF_DAY, 23);
        d.set(Calendar.MINUTE, 59);
        d.set(Calendar.SECOND, 59);
        d.set(Calendar.MILLISECOND, 999);
        return d.getTime();
    }

    /**
     * 
     * 得到月的第一天 <功能详细描述>
     * 
     * @param date
     * @return
     */
    public static Date firstOfMonth(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.set(Calendar.HOUR_OF_DAY, 0);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        d.set(Calendar.MILLISECOND, 0);
        d.set(Calendar.DAY_OF_MONTH, 1);
        return d.getTime();
    }

    /**
     * 得到月最后一天 <一句话功能简述> <功能详细描述>
     * 
     * @param date
     * @return
     */
    public static Date endOfMonth(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.set(Calendar.DAY_OF_MONTH, 1);
        d.add(Calendar.MONTH, 1);
        d.add(Calendar.DAY_OF_MONTH, -1);
        d.set(Calendar.HOUR_OF_DAY, 23);
        d.set(Calendar.MINUTE, 59);
        d.set(Calendar.SECOND, 59);
        d.set(Calendar.MILLISECOND, 999);
        return d.getTime();
    }

    /**
     * 得到当前天 <一句话功能简述> <功能详细描述>
     * 
     * @param date
     * @return
     */
    public static int getDays(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        return d.getActualMaximum(Calendar.DATE);
    }

    /**
     * 
     * 得到当前的月份
     * 
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    /**
     * 格式化日期为字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = DateFormatHolder.formatFor(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 
     * 格式转换
     * 
     * @param in
     * @param inApplyPattern
     * @param outApplyPattern
     * @return
     */
    public static String formatDate(String in, String inApplyPattern, String outApplyPattern) {
        Date date = string2Date(in, inApplyPattern);
        return formatDate(date, outApplyPattern);
    }

    /**
     * 格式化日期
     * 
     * @param value
     * @param format
     * @return
     */
    public static Date string2Date(String date, String format) {
        SimpleDateFormat sdf = DateFormatHolder.formatFor(format);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("date format error with " + date + " using " + format);
        }
        return d;
    }

    /**
     * 获取两个时间相聚的天数 <一句话功能简述> <功能详细描述>
     * 
     * @param bfDate
     * @param afDate
     * @return
     */
    public static long getTimeIntervalDays(Date bfDate, Date afDate) {
        String stBfDate = formatDate(bfDate, GENERAL_SMALL_DATE_FORMAT);
        String stAfDate = formatDate(afDate, GENERAL_SMALL_DATE_FORMAT);
        SimpleDateFormat format = DateFormatHolder.formatFor(GENERAL_SMALL_DATE_FORMAT);
        Date beginDate;
        Date endDate;
        long day = 0;
        try {
            beginDate = format.parse(stBfDate);
            endDate = format.parse(stAfDate);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            // Ignore, never happened
        }
        return day;
    }

    /**
     * A factory for {@link SimpleDateFormat}s. The instances are stored in a
     * threadlocal way because SimpleDateFormat is not threadsafe as noted in {@link SimpleDateFormat its javadoc}.
     *
     */
    final static class DateFormatHolder {

        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>>();

        /**
         * creates a {@link SimpleDateFormat} for the requested format string.
         *
         * @param pattern
         * a non-{@code null} format String according to {@link SimpleDateFormat}. The format is not checked against
         * {@code null} since all paths go through {@link DateUtils}.
         * @return the requested format. This simple dateformat should not be used
         * to {@link SimpleDateFormat#applyPattern(String) apply} to a
         * different pattern.
         */
        public static SimpleDateFormat formatFor(final String pattern) {
            final SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
            Map<String, SimpleDateFormat> formats = ref == null ? null : ref.get();
            if (formats == null) {
                formats = new HashMap<String, SimpleDateFormat>();
                THREADLOCAL_FORMATS.set(new SoftReference<Map<String, SimpleDateFormat>>(formats));
            }

            SimpleDateFormat format = formats.get(pattern);
            if (format == null) {
                format = new SimpleDateFormat(pattern, Locale.US);
                format.setTimeZone(TimeZone.getTimeZone("GMT"));
                formats.put(pattern, format);
            }

            return format;
        }

        public static void clearThreadLocal() {
            THREADLOCAL_FORMATS.remove();
        }

    }

}
