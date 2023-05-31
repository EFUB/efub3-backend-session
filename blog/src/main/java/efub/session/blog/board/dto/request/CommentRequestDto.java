package efub.session.blog.board.dto.request;

import efub.session.blog.account.domain.Account;
import efub.session.blog.board.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    private Long accountId;
    @NotNull(message = "내용은 필수로 입력되어야 합니다.")// 공백까지는 허용
    private String content;

    @Builder
    public CommentRequestDto(Long accountId, String content) {
        this.accountId = accountId;
        this.content = content;
    }

    public Comment toEntity(Account account)
    {
        return Comment.builder()
                .content(this.content)
                .writer(account)
                .build();

    }
}