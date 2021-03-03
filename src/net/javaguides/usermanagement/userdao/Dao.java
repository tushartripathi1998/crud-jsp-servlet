package net.javaguides.usermanagement.userdao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.javaguides.usermanagement.model.User;
import net.javaguides.usermanagement.util.HibernateUtil;

public class Dao {
	public User saveData(User user) {
		try {
			Transaction transaction = null;
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occurred on save : "+e.getMessage());
		}
		return user;
	}
	
	public User updateData(User user) {
		try {
			Transaction transaction = null;
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occurred on update : "+e.getMessage());
		}
		return user;
	}

	public List<User> getUsersList() {
		List<User> users = null;
		try {
			Transaction transaction = null;
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			users = session.createQuery("from User",User.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occurred on update : "+e.getMessage());
		}
		return users;
	}

	public User getUser(int userId) {
		System.out.println(userId);
		User  user = null;
		Transaction transaction = null;
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		user = session.get(User.class, userId);
		transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occurred on getUser : "+e.getMessage());
		}
		System.out.println(user.getEmail());
		return user;
	}

	public void delete(int userId) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			User user = this.getUser(userId);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception occurred on delete : "+e.getMessage());
		}
	}
}
