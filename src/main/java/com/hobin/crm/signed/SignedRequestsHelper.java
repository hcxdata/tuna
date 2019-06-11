package com.hobin.crm.signed;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
public class SignedRequestsHelper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ALGORITHM = "HmacSHA1";
    private static final String UTF8_CHARSET = "UTF-8";
    private static final String SEPARATOR = "&";
    static final String SIGN_FLAG = "Signature";
    static final String[] signedParams = { "Version", "AccessKeyId", SIGN_FLAG,
            "SignatureMethod", "Timestamp", "SignatureVersion",
            "SignatureNonce" };

    // 检查请求参数中是否包含签名的字段
    public boolean checkSignRequest(HttpServletRequest request) {
        for (String param : signedParams) {
            String value = request.getParameter(param);
            if (StringUtils.isBlank(value)) {
                String query = request.getQueryString();
                String path = query != null ? request.getRequestURI() + "?" + query : request.getRequestURI();
                logger.error("Illegal request to {} for parameter {} is blank", path, param);
                return false;
            }
        }
        return true;
    }

    public String requestSign(Map<String, String> params, String secret,
            String httpMethod) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);

        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                query.append("&").append(key).append("=")
                        .append(value);
            }
        }

        // 第三步：使用HMAC加密
        String stringSign = stringToSign.append(
                percentEncode(query.toString().substring(1))).toString();
        byte[] bytes = encryptHMAC(stringSign, secret);

        // 第四步：把二进制转化为大写的十六进制或者Base64编码
        // return byte2hex(bytes);
        // return byte2base64(bytes);
        return byte2base64(bytes);
    }

    public byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(
                    secret.getBytes(UTF8_CHARSET), ALGORITHM);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(UTF8_CHARSET));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    public String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public String byte2base64(byte[] bytes) {
        return new String(Base64.encode(bytes));
    }

    private String percentEncode(String s) {
        String out;
        try {
            out = URLEncoder.encode(s, UTF8_CHARSET).replace("+", "%20")
                    .replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            out = s;
        }
        return out;
    }

    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }
    
	public static void main(String[] args) throws IOException {
		String appSecret = "0Sa35kE87rNID0";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", "1.0");
		params.put("AccessKeyId", "iso17025");
		params.put("SignatureMethod", "cnas");
		params.put("Timestamp", "2025");
		params.put("SignatureVersion", "1.0");
		params.put("SignatureNonce", "abc");
		
		SignedRequestsHelper helper = new SignedRequestsHelper();
		System.out.println(helper.requestSign(params, appSecret, "POST"));
	}
}
