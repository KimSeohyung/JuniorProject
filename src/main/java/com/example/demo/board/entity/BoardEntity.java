package com.example.demo.board.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "tb_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer board_num;

    private String board_title;
    private String board_contents;

    @ColumnDefault("0")
    private Integer board_viewcounts;

    private String board_type;

    private LocalDateTime board_regidate = LocalDateTime.now();
    private LocalDateTime board_modidate = LocalDateTime.now();

    private Integer user_num;



}
