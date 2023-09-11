package com.example.boardcrudex.domain.reply.entity;

import com.example.boardcrudex.domain.board.entity.Board;
import com.example.boardcrudex.global.user.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String comment;

    public void update(String comment) {
        this.comment = comment;
    }

}
