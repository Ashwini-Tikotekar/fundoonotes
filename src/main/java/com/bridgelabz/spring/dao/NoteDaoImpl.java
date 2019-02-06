package com.bridgelabz.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.model.Note;



@Repository
public class NoteDaoImpl implements NoteDao{
	

	@Autowired
	private SessionFactory sessionFactory;

	public int createNote(Note user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;

	}
	public Note getNoteById(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Note where id= :id");
		query.setInteger("id", id);
		Note user = (Note) query.uniqueResult();
		tx.commit();
		if (user != null) {
			System.out.println("User detail is=" + user.getNoteId() + "," + user.getDescription() + "," + user.getTitle() + ","
					+ user.getCreated_Date()+","+user.getUpdated_Date());
			session.close();
			return user;
		} else {
			return null;
		}
	}

	public void updateNote(int id, Note user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

	public void deleteNote(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from  Note u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	public List<Note> retrieveNote(int id) {
        Session session = sessionFactory.openSession();
        Query query=session.createQuery("from Note where id= :id");
        query.setInteger("userId", id);
        List<Note> listOfNote = query.list();
        return listOfNote;
    }


	}





	