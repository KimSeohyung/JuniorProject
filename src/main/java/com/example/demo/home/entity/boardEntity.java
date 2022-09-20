package com.example.demo.home.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class boardEntity {

    @Id
    @GeneratedValue
    private Integer board_num;

}
