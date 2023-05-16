package efub.session.blog.comment.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.post.domain.Post;
import efub.session.blog.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // 객체를 테이블과 매핑
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자
public class Comment extends BaseTimeEntity {

    @Id // PK 컬럼 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 연관관계 매핑, Lazy: 지연로딩 방식으로 연관된 엔티티는 가져오지 않고 프록시 객체를 넣어둠. 실무에서 주로 사용
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account writer;

    @Builder // 매개변수의 의미 명확
    public Comment(String content, Post post, Account writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    // @Builder
}
