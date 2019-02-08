package com.bridgelabz.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;



@Repository
public class NoteDaoImpl implements NoteDao{
	

	@Autowired
	private SessionFactory sessionFactory;

	public int createNote(Note note) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(note);
		return userId;

	}
	public Note getNoteById(int id) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Note where noteId= :noteId");
		query.setInteger("noteId", id);
		Note note = (Note) query.uniqueResult();
		tx.commit();
		if (note != null) {
			System.out.println("Note detail is=" + note.getNoteId() + "," + note.getDescription() + "," + note.getTitle() + ","
					+ note.getCreated_Date()+","+note.getUpdated_Date());
			session.close();
			return note;
		} else {
			return null;
		}
	}

	public void updateNote(int id, Note note) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(note);
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

	public int createLabel(Label label) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(label);
		return userId;
	}
	
	public Label getLabelById(int id) {
		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Label where LabelId= :LabelId");
		query.setInteger("LabelId", id);
		Label label = (Label) query.uniqueResult();
		if (label != null) {
			System.out.println("label detail is=" + label.getLabelId() + "," + label.getLabelName()); 
			session.close();
			return label;
		} else {
			return null;
		}
	}

	public void deleteLabel(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("DELETE from  Label u where u.id= :id");
		query.setInteger("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	
	public void editLabel(int id, Label label) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(label);
		tx.commit();
		session.close();
	}
	public List<Label> retrieveLabel(int id) {
        Session session = sessionFactory.openSession();
        Query query=session.createQuery("from Label where id= :id");
        query.setInteger("id", id);
        List<Label> listOfLabel = query.list();
        return listOfLabel;
    }

		
	}






	