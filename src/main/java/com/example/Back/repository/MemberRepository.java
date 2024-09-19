package com.example.Back.repository;

import com.example.Back.entity.Member;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends ListCrudRepository<Member, Long> {
}
