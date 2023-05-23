package efub.session.blog.domain.heart.domain;

import efub.session.blog.domain.account.domain.Account;
import efub.session.blog.domain.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// 1. entity 매핑
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentHeart {
    // 댓글 좋아요 id 작성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    // 댓글 - Comment
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "댓글은 필수로 입력되어야 합니다.")
    @JoinColumn(name = "comment_id", updatable = false)
    private Comment comment;

    // 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    @JoinColumn(name = "account_id", updatable = false)
    private Account writer;

    @Builder
    public CommentHeart(Comment comment, Account account){
        this.comment = comment;
        this.writer = account;
    }

}
