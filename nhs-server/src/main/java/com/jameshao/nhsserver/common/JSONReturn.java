package com.jameshao.nhsserver.common;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jameshao.nhsserver.utils.FLAGS;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * 返回json统一格式：
 * {
 *   result: success/error,
 *   data: [处理结果: 实体类对象/列表/字符串等类型](result是"success"时返回)
 *   error:[处理结果: 字符串](result是"error"时返回)
 * }
 * */

@Component
public class JSONReturn {
    private Boolean bsuccess;
    private String errorMsg;
    private Object data;
    private Map resultMap = new HashMap<>();
    private JSONObject jsonResult = new JSONObject();

    public String returnSuccess() throws JsonProcessingException {
        resultMap.put(FLAGS.RETURN_FLAG, FLAGS.RETURN_SUCCESS);
        resultMap.put(FLAGS.FAILED_MSG, null);
        resultMap.put(FLAGS.SUCCESS_MSG, null);
        return jsonResult.toJSONString(resultMap);
    }

    public String returnSuccess(Object obj) throws JsonProcessingException {
        data = obj;
        resultMap.put(FLAGS.RETURN_FLAG, FLAGS.RETURN_SUCCESS);
        resultMap.put(FLAGS.SUCCESS_MSG, data);
        resultMap.put(FLAGS.FAILED_MSG, null);
        return jsonResult.toJSONString(resultMap);
    }

    public String returnFailed() throws JsonProcessingException {
        resultMap.put(FLAGS.RETURN_FLAG, FLAGS.RETURN_FAILED);
        resultMap.put(FLAGS.SUCCESS_MSG, null);
        resultMap.put(FLAGS.FAILED_MSG, null);
        return jsonResult.toJSONString(resultMap);
    }

    public String returnFailed(String errorMsg) throws JsonProcessingException {
        this.errorMsg = errorMsg;
        resultMap.put(FLAGS.RETURN_FLAG, FLAGS.RETURN_FAILED);
        resultMap.put(FLAGS.FAILED_MSG, this.errorMsg);
        resultMap.put(FLAGS.SUCCESS_MSG, null);
        return jsonResult.toJSONString(resultMap);
    }
    public String returnError(String errorMsg){
        StringBuffer result = new StringBuffer();
        resultMap.put(FLAGS.SUCCESS_MSG, null);

        result.append(FLAGS.RETURN_FLAG).append(":").append(FLAGS.RETURN_FAILED)
                .append(",")
                .append(FLAGS.FAILED_MSG).append(":").append(errorMsg);
        return result.toString();
    }
}
