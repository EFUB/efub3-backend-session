package efub.session.blog.post.dto.request;

import lombok.Getter;

@Getter
public class PostModifyRequestDto {
    private Long accountId;
    private String title;
    private String content;

}
