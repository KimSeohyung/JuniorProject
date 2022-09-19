package com.example.demo.member.service.request;



import com.example.demo.member.entity.ColumnEncryptor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_user")
@DynamicInsert
public class UserInsertCommand {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int user_num ;

    private String user_email;
    private String user_name;

    @Convert(converter = ColumnEncryptor.class)
    private String user_pw;

    private String user_phone;

    @ColumnDefault("0")
    private String user_admin;
}
