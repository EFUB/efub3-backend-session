package efub.session.blog.comment.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.post.domain.Post;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable=false, length=500)
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    //foreign key
    @JoinColumn(name = "post_id", nullable=false, updatable=false)
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY)
    //foreign key
    @JoinColumn(name = "account_id", nullable=false, updatable=false)
    private Account writer;

    //alt+insert
    @Builder
    public Comment(String content, Post post, Account writer){
        this.content=content;
        this.post=post;
        this.writer=writer;
    }
}
