package com.toast.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
