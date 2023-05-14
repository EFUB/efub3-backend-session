package com.efub.blogsession.domain.post.controller;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.account.dto.AccountCommentsResponseDto;
import com.efub.blogsession.domain.account.service.AccountService;
import com.efub.blogsession.domain.comment.dto.CommentRequestDto;
import com.efub.blogsession.domain.comment.dto.CommentResponseDto;
import com.efub.blogsession.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
// url: /posts/{postId}/comments
public class PostCommentController {

    // 의존관계 : PostCommentController -> CommentService
    private final CommentService commentService;

    private final AccountService accountService;
    // 특정 게시글의 댓글 생성
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public CommentResponseDto createPostComment(@PathVariable Long postId, @RequestBody @Valid CommentRequestDto requestDto){
        Long commentId = commentService.createComment(postId,requestDto);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }
    // 특정 게시글의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountCommentsResponseDto readAccountComments(@PathVariable Long accountId){
        Account writer=accountService.findAccountById(accountId);
        List<Comment> commentList = commentService.findCommentListByWriter(writer);
        return AccountCommentsResponseDto.of(writer.getNickname(),commentList);
    }


}