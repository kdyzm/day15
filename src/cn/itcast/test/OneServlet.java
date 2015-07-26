package cn.itcast.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.utils.BaseServlet;

public class OneServlet extends BaseServlet {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		System.err.println("这是默认的方法。。。。。");
	}
	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		System.err.println("这是login。。。。。");
	}
	public void exit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		System.err.println("这是exit。。。。。");
	}

}
