package cn.itcast.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.user.service.UserService;
import cn.itcast.utils.BaseServlet;

public class UserServlet extends BaseServlet {
	//声明Servid
	private UserService service = new UserService();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		//接收参数
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		///封装数据
		User u = new User(null,name,pwd);
		//调用后台
		u = service.login(u);
		if(u==null){
			System.err.println("用户名或是密码错误");
		}else{
			System.err.println("登录成功。。。。");
			//将用户的对象放到session
			req.getSession().setAttribute("user", u);
			//去显示这个用户的所有联联系人
			resp.sendRedirect(req.getContextPath()+"/contacts");
		}
	}
	public void exit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
	}
	public void reg(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
	}

}
