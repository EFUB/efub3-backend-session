package com.efub.blogsession.domain.comment.dto;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.stream.events.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 애노테이션 추가하기!
public class CommentRequestDto {

    @NotNull(message = "작성자 ID를 입력해주세요.")
    private Long accountId;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    public Comment toEntity(Post post, Account writer) {
        return Comment.builder()
                .post(post)
                .writer(writer)
                .content(this.content)
                .build();
    }
}
