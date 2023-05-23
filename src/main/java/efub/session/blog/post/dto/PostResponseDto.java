package efub.session.blog.post.dto;

import efub.session.blog.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private Boolean anonymous;
    private String content;
    private String ownerName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post){
        this.postId=post.getPostId();
        this.anonymous=post.getAnonymous();
        this.ownerName=post.getOwner().getNickname();
        this.content=post.getContent();
        this.createdDate=post.getCreatedDate();
        this.modifiedDate=post.getModifiedDate();
    }
}
