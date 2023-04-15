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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 카멜식 표기, mysql에서는 post_id

    @Column
    private String title;

    @Column(columnDefinition = "TEXT") // string이 아니라 text임을 명시
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account writer; // post의 글 작성자이므로 변수명을 writer로

    @Builder
    public Post(Long postId, String title, String content, Account writer) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void updatePost(PostModifyRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
