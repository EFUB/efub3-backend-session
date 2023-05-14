package efub.session.blog.board.dto;

import lombok.Getter;
@Getter
public class BoardRequestDto {
    private Long memberId;
    private String name;
    private String description;
    private String notice;
}
