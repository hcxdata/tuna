package com.hobin.crm.signed;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SignedInteceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SignedRequestsHelper signedRequestsHelper;

    @Value("${server.signed.secret-key}")
    private String appSecret;

    public void setSignedRequestsHelper(
            SignedRequestsHelper signedRequestsHelper) {
        this.signedRequestsHelper = signedRequestsHelper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        // 如果请求中没有包含签名的参数，则直接拒绝
        if (!signedRequestsHelper.checkSignRequest(request)) {
            response.sendError(400, "url is illegal!");
            return false;
        }

        // 构建请求参数的HashMap
        Enumeration<String> pNames = request.getParameterNames();
        Map<String, String> params = new HashMap<String, String>();
        while (pNames.hasMoreElements()) {
            String pName = pNames.nextElement();
            if (SignedRequestsHelper.SIGN_FLAG.equals(pName)) {
                continue;
            }
            params.put(pName, request.getParameter(pName));
        }

        //String appSecret = "0Sa35kE87rNID0";
        String sign = request.getParameter(SignedRequestsHelper.SIGN_FLAG);
        String preSign = signedRequestsHelper.requestSign(params, appSecret,
                request.getMethod().toUpperCase());
        String encodePreSign = URLEncoder.encode(preSign,"UTF-8");
        if (preSign.equals(sign) || encodePreSign.equals(sign)) {
            return true;
        } else {
            logger.error("Request with bad signature, expected '{}', actual '{}'", preSign, sign);
            response.sendError(403, "sign is illegal!");
            return false;
        }
    }
}
