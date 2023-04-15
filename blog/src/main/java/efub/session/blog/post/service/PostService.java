package efub.session.blog.post.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.repository.AccountRepository;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.PostModifyRequestDto;
import efub.session.blog.post.dto.PostRequestDto;
import efub.session.blog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service // 레포지토리만 봄
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Post addPost(PostRequestDto requestDto) {
        Account writer = accountRepository.findById(requestDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));// Post를 넘겨받는 dto가 request 파라미터
        return postRepository.save( // mysql에서는 insert
                Post.builder()
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .writer(writer)
                        .build()
        );
    }

    public List<Post> findPostList() {
        return postRepository.findAll();
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public void removePost(Long postId, Long accountId) { // 리턴값 없음
        Post post = postRepository.findByPostIdAndWriter_AccountId(postId, accountId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        // 메소드를 구체화 시켜주는 방법, postId, accountId에 따라서 접근이 잘못된 것에 대해 알려줄 수 있음
        postRepository.delete(post); // 아무것도 조회해주지 않기에 굳이 리턴할 필요 없음
    }

    public Post modifyPost(Long postId, PostModifyRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndWriter_AccountId(postId, requestDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        post.updatePost(requestDto);
        return post;
    }
}
