package com.bridgelabz.spring.model;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@SuppressWarnings("serial")
@Entity
@Table(name= "Note")
public class Note implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "noteId")
	private int noteId;

	@Column(name = "Title")
	private String Title;

	@Column(name = "description")
	private String description;

	@CreationTimestamp
	@Column(name = "created_Date")
	private Timestamp created_Date;

	@CreationTimestamp
	@Column(name="updated_Date")
	private Timestamp updated_Date;

	@Column(name="isArchive")
	private boolean isArchive;

	@Column(name="isPinned")
	private boolean isPinned;

	@Column(name="inTrash")
	private boolean inTrash;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Timestamp created_Date) {
		this.created_Date = created_Date;
	}

	public Timestamp getUpdated_Date() {
		return updated_Date;
	}

	public void setUpdated_Date(Timestamp updated_Date) {
		this.updated_Date = updated_Date;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isInTrash() {
		return inTrash;
	}

	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", Title=" + Title + ", description=" + description + ", created_Date="
				+ created_Date + ", updated_Date=" + updated_Date + ", isArchive=" + isArchive + ", isPinned="
				+ isPinned + ", inTrash=" + inTrash + "]";
	}



}
