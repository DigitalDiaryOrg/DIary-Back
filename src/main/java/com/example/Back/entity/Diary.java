package com.example.Back.entity;

import java.util.Date;
import java.util.List;

import com.example.Back.dto.DiaryDto;
import com.example.Back.repository.DiaryRepository;
import com.example.Back.repository.converter.EmotionFieldConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name="diary")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_id", updatable = false)
	private Member member;

	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="emotion_field")
	@Convert(converter = EmotionFieldConverter.class)
	private List<String> emotionField;

	@Column(name="music_title")
	private String musicTitle;

	@Column(name="music_id")
	private String musicId;

	@Column(name = "content")
	private String content;

	@Column(name = "praise")
	private String praise;

	@Builder
	public Diary(Member member, List<String> emotionField, String musicTitle,
				 String musicId, String content, String praise, Date date) {
		this.member = member;
		this.emotionField = emotionField;
		this.musicTitle = musicTitle;
		this.musicId = musicId;
		this.content = content;
		this.praise = praise;
		this.date = date;
	}

	public Diary update(Diary newDiary){
		this.emotionField=newDiary.getEmotionField();
		this.musicId=newDiary.getMusicId();
		this.musicTitle=newDiary.getMusicTitle();
		this.content=newDiary.getContent();
		this.praise=newDiary.getPraise();

		return this;
	}

	
}
