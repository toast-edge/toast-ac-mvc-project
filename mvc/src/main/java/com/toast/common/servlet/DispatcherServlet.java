package com.toast.common.servlet;

import com.toast.common.mvc.bean.ControllerRequestMapping;
import com.toast.common.mvc.util.ScannerPackageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe 请求分发处理机制
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {    // 初始化的时候进行扫描配置
        // 获取ServletContext所配置的初始化上下文参数
        String basePackage = super.getServletContext().getInitParameter("base-package"); // 获取初始化参数
        System.out.println("dispatcherServlet init");
        ScannerPackageUtil.scannerHandle(super.getClass(), basePackage); // 扫描处理
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取了当前请求路径，而这个路径恰好就是Action配置的映射路径
        String path = req.getServletPath().substring(0, req.getServletPath().lastIndexOf("."));
        // 包扫描完成之后，所有的映射路径实际上都保存在了“Map”集合之中
        ControllerRequestMapping mapping = ScannerPackageUtil.getActionMap().get(path);
        // 此时可以获取到映射的Action类（Class实例）以及对应的Method实例
        try {
            Object actionObject = mapping.getActionClass().getDeclaredConstructor().newInstance(); // 反射获取Action类对象
            mapping.getActionMethod().invoke(actionObject); // 方法的反射调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}