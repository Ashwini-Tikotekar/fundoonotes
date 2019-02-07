package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.NoteDao;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.TokenGenerator;


@Service
public class NoteServiceImlp implements NoteService {
	@Autowired
	private NoteDao noteDao;
	@Autowired
	private TokenGenerator tokenGenerator;
	@Autowired
	private UserDao userDao;

	@Transactional
	public boolean createNote(Note note, int id,HttpServletRequest request ) {
		UserDetails user = userDao.getUserById(id);
		if (user != null) {
			//note.setId(user);
			int noteid = noteDao.createNote(note);
			if (noteid > 0) {
				return true;
			}
		}        return false;
	}

	public Note updateNote(int id, Note user, HttpServletRequest request) {
		Note user2 = noteDao.getNoteById(id);
		if (user2 != null) {
			user2.setTitle(user.getTitle());
			user2.setDescription(user.getDescription());
			user2.setArchive(user.isArchive());
			user2.setPinned(user.isPinned());
			user2.setInTrash(user.isInTrash());
			noteDao.updateNote(id, user2);
		}
		return user2;
	}

	public Note deleteNote(int id, HttpServletRequest request) {
		Note user2 = noteDao.getNoteById(id);
		if (user2 != null) {
			noteDao.deleteNote(id);
		}
		return user2;


	}


	@Transactional
	public List<Note> retrieveNote(int id,HttpServletRequest request) {
		List<Note> listOfNote = noteDao.retrieveNote(id);
		if (!listOfNote.isEmpty()) {
			return listOfNote;
		}
		return null;
	}


	@Transactional
	public boolean createLabel ( Label label,int id,HttpServletRequest request) {
		UserDetails user = userDao.getUserById(id);
		if (user != null) {
			label.setId(user);
		
			int labelid = noteDao.createLabel(label);
			if (labelid > 0) {
				return true;
			}
		}	       return false;
	}
	
	
	

	public Label deleteLabel(int id, HttpServletRequest request) {
		Label user2 = noteDao.getLabelById(id);
		if (user2 != null) {
			noteDao.deleteLabel(id);
		}
		return user2;

}
	@Transactional
	public Label editLabel(int id, Label label, HttpServletRequest request) {
		Label label1 = noteDao.getLabelById(id);
		
		if (label1 != null) {
			label1.setLabelName(label.getLabelName());
			noteDao.editLabel(id, label1);
		}
		return label1;
	}
	@Transactional
	public List<Label> retrieveLabel(int id,HttpServletRequest request) {
		List<Label> listOfLabel = noteDao.retrieveLabel(id);
		if (!listOfLabel.isEmpty()) {
			return listOfLabel;
		}
		return null;
	}
}

