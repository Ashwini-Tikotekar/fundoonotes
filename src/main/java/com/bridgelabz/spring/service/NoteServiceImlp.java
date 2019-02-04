package com.bridgelabz.spring.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.NoteDao;
import com.bridgelabz.spring.model.Note;

import com.bridgelabz.spring.utility.TokenGenerator;


@Service
public class NoteServiceImlp implements NoteService {
	@Autowired
	private NoteDao noteDao;
	@Autowired
	private TokenGenerator tokenGenerator;
	@Transactional
	public boolean  createNote(Note user, HttpServletRequest request) {
		int id = noteDao.createNote(user);
		if (id > 0) {
			String token = tokenGenerator.generateToken(String.valueOf(id));
			System.out.println(token);
			return true;
		}
		return false;
	}
	public Note updateNote(int id, Note user, HttpServletRequest request) {
		Note user2 = noteDao.getNoteById(id);
		if (user2 != null) {
			user2.setNoteId(user.getNoteId());
			user2.setTitle(user.getTitle());
			user2.setDescription(user.getDescription());
			user2.setUpdated_Date(user.getUpdated_Date());
			user2.setCreated_Date(user.getCreated_Date());
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
}
