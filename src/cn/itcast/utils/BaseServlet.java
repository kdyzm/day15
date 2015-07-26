package cn.itcast.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 分发的基类
 * @author Administrator
 */
public abstract class BaseServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	//http://server:port/project/someServlet?cmd=someMethod
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("UTf-8");
		//获取参数
		String methodName = req.getParameter("cmd");
		if(methodName==null || methodName.trim().equals("")){
			methodName="execute";//默认方法
		}
		//通过反射调用子类的方法
		try{
			Method method = this.getClass()
						.getMethod(methodName,
						HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,req,resp);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	//要求子类必须要拥有exexute方法
	public abstract void execute(HttpServletRequest req,HttpServletResponse resp) throws Exception;
}
