package efub.session.blog.heart.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "댓글은 필수")
    @JoinColumn(name = "comment_id", updatable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수")
    @JoinColumn(name = "account_id", updatable = false)
    private Account writer;

    @Builder
    public CommentHeart(Comment comment, Account account) {
        this.comment = comment;
        this.writer = writer;
    }
}
