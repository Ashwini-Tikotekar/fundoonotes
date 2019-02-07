package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteService {
	public boolean createNote(Note user,int id, HttpServletRequest request);

	//public Note loginNote(String emailId, String password, HttpServletRequest request);

	public Note updateNote(int id, Note user, HttpServletRequest request);

	public Note deleteNote(int id, HttpServletRequest request);

	public List<Note> retrieveNote(int id,HttpServletRequest request);
	 
	public boolean createLabel(Label label ,int id, HttpServletRequest request);
	
	public Label deleteLabel(int id, HttpServletRequest request);
	
	public Label editLabel(int id, Label label, HttpServletRequest request);

	public List<Label> retrieveLabel(int id,HttpServletRequest request);
	 

}
