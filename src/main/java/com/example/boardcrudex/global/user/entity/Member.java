package com.example.boardcrudex.global.user.entity;

import com.example.boardcrudex.domain.board.entity.Board;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Setter
    private String password;

    private String name;

    @Setter
    @ColumnDefault("0")
    private String useYn;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();

    public void update(String password) {
        this.password = password;
    }
}
