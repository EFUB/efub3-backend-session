package efub.session.blog.board.dto;

import efub.session.blog.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String boardName;
    private String description;
    private String notice;
    private String ownerName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board){
        this.boardId=board.getBoardId();
        this.boardName=board.getName();
        this.description=board.getDescription();
        this.notice=board.getNotice();
        this.ownerName=board.getOwner().getNickname();
        this.createdDate=board.getCreatedDate();
        this.modifiedDate=board.getModifiedDate();
    }
}
