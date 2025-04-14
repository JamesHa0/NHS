package com.jameshao.nhsserver.utils;

public class FLAGS {

    //处理结果标识码
    public static final String RETURN_FLAG = "result";

    //处理结果成功标志
    public static final String RETURN_SUCCESS = "success";

    //处理结果失败标志
    public static final String RETURN_FAILED = "failed";

    //处理结果返回数据标志
    public static final String SUCCESS_MSG = "data";

    //处理结果返回错误信息标志
    public static final String FAILED_MSG = "error";

    //程序标志（异常）
    public static final String ERROR_MSG = "process error";

    //登陆失败提示语
    public static final String LOGIN_FAIL = "wrong username or password!";

    //token关键词
    public static final String TOKEN = "token";

    public static String NO_LOGIN = "no login";
    public static String LOGIN_OUTOFTINE = "logout of time";

    //token密钥
    public static String SECRET = "22023237";

    public static Integer REDIS_OUTOF_TIME = 50;
}
