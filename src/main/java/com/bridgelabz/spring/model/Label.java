package com.bridgelabz.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	@Entity
	@Table(name= "Label")
public class  Label implements Serializable{
		@Id
		@GeneratedValue
		@Column(name = "LabelId")
		private int LabelId;

		@Column(name = "LabelName")
		private String LabelName;
		
		@ManyToOne
		@JoinColumn(name="id")
		private UserDetails id;

		public int getLabelId() {
			return LabelId;
		}

		public void setLabelId(int labelId) {
			LabelId = labelId;
		}

		public String getLabelName() {
			return LabelName;
		}

		public void setLabelName(String labelName) {
			LabelName = labelName;
		}

		@Override
		public String toString() {
			return "Label [LabelId=" + LabelId + ", LabelName=" + LabelName + "]";
		}

		public UserDetails getId() {
			return id;
		}

		public void setId(UserDetails id) {
			this.id = id;
		}
}
