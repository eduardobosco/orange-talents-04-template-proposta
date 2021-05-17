package br.com.ot4.proposta.compartilhado;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class RequestHeadersUtil {

    public static final String IP = "ip";
    public static final String USER_AGENT = "user-agent";

    public Map<String, String> getRequestHeaders(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());

        if(ip.equals("0:0:0:0:0:0:0:1"))
            ip = "127.0.0.1";
        result.put(IP, ip);

        String userAgent = request.getHeader("User-Agent");
        result.put(USER_AGENT, userAgent);

        return result;
    }

}