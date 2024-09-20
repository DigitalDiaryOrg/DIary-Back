package com.example.Back;

import com.example.Back.init.TestDataInit;
import com.example.Back.repository.DiaryRepository;
import com.example.Back.repository.MemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
	@Bean
	@Profile("local")
	public TestDataInit testDataInit(MemberRepository memberRepository, DiaryRepository diaryRepository) {
		return new TestDataInit(memberRepository, diaryRepository);
	}
}
