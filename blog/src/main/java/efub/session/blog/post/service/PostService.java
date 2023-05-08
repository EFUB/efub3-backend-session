package efub.session.blog.post.service;


import efub.session.blog.account.domain.Account;
import efub.session.blog.account.exception.NotExistAccountException;
import efub.session.blog.account.repository.AccountRepository;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.PostModifyRequestDto;
import efub.session.blog.post.dto.PostRequestDto;
import efub.session.blog.post.exception.NotExistPostException;
import efub.session.blog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Post addPost(PostRequestDto postRequestDto) {
        Account writer = accountRepository.findById(postRequestDto.getAccountId())
                .orElseThrow(NotExistAccountException::new);
        return postRepository.save(
                Post.builder()
                        .title(postRequestDto.getTitle())
                        .content(postRequestDto.getContent())
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
        return postRepository.findById(postId).orElseThrow(NotExistPostException::new);
    }

    @Transactional
    public void removePost(Long postId, Long accountId) {
        Post post = postRepository.findByPostIdAndWriter_AccountId(postId, accountId)
                .orElseThrow(NotExistPostException::new);
        postRepository.delete(post);

    }

    @Transactional
    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndWriter_AccountId(postId, requestDto.getAccountId())
                .orElseThrow(NotExistPostException::new);
        post.updatePost(requestDto);

        return post;

    }
}
