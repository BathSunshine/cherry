package com.github.cherry.framework.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author Scott
 * @since 1.0
 */
public abstract class NumberUtils {

    private static final int DEF_DIV_SCALE = 2;

    private static final String DECIMAL = "^[\\-\\+]?[0-9]+([.]{1}[0-9]+){0,1}$";

    /**
     * 
     * 是否相等
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) == 0;
    }

    /**
     * 
     * 比较 true a > b false a <= b
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean compare(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) > 0;
    }

    /**
     * 1.115 == >> 1.16
     * 
     * <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @return
     */
    public static BigDecimal round(BigDecimal a) {
        return a.setScale(DEF_DIV_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * 1.115 == >> 1.16
     * 
     * <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @return
     */
    public static BigDecimal round(BigDecimal a, int scale) {
        return a.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 
     * a + b
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal add(double a, double b) {
        return add(new BigDecimal(a + ""), new BigDecimal(b + ""));
    }

    /**
     * 
     * a + b <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /**
     * 
     * a - b
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal sub(double a, double b) {
        return sub(new BigDecimal(a + ""), new BigDecimal(b + ""));
    }

    /**
     * 
     * a - b
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /**
     * a * b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal mul(double a, double b) {
        return mul(a, b, DEF_DIV_SCALE);
    }

    /**
     * a * b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal mul(double a, double b, int scale) {
        return mul(new BigDecimal(a + ""), new BigDecimal(b + ""), scale);
    }

    /**
     * a * b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal mul(BigDecimal a, BigDecimal b) {
        return mul(a, b, DEF_DIV_SCALE);
    }

    /**
     * a * b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal mul(BigDecimal a, BigDecimal b, int scale) {
        return a.multiply(b).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * a / b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal div(double a, double b) {
        return div(a, b, DEF_DIV_SCALE);
    }

    /**
     * a / b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @param scale
     * @return
     */
    public static BigDecimal div(double a, double b, int scale) {
        return div(new BigDecimal(a + ""), new BigDecimal(b + ""), scale);
    }

    /**
     * a / b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal div(BigDecimal a, BigDecimal b) {
        return a.divide(b, DEF_DIV_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * a / b <一句话功能简述> <功能详细描述>
     * 
     * @param a
     * @param b
     * @param scale
     * @return
     */
    public static BigDecimal div(BigDecimal a, BigDecimal b, int scale) {
        return a.divide(b, scale, RoundingMode.HALF_UP);
    }

    /**
     * |a|
     * 
     * @param a
     * @return
     */
    public static BigDecimal abs(BigDecimal a) {
        return a.abs();
    }

    /**
     * a == b ?
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Integer a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    /**
     * a == b ?
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Long a, Long b) {
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    /**
     * 
     * string 2 int <功能详细描述>
     * 
     * @param number
     * @return
     */
    public static int parseInt(String number) {
        return parseInt(number, 0);
    }

    /**
     * 
     * string 2 int <功能详细描述>
     * 
     * @param number
     * @param value
     * @return
     */
    public static int parseInt(String number, int value) {
        if (ObjectUtils.isEmpty(number)) {
            return value;
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    /**
     * 
     * string 2 long <功能详细描述>
     * 
     * @param number
     * @return
     */
    public static long parseLong(String number) {
        return parseLong(number, 0l);
    }

    /**
     * 
     * string 2 long <功能详细描述>
     * 
     * @param number
     * @param value
     * @return
     */
    public static long parseLong(String number, long value) {
        if (ObjectUtils.isEmpty(number)) {
            return value;
        }
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    /**
     * 
     * string 2 float
     * 
     * @param number
     * @return
     */
    public static float parseFloat(String number) {
        return parseFloat(number, 0f);
    }

    /**
     * 
     * string 2 float
     * 
     * @param number
     * @param value
     * @return
     */
    public static float parseFloat(String number, float value) {
        if (ObjectUtils.isEmpty(number)) {
            return value;
        }

        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    /**
     * 
     * string 2 double <功能详细描述>
     * 
     * @param number
     * @return
     */
    public static double parseDouble(String number) {
        return parseDouble(number, 0d);
    }

    /**
     * 
     * string 2 double <功能详细描述>
     * 
     * @param number
     * @return
     */
    public static double parseDouble(String number, double value) {
        if (ObjectUtils.isEmpty(number)) {
            return value;
        }

        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    /**
     * 是否是无符号整数
     * 
     * @param source
     * @return
     */
    public static boolean isNumeric(String str) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * 是否是浮点数 <功能详细描述>
     * 
     * @param number
     * @return
     */
    public static boolean isDecimal(String number) {
        return number != null && number.matches(DECIMAL);
    }

    /**
     * 
     * 浮点数转换为百分比 <功能详细描述>
     * 
     * @param value
     * @return
     */
    public static String double2Percent(double value) {
        NumberFormat fmt = NumberFormat.getPercentInstance();
        fmt.setMaximumFractionDigits(2);
        return fmt.format(value);
    }

    /**
     * 
     * <一句话功能简述> <功能详细描述>
     * 
     * @param value
     * @param format
     * @return
     */
    public static String formatCurrency(BigDecimal value, Locale l) {
        NumberFormat fmt = DecimalFormat.getCurrencyInstance(l);
        return fmt.format(value);
    }
    
    
}
