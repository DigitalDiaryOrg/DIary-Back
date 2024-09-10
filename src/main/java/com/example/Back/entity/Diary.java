package com.example.Back.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="diary")
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", updatable = false)
	private Member member;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="emotion_field")
	private String[] emotionField;
	
	@Column(name="music_title")
	private String musicTitle;
	
	@Column(name="music_id")
	private String musicId;
	
	
	
}
