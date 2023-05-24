package efub.session.blog.comment.domain;

import efub.session.blog.domain.account.domain.Account;
import efub.session.blog.domain.post.domain.Post;
import efub.session.blog.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ==auto increment
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account writer;

    // 2. comment domain에 엔티티 매핑
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CommentHeart> commentLikeList = new ArrayList<>();

    @Builder
    public Comment(String content, Post post, Account writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    // update 메소드 추가
    public void updateComment(String content) {
        this.content = content;
    }

}
