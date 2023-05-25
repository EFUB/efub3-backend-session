package com.efub.blogsession.domain.post.service;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.account.service.AccountService;
import com.efub.blogsession.domain.post.domain.Post;
import com.efub.blogsession.domain.post.dto.PostModifyRequestDto;
import com.efub.blogsession.domain.post.dto.PostRequestDto;
import com.efub.blogsession.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AccountService accountService;

    public Post addPost(PostRequestDto requestDto) {
        Account writer = accountService.findAccountById(requestDto.getAccountId());

        return postRepository.save(
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Post> findPostList() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public void removePost(Long postId, Long accountId) {
        Post post = postRepository.findByPostIdAndWriter_AccountId(postId, accountId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        postRepository.delete(post);
    }

    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndWriter_AccountId(postId, requestDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> findPostListByWriter(Long accountId){
        Account writer = accountService.findAccountById(accountId);
        return postRepository.findAllByWriter(writer);
    }
}