package com.qs.bluewhale.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    //默认异常处理页面
    private static final String DEFAUL_ERROR_VIEW = "error";

    //权限不足异常处理页面
    private static final String NO_AUTH_EXCEPTION_VIEW = "exception/noAuth";

    /**
     * 默认异常处理方法,返回异常请求路径和异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        //异常请求路径
        mav.addObject("url", "请求路径：" + request.getRequestURI());
        //返回异常处理页面
        mav.setViewName(DEFAUL_ERROR_VIEW);
        return mav;
    }

    /**
     * 权限不足异常处理
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public ModelAndView authorizationExceptionHandler(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(NO_AUTH_EXCEPTION_VIEW);
        mv.addObject("msg", "当前用户权限不足！");
        return mv;
    }
}
