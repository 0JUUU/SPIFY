package com.ssafy.house.interceptor;

import com.ssafy.house.model.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(request.getMethod() + " : " + request.getServletPath());
        System.out.println("인터셉터 들어왔음");

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            String token = request.getHeader("token");
            if (token != null && token.length() > 0) {
                System.out.println("토큰 OK?");
                jwtService.checkValid(token);
                logger.info("토큰 사용 가능 : {}", token);
                return true;
            } else {
                throw new RuntimeException("인증 토큰이 없습니다.");
            }
        }
    }
}
