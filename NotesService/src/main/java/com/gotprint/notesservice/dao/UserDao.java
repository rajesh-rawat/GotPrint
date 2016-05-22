package com.gotprint.notesservice.dao;

import java.util.List;

import org.hibernate.Query;

import com.gotprint.notesservice.domain.object.User;

/**
 * @author Rajesh Rawat
 *
 */
public class UserDao extends BaseDao{

	/**
	 * Takes input as email and returns associated user information
	 * 
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email){
		Query query = getCurrentSession().createQuery("from User u where u.email=:email");
		query.setString("email", email);
		List list = query.list();
		User user = ((list==null || list.isEmpty()) ?null: (User)  (list.get(0)));
		return user;
	}
}
