package com.yongseong.spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoggingService {

    void logRequest(HttpServletRequest httpSErvletRequest, Object body);

    void logResponse(HttpServletRequest httpServletREquest, HttpServletResponse httpServletResponse, Object body);
}