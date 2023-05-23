package efub.session.blog.heart.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.heart.domain.PostHeart;
import efub.session.blog.heart.repository.PostHeartRepository;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostHeartService {

    private final PostHeartRepository postHeartRepository;
    private final PostService postService;
    private final AccountService accountService;

    public void create(Long postId, Long accountId) {
        Account account = accountService.findAccountById(accountId);
        Post post = postService.findPost(postId);

        if (isExistsByWriterAndPost(account, post)) throw new RuntimeException("이미 좋아요를 누른 게시물입니다.");

        PostHeart postHeart = PostHeart.builder()
                .post(post)
                .writer(account)
                .build();

        postHeartRepository.save(postHeart);
    }

    public void delete(Long postId,Long accountId) {
        Post post = postService.findPost(postId);
        Account account = accountService.findAccountById(accountId);
        PostHeart postLike = postHeartRepository.findByWriterAndPost(account, post)
                .orElseThrow(() -> new RuntimeException("좋아요가 존재하지 않습니다."));
        postHeartRepository.delete(postLike);
    }

    public boolean isHeart(Long accountId, Post post) {
        Account account = accountService.findAccountById(accountId);
        return isExistsByWriterAndPost(account, post);
    }

    @Transactional(readOnly = true)
    private boolean isExistsByWriterAndPost(Account account, Post post) {
        return postHeartRepository.existsByWriterAndPost(account, post);
    }

    @Transactional(readOnly = true)
    public Integer countPostHeart(Post post) {
        return postHeartRepository.countByPost(post);
    }
}
