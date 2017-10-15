package com.github.cherry.framework.common.utils;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class MaskUtils {

    public static final int FROM_HEAD = 0;

    public static final int FROM_END = 1;

    private MaskUtils() {
    }

    /**
     * 
     * 脱敏处理
     * 
     * @param info
     * @param first 从0开始
     * @param last
     * @return
     */
    public static String mask(String info, int first, int last) {
        if (info == null || info.length() == 0 || first < 0 || last < 0 || last < first || last > info.length() - 1) {
            return info;
        }

        StringBuffer sb = new StringBuffer(info.substring(0, first));

        for (int i = first; i <= last; i++) {
            sb.append("*");
        }

        int less = info.length() - sb.length();

        if (less > 0) {
            sb.append(info.substring(info.length() - less));
        }

        return sb.toString();
    }

    /**
     * 
     * 计算脱敏未知
     * 
     */
    public static int[] maskIndex(String value, int maskType, int mask) {
        int[] index = new int[2];
        int start = 0;
        int end = 0;

        switch (maskType) {
            case FROM_HEAD:
                start = mask;
                end = value.length() - 1;
                break;
            case FROM_END:
                start = 0;
                end = value.length() - mask - 1;
                break;
        }

        if (start > value.length() - 1) {
            start = value.length() - 1;
        }

        if (end < 0) {
            end = value.length() - 1;
        }

        if (start > end) {
            end = start;
        }

        index[0] = start;
        index[1] = end;

        return index;
    }
}
