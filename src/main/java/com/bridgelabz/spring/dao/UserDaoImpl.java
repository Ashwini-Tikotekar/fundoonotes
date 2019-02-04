package com.bridgelabz.spring.dao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bridgelabz.spring.model.UserDetails;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int register(UserDetails user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;
	}

	public UserDetails loginUser(String emailId, String password) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from UserDetails where emailId= :emailId and password =:password");
		query.setString("emailId", emailId);
		query.setString("password", password);
		UserDetails user = (UserDetails) query.uniqueResult();
	
		if (user != null) {
			System.out.println("User detail is=" + user.getId() + "," + user.getName() + "," + user.getEmailId() + ","
					+ user.getMobileNumber());
			tx.commit();
			session.close();
			return user;
		} else {
			return null;
		}

	}

	public UserDetails getUserById(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from UserDetails where id= :id");
		query.setInteger("id", id);
		UserDetails user = (UserDetails) query.uniqueResult();
		tx.commit();
		if (user != null) {
			System.out.println("User detail is=" + user.getId() + "," + user.getName() + "," + user.getEmailId() + ","
					+ user.getMobileNumber());
			session.close();
			return user;
		} else {
			return null;
		}
	}

	public void updateUser(int id, UserDetails user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

	public void deleteUser(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from  UserDetails u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

}


