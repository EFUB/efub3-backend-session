package efub.session.blog.domain.post.domain;

import efub.session.blog.domain.account.domain.Account;
<<<<<<< HEAD
import efub.session.blog.domain.comment.domain.Comment;
import efub.session.blog.domain.heart.domain.PostHeart;
import efub.session.blog.domain.post.dto.request.PostModifyRequestDto;
import efub.session.blog.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
=======
import efub.session.blog.domain.post.dto.PostModifyRequestDto;
import efub.session.blog.global.entity.BaseTimeEntity;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
=======

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    private Long postId;

    @Column
    private String title;

<<<<<<< HEAD
    @Column
=======
    @Column(columnDefinition = "TEXT")
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account writer;

<<<<<<< HEAD
    // mappedBy : 연관 관계의 주인(Owner)
    // cascade : 엔티티 삭제 시 연관된 엔티티의 처리 방식
    // orphanRemoval : 고아 객체(연관된 부모 엔티티가 없는 자식 엔티티)의 처리 방식 orphanRemoval = true -> 부모 엔티티가 삭제되거나 fk가 null로 바뀌면 삭제됨.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>(); //ctrl shit enter

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHeart> postHeartList = new ArrayList<>();

    @Builder
    public Post(Long postId, String title, String content, Account writer) {
=======
    @Builder
    public Post(Long postId, String title, String content, Account writer){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void updatePost(PostModifyRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
<<<<<<< HEAD

=======
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
}
