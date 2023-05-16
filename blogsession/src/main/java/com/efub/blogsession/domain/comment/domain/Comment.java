package com.efub.blogsession.domain.comment.domain;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.post.domain.Post;
import com.efub.blogsession.global.entity.BaseTimeEntity;
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


    @Builder
    public Comment(Long commentId, String content, Post post, Account writer) {
        this.commentId = commentId;
        this.content = content;
        this.post = post;
        this.writer = writer;
    }
}

