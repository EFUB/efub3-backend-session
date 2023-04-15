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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long postId;    // 자바에서는 postId, SQL에서는 post_id 라고 쓰는 게 일반적. 스프링은 두 형식 사이 매핑을 자동으로 해준다.

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")  // MySQL 상에서 TEXT로 선언되는 컬럼이라는 것을 표시
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id")    // Account 객체의 ID가 account_id라는 foreign key로 Post 테이블에 포함됨
    private Account writer; // 이 post의 작성자를 가리키는 컬럼이므로 이름을 writer로 설정

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
