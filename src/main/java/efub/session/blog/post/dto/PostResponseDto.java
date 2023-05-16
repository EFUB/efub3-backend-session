package efub.session.blog.post.dto;


import efub.session.blog.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostResponseDto {

    private Long postId;
    private String writerName;
    private String title;
    private String content;
    // 응답 body에는 생성날짜와 수정날짜도 포함되니까 적어주기
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.writerName = post.getWriter().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(post);
    }

}
