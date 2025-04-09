package com.jameshao.nhsserver.intercepter;

import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.utils.FLAGS;
import com.jameshao.nhsserver.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印处理器名称
        String wholePath = handler.toString();
        String methodName = wholePath.substring(wholePath.indexOf("#")+1);

        System.out.print("==========处理器:[");
        System.out.print(methodName);
        System.out.println("]==========");

        JSONReturn jsonReturn = new JSONReturn();
        //验证Token
        try {
            String token = request.getHeader(FLAGS.TOKEN);
            if (token == null || token.trim().isEmpty()){
                response.getWriter().write(jsonReturn.returnError("未登录或登录超时，请重新登录！(You'll need to log in!)"));
                //不调用拦截器，直接返回
                return false;
            }

            //验证Token加解密
            TokenUtil.authToken(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(jsonReturn.returnError(e.getMessage()));
            return false;
        }

    }
}
