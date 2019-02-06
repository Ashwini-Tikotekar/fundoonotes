package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.Note;

public interface NoteDao {
	public int createNote(Note user);

	//	public Note loginUser(String emailId, String password);

	public Note getNoteById(int id);

	public void updateNote(int id, Note user);

	public void deleteNote(int id);
	public List<Note> retrieveNote(int id);
}
