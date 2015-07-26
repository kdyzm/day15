package cn.itcast.user.service;

import cn.itcast.domain.User;
import cn.itcast.user.dao.UserDao;

public class UserService {
	private UserDao dao = new UserDao();
	public User login(User user) throws Exception{
		return dao.login(user);
	}
}
