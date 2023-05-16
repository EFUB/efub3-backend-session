package efub.session.blog.post.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.post.dto.request.PostModifyRequestDto;
import efub.session.blog.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // mysql에서는 post_id

    @Column
    private String title;

    @Column(columnDefinition = "TEXT") // mysql의 변수명
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id") // account_id가 foreign key로 들어간 것
    private Account writer; // 여기서의 account는 작성자니까 account보다는 writer로 이름 설정

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(Long postId, String title, String content, Account writer) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void updatePost(PostModifyRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
