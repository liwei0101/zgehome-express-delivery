package com.zhengtoon.app.express.company.commons.exception;

import com.zhengtoon.framework.entity.CodeMessage;


/**
 * 应用异常编码
 *
 * @author
 * @since
 */
public class ExceptionCode {

    /**
     * 成功
     */
    public static final CodeMessage SUCCESS = new CodeMessage(0, "成功");

    /**
     * 服务器异常
     */
    public static final CodeMessage SERVICE_BUSY = new CodeMessage(999999, "服务器繁忙，请稍后重试");

    /**
     * 参数为空 20001
     */
    public static final CodeMessage PARAM_IS_NULL = new CodeMessage(20001, "参数为空");

    /**
     * 参数非法 20002
     */
    public static final CodeMessage PARAM_IS_ILLEGAL = new CodeMessage(20002, "参数非法");

}