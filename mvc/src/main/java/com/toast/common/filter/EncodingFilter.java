package com.toast.common.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe 定义字符集过滤器
 */
public class EncodingFilter extends HttpFilter {
    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig.getInitParameter("charset") != null) {
            this.charset = filterConfig.getInitParameter("charset");
        }
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(this.charset);
        response.setCharacterEncoding(this.charset);
        chain.doFilter(request, response);
    }
}
