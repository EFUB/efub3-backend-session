package efub.session.blog.post.dto.response;

import efub.session.blog.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String writerName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Long postId, String writerName, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.postId = postId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static PostResponseDto from(Post post){
        return new PostResponseDto(
                post.getPostId(),
                post.getWriter().getNickname(), // getWriter()만이 아니라 getNickname()까지 해줘야 됨
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate());
    }
}
