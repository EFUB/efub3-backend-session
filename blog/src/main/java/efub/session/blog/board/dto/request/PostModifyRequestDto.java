package efub.session.blog.board.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostModifyRequestDto {
    private Long accountId;
    private String title;
    private String content;
}
