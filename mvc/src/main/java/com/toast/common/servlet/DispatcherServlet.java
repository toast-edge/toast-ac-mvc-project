package com.toast.common.servlet;

import com.toast.common.mvc.bean.ActionUtil;
import com.toast.common.mvc.bean.ControllerRequestMapping;
import com.toast.common.mvc.bean.DependObject;
import com.toast.common.mvc.util.ParameterUtil;
import com.toast.common.mvc.util.ScannerPackageUtil;

import javax.servlet.ServletException;
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
        String dispatcherPath = null; // 跳转路径
        // 获取了当前请求路径，而这个路径恰好就是Action配置的映射路径
        String path = req.getServletPath().substring(0, req.getServletPath().lastIndexOf("."));
        // 包扫描完成之后，所有的映射路径实际上都保存在了“Map”集合之中
        ControllerRequestMapping mapping = ScannerPackageUtil.getActionMap().get(path);
        // 此时可以获取到映射的Action类（Class实例）以及对应的Method实例
        try {
            ServletObject.setRequest(req); // 在当前的线程内保存有request
            ServletObject.setResponse(resp); // 在当前的线程内保存有response
            Object actionObject = mapping.getActionClass().getDeclaredConstructor().newInstance(); // 反射获取Action类对象
            ParameterUtil parameterUtil = new ParameterUtil(req);
            ServletObject.setParameterUtil(parameterUtil);
            // 现在传入的是Action层，则第一次会进行Action结构处理（可以获取到业务层）
            // 而后第二次自己调用的的时候就可以通过业务层获取数据层
            new DependObject(actionObject).injectObject(); // 实现对象注入操作
            // 获取当前Action方法要调用的参数具体内容
            Object params [] = ActionUtil.getMethodParameterValue(actionObject, mapping.getActionMethod());
            // 在进行方法反射调用的时候会有返回内容，而返回的内容统一类型为Object
            Object returnValue = mapping.getActionMethod().invoke(actionObject, params); // 方法的反射调用
            if (returnValue != null) {  // 有返回值，方法的返回值不是void
                if (returnValue instanceof String) {    // 当前方法返回了字符串
                    dispatcherPath = returnValue.toString(); // 直接获取跳转路径
                }
                if (returnValue instanceof ModelAndView) {  // 返回ModelAndView
                    ModelAndView modelAndView = (ModelAndView) returnValue;
                    dispatcherPath = modelAndView.getView(); // 获取跳转的视图路径
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ServletObject.clean(); // 删除当前线程request/response
        }
        if (dispatcherPath != null) {   // 有跳转路径配置
            req.getRequestDispatcher(dispatcherPath).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}