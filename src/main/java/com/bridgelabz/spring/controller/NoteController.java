package com.bridgelabz.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

import com.bridgelabz.spring.service.NoteService;


@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<Void> createNote(@RequestBody Note user, HttpServletRequest request,@RequestParam("id") int id) {
		try {
			if (noteService.createNote(user,id, request))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);


	}
	@RequestMapping(value = "/updatenote", method = RequestMethod.POST)
	public ResponseEntity<?> updateNote(@RequestParam("id") int id, @RequestBody Note user,
			HttpServletRequest request) {

		Note user2 = noteService.updateNote(id, user, request);
		if (user2 != null) {
			return new ResponseEntity<Note>(user2, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@RequestParam("id") int id, HttpServletRequest request) {

		Note user = noteService.deleteNote(id, request);
		if (user != null) {
			return new ResponseEntity<Note>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/retrievenote", method = RequestMethod.GET)
	public ResponseEntity<?> createNote(@RequestParam("id") int id,HttpServletRequest request) {
		List<Note> listOfNote = noteService.retrieveNote(id,request);
		if (!listOfNote.isEmpty()) {
			return new ResponseEntity<List<Note>>(listOfNote, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<Void> createLabel(@RequestHeader("token") String token, @RequestParam("id")int id,@RequestBody Label label, HttpServletRequest request,HttpServletResponse responsew) {
		try {
			if (noteService.createLabel(label,id, request))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);

}
	
	@RequestMapping(value = "/deletelabel", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLabel(@RequestParam("id") int id, HttpServletRequest request) {

		Label label = noteService.deleteLabel(id, request);
		if (label != null) {
			return new ResponseEntity<Label>(label, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("label not found",
					HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/editlabel", method = RequestMethod.POST)
	public ResponseEntity<?> editLabel(@RequestParam("id") int id, @RequestBody Label label ,
			HttpServletRequest request) {

		Label label1 = noteService.editLabel(id, label, request);
		if (label1 != null) {
			return new ResponseEntity<Label>(label1, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("label not found",
					HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/retrievelabel", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveLabel(@RequestParam("id") int id,HttpServletRequest request) {
		List<Label> listOfLabel = noteService.retrieveLabel(id,request);
		if (!listOfLabel.isEmpty()) {
			return new ResponseEntity<List<Label>>(listOfLabel, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Label not found",
					HttpStatus.NOT_FOUND);
		}
	}
}
