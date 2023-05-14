package com.efub.blogsession.domain.account.controller;

import com.efub.blogsession.domain.post.domain.Post;
import com.efub.blogsession.domain.post.dto.PostListResponseDto;
import com.efub.blogsession.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/accounts/{accountId}/posts")
@RestController
@RequiredArgsConstructor
public class AccountPostController {

    // 의존관계 : AccountCommentController -> PostService
    // 의존관계 : AccountCommentController -> AccountService
    private final PostService postService;
    // 특정 유저의 게시글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListResponseDto readAccountPosts(@PathVariable Long accountId) {
        List<Post> postList = postService.findPostListByWriter(accountId);
        return PostListResponseDto.of(postList);
    }
}