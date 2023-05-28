package efub.session.blog.board.dto.response;

import efub.session.blog.board.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {
    private Long postId;
    private String writerName;
    private String title;
    private String content;

    private Integer heartCount;
    private Boolean isHeart;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostResponseDto of(Post board) {
        return PostResponseDto.builder()
                .postId(board.getPostId())
                .title(board.getTitle())
                .content(board.getContent())
                .writerName(board.getWriter().getNickname())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();
    }
    public void uploadHeart(Integer heartCount, boolean isHeart) {
        this.heartCount = heartCount;
        this.isHeart = isHeart;

    }

}

