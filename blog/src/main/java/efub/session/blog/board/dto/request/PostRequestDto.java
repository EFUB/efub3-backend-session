package efub.session.blog.board.dto.request;


import efub.session.blog.account.domain.Account;
import efub.session.blog.board.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    private Long accountId;
    @NotBlank(message = "제목은 필수로 입력되어야 합니다.")
    private String title;

    @NotNull(message = "내용은 필수로 입력되어야 합니다.")
    private String content;

    @Builder
    public PostRequestDto(Long accountId, String title, String content) {
        this.accountId = accountId;
        this.title = title;
        this.content = content;
    }

    public Post toEntity(Account writer) {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}

