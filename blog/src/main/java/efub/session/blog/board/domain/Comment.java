package efub.session.blog.board.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false)
    private Account writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CommentHeart> commentLikeList = new ArrayList<>();


    public void setPost(Post post) {
        if(this.post != null)
        {
            this.post.getCommentList().remove(this);

        }
        this.post = post;
        post.getCommentList().add(this);
    }

    @Builder
    public Comment(String content, Account writer, Post post) {
        this.content = content;
        this.writer = writer;
        this.post = post;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
