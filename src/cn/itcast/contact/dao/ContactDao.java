package cn.itcast.contact.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.domain.Contact;
import cn.itcast.utils.ConnUtils;

public class ContactDao {
	public List<Contact> query(String uid) throws Exception{
		List<Contact> cs = new ArrayList<Contact>();
		Connection con = null;
		try{
			con = ConnUtils.getConn();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from contacts where uid='"+uid+"'");
			while(rs.next()){
				Contact c = 
						new Contact(rs.getString("id"),rs.getString("name"), rs.getString("sex"),rs.getString("tel"),
								rs.getString("addr"));
				cs.add(c);
			}
		}finally{
			con.close();
		}
		return cs;
	}
	//删除
	public void del(String cid) throws Exception{
		Connection con = null;
		try{
			con = ConnUtils.getConn();
			Statement st = con.createStatement();
			String sql = "delete from contacts where id='"+cid+"'";
			System.err.println(sql);
			st.execute(sql);
		}finally{
			con.close();
		}
	}
}
