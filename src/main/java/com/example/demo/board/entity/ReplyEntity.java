package com.example.demo.board.entity;

import com.example.demo.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.StringNVarcharType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "tb_reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_num", nullable = false, length = 11)
    private Integer replyNum;

    @Column(name = "reply_contents", nullable = false, length = 1000)
    private String replyContents;

    @Column(name = "reply_regidate", length = 6)
    private LocalDateTime replyRegidate = LocalDateTime.now();

    @Column(name = "reply_modidate", length = 6)
    private LocalDateTime replyModidate = LocalDateTime.now();

    @ColumnDefault("0")
    @Column(name = "reply_isdel", length = 1)
    private Integer replyIsdel;

    @Column(name = "board_num", length = 11)
    private Integer boardNum;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "user_num")
    private Member member;
}
