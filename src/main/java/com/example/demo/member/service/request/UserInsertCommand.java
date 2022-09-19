package com.example.demo.member.service.request;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_user")
public class UserInsertCommand {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int user_num ;

    private String user_email;
    private String user_name;
    private String user_pw;
    private int user_phone;
    private String user_admin;
}
