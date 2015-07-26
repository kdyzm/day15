package cn.itcast.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.itcast.domain.User;
import cn.itcast.utils.ConnUtils;

public class UserDao {
	//登录功能
	public User login(User user) throws SQLException{
		User u = null;
		Connection con = null;
		try{
			con = ConnUtils.getConn();
			String sql = "select * from users where name=? and pwd=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,user.getName());
			pst.setString(2,user.getPwd());
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				u = new User(rs.getString("id"),rs.getString("name"),rs.getString("pwd"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return u;
	}
}
