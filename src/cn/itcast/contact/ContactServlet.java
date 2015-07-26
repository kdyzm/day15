package cn.itcast.contact;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.contact.service.ContactService;
import cn.itcast.domain.Contact;
import cn.itcast.domain.User;
import cn.itcast.utils.BaseServlet;

public class ContactServlet extends BaseServlet {
	private ContactService service = new ContactService();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		User u = (User) req.getSession().getAttribute("user");
		List<Contact> cs = service.query(u.getId());
		//转发
		req.setAttribute("cs", cs);
		req.getRequestDispatcher("/jsps/cs.jsp").forward(req, resp);
	}
	
	public void del(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String cid = req.getParameter("id");
		service.del(cid);
		resp.sendRedirect(req.getContextPath()+"/contacts");
	}

}
