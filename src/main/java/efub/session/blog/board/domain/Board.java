package efub.session.blog.board.domain;

import efub.session.blog.board.dto.BoardRequestDto;
import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String notice;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member owner;

    @Builder
    public Board(String name, String description, String notice, Member owner){
        this.name=name;
        this.description=description;
        this.notice=notice;
        this.owner=owner;
    }

    public void updateBoard(BoardRequestDto requestDto){
        this.name=requestDto.getName();
        this.description=requestDto.getDescription();
        this.notice=requestDto.getNotice();
    }
}
