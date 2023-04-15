package efub.session.blog.post.dto;

import lombok.Getter;

@Getter //requestdto에서 대부분 들어가야함
public class PostModifyRequestDto {
    private Long accountId;
    private String title;
    private String content;
}
