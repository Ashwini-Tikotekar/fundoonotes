package com.bridgelabz.spring.service;

import java.util.List;
import java.util.stream.Collectors;

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
	public boolean createNote(String token,Note note,HttpServletRequest request ) {
		int id=tokenGenerator.VerifyToken(token);
		System.out.println("User ID"+id);
		System.out.println("note tiitle= "+note.getTitle());
		UserDetails user = userDao.getUserById(id);
		if (user != null) {
			note.setId(user);
			
			int noteid = noteDao.createNote(note);
			if (noteid > 0) {
				return true;
			}
		}        return false;
	}

	public Note updateNote(String token,int noteId, Note note, HttpServletRequest request) {
		int userId=tokenGenerator.VerifyToken(token);
		UserDetails user=userDao.getUserById(userId);
		if(user!=null) {
			Note cuurentnote = noteDao.getNoteById(noteId);
			if (cuurentnote != null) {
				cuurentnote.setTitle(note.getTitle());
				cuurentnote.setDescription(note.getDescription());
				cuurentnote.setArchive(note.isArchive());
				cuurentnote.setPinned(note.isPinned());
				cuurentnote.setInTrash(note.isInTrash());
				noteDao.updateNote(noteId, cuurentnote);
				return cuurentnote;
			}	
		}
		return null;
		
	}

	public Note deleteNote(String token,int noteId, HttpServletRequest request) {
		int userId=tokenGenerator.VerifyToken(token);
	UserDetails user=userDao.getUserById(userId);
		if(user!=null) {
		Note cuurentnote = noteDao.getNoteById(userId);
		if (cuurentnote != null) {
			noteDao.deleteNote(noteId);
			return cuurentnote;
		}
		}	
		return null;
	}

	@Transactional
	public List<Note> retrieveNote(String token,HttpServletRequest request) {
		int id=tokenGenerator.VerifyToken(token);
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
		Label label = noteDao.getLabelById(id);
		if ( label!= null) {
			noteDao.deleteLabel(id);
		}
		return label;
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
	@Transactional
	public boolean addNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {

		int id=tokenGenerator.VerifyToken(token);
		UserDetails user=userDao.getUserById(id);
		if(user!=null) {
			Note notes=noteDao.getNoteById(noteId);
			Label label=noteDao.getLabelById(labelId);
			List<Label> listOfLabel = notes.getLabelList();
			listOfLabel.add(label);
			if (!listOfLabel.isEmpty()) {
				notes.setLabelList(listOfLabel);
				noteDao.updateNote(1, notes);
				return true;
			}
		}
		return false;
	}



	public boolean removeNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {
		int id = tokenGenerator.VerifyToken(token);
		UserDetails user = userDao.getUserById(id);
		
		if (user != null) {
			Note residingNote = noteDao.getNoteById(noteId);
			List<Label> labels = residingNote.getLabelList();
			if (!labels.isEmpty()) {
				labels = labels.stream().filter(label -> label.getLabelId() != labelId)
						.collect(Collectors.toList());
				residingNote.setLabelList(labels);
				noteDao.updateNote(noteId, residingNote);
				return true;
			}
		}
		return false;
	}
}

