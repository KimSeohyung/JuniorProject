package com.example.demo.board.service.request;

import com.example.demo.board.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_reply")
@DynamicInsert
public class ReplyInsertCommand {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer reply_num;

    private String reply_contents;

    private LocalDateTime reply_regidate=LocalDateTime.now();
    private LocalDateTime reply_modidate=LocalDateTime.now();

    private Integer user_num;
    private Integer board_num;

}
