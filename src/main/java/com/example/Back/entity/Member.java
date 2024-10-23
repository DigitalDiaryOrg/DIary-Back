package com.example.Back.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="email")
	private String email;

	@Column(name="provider")
	private String provider;

	@Column(name="nickname")
	private String nickname;

	@Column(name="role")
	private String role;


	@Builder
	public Member(String email, String provider, String nickname, String role) {
		this.email = email;
		this.provider = provider;
		this.nickname = nickname;
		this.role=role;
	}

	public void update (String email){
		this.email=email;
	}
}
