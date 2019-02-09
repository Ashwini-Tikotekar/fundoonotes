package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteDao {
	 int createNote(Note note);

 // Note loginUser(String emailId, String password);

	 Note getNoteById(int id);

	 void updateNote(int id, Note note);

	 void deleteNote(int id);
	
	 List<Note> retrieveNote(int userId);
	
	 int createLabel(Label label );
	
	 Label getLabelById(int id);
	
	 void deleteLabel(int id);
	
	 void editLabel(int id,Label label);

	 List<Label> retrieveLabel(int id);

}



