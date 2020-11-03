package com.zhengtoon.config.loaded.plugin.commons.constant;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 常量类
 */
public final class CommonConstants {

    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);

    public static DateTimeFormatter dateTimeFormatterStandard = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DateTimeFormatter dateTimeFormatterFirst = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DateTimeFormatter dateTimeFormatterSecond = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static DateTimeFormatter dateTimeFormatterThird = DateTimeFormatter.ofPattern("HH:mm");

    public static final Integer INTEGER_NEGA_ONE = -1;

    public static final Integer INTEGER_ZERO = 0;

    public static final Integer INTEGER_ONE = 1;

    public static final Integer INTEGER_TWO = 2;

    public static final Integer INTEGER_THREE = 3;

    public static final Integer INTEGER_FOUR = 4;

    public static final Integer INTEGER_FIVE = 5;

    public static final Integer INTEGER_SIX = 6;

    public static final Long LONG_NEGA_ONE = -1L;

    public static final Long LONG_ZERO = 0L;

    public static final Long LONG_ONE = 1L;

    //分页参数
    public static final Integer PAGE_NO = 1;

    //分页参数
    public static final Integer PAGE_SIZE = 20;

    //发布状态-下架
    public static final Integer PUB_STATUS_ONE = 1;

    //发布状态-发布
    public static final Integer PUB_STATUS_TWO = 2;
}
