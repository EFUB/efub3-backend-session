package efub.session.blog.heart.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHeart {
    // 게시글 좋아요 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_heart_id")
    private Long id;

    // 게시글 - Post 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "게시글은 필수")
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    // 작성자 - writer 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수")
    @JoinColumn(name = "account_id", updatable = false)
    private Account writer;

    // Builder 패턴
    @Builder
    public PostHeart (Post post, Account writer) {
        this.post = post;
        this.writer = writer;
    }
}
