package com.efub.blogsession.domain.account.controller;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.account.dto.AccountCommentsResponseDto;
import com.efub.blogsession.domain.account.service.AccountService;
import com.efub.blogsession.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/{accountId}/comments")
// url: /accounts/{accountId}/comments
public class AccountCommentController {
    // 의존관계 : AccountCommentController -> CommentService
    private final CommentService commentService;
    // 의존관계 : AccountCommentController -> AccountService
    private final AccountService accountService;

    // 특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountCommentsResponseDto readAccountComments(@PathVariable Long accountId){
        Account writer = accountService.findAccountById(accountId);
        List<Comment> commentList = commentService.findCommentListByWriter(writer);
        return AccountCommentsResponseDto.of(writer.getNickname(),commentList);
    }
}