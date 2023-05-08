package efub.session.blog.post.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.global.entity.BaseTimeEntity;
import efub.session.blog.post.dto.PostModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account writer;
    @Builder
    public Post(Long postId, String title, String content, Account writer){
        this.postId=postId;
        this.title=title;
        this.content=content;
        this.writer=writer;
    }

    public void updatePost(PostModifyRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.content=requestDto.getContent();
    }
}
