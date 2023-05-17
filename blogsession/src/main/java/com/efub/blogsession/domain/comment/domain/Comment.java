package com.efub.blogsession.domain.comment.domain;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.post.domain.Post;
import com.efub.blogsession.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincreament
    private Long commentId;

    @Column(nullable = false,length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable = false,updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id",nullable = false,updatable = false)
    private Account writer;

    //2.comment domain에 엔티티 매핑
    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL,orphanRemoval = true)
    List<CommentHeart> commentHeartList=new ArrayList<>();

    @Builder
    public Comment(Long commentId, String content, Post post, Account writer) {
        this.commentId = commentId;
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    //댓글 수정
    public void updateComment(String content){
        this.content = content;
    }
}

