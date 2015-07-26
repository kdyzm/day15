package cn.itcast.contact.service;

import java.util.List;

import cn.itcast.contact.dao.ContactDao;
import cn.itcast.domain.Contact;

public class ContactService {
	private ContactDao dao = new ContactDao();
	public List<Contact> query(String uid) throws Exception{
		return dao.query(uid);
	}
	public void del(String cid) throws Exception{
		dao.del(cid);
	}
}
