package com.worry.common.fillter;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域
 */
public class WebContextFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        /*httpServletResponse.setHeader("Access-Control-Allow-Headers","Authentication");
        httpServletResponse.setHeader("Access-Control-Allow-Methods","POST");*/
        httpServletResponse.setHeader("Access-Control-Max-Age","1000");
        
        // 响应类型
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");

     	filterChain.doFilter(servletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
