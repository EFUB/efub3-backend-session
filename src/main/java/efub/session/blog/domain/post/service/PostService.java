package efub.session.blog.domain.post.service;

import efub.session.blog.domain.account.domain.Account;
<<<<<<< HEAD
import efub.session.blog.domain.account.service.AccountService;
import efub.session.blog.domain.post.domain.Post;
import efub.session.blog.domain.post.dto.request.PostModifyRequestDto;
import efub.session.blog.domain.post.dto.request.PostRequestDto;
=======
import efub.session.blog.domain.account.repositoy.AccountRepository;
import efub.session.blog.domain.post.domain.Post;
import efub.session.blog.domain.post.dto.PostModifyRequestDto;
import efub.session.blog.domain.post.dto.PostRequestDto;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
import efub.session.blog.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

<<<<<<< HEAD
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AccountService accountService;

    public Post addPost(PostRequestDto requestDto) {
        Account writer = accountService.findAccountById(requestDto.getAccountId());
=======

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Post addPost(PostRequestDto requestDto) {
        Account writer = accountRepository.findById(requestDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49

        return postRepository.save(
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .build()
        );
    }

<<<<<<< HEAD
    @Transactional(readOnly = true)
=======
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    public List<Post> findPostList() {
        return postRepository.findAll();
    }

<<<<<<< HEAD
    @Transactional(readOnly = true)
=======
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
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
<<<<<<< HEAD

    public List<Post> findPostListByWriter(Long accountId) { //ctrl alt 방향키 이전 커서로 이동
        Account account = accountService.findAccountById(accountId);
        return postRepository.findAllByWriter(account);
    }
=======
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
}
