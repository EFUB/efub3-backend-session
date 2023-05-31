package efub.session.blog.comment.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.global.entity.BaseTimeEntity;
import efub.session.blog.post.domain.Post;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가. auto
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false) // post_id를 foreign키를 씀
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account writer;

    // @Builder alt+insert 단축키->constructor
    @Builder
    public Comment(String content, Post post, Account writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    public void updateComment(String content){
        this.content = content;
    }
}
