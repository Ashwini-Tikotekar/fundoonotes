package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteService {
	 boolean createNote(String token,Note note, HttpServletRequest request);

	//public Note loginNote(String emailId, String password, HttpServletRequest request);

	 Note updateNote(String token,int noteId, Note note, HttpServletRequest request);

	 Note deleteNote(String token,int noteId, HttpServletRequest request);

	 List<Note> retrieveNote(String token,HttpServletRequest request);
	 
	 boolean createLabel(Label label ,int id, HttpServletRequest request);
	
	 Label deleteLabel(int id, HttpServletRequest request);
	
	 Label editLabel(int id, Label label, HttpServletRequest request);

	 List<Label> retrieveLabel(int id,HttpServletRequest request);
	
	 boolean addNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);
	 
	 boolean removeNoteLabel(String token, int noteId, int labelId, HttpServletRequest request);
	
}
