package com.example.demo.member.entity;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "tb_user")

public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_num", nullable = false, length = 11)
    private Integer userNum;

    @Column(name = "user_email", nullable = false, length = 300)
    private String userEmail;

    @Column(name = "user_name", nullable = false, length = 300)
    private String userName;

    @Column(name = "user_pw", nullable = false, length = 300)
    private String userPw;

    @Column(name = "user_phone", nullable = false, length = 300)
    private String userPhone;

    @Column(name = "user_admin", length = 300)
    private String userAdmin;

    @ColumnDefault("0")
    @Column(name = "user_isdel", length = 1)
    private Integer userIsdel;
}

