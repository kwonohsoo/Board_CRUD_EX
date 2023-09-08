package com.example.boardcrudex.domain.board.entity;

import com.example.boardcrudex.global.user.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Member member;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
