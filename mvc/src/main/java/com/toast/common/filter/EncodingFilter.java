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
 * @time 2023/3/18
 * @describe
 */
public class EncodingFilter extends HttpFilter {
    private String charset = "UTF-8";
    @Override
    public void init(FilterConfig config) throws ServletException {
        if (config.getInitParameter("charset") != null) {
            this.charset = config.getInitParameter("charset");
        }
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(this.charset);
        res.setCharacterEncoding(this.charset);
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
