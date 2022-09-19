package com.example.demo.member.entity;

import com.example.demo.member.service.request.UserInsertCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<UserInsertCommand,String> {

}
