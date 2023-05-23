package efub.session.blog.heart.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.AccountInfoRequestDto;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.service.CommentService;
import efub.session.blog.heart.domain.CommentHeart;
import efub.session.blog.heart.repository.CommentHeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 4. 의존관계 주입
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentHeartService {
    private final CommentService commentService;
    private final CommentHeartRepository commentHeartRepository;
    private final AccountService accountService;

    public void create(Long commentId, AccountInfoRequestDto requestDto) {
        Account account = accountService.findAccountById(requestDto.getAccountId());
        Comment comment = commentService.findCommentById(commentId);

        // 한 유저가 한 게시물에 대해 한 번만 "좋아요"를 누를 수 있도록 제한하는 기능 추가
        if (isExistsByWriterAndComment(account, comment)) {
            throw new RuntimeException("이미 좋아요를 눌렀습니다.");
        }

        CommentHeart commentHeart = CommentHeart.builder()
                .comment(comment)
                .account(account)
                .build();

        commentHeartRepository.save(commentHeart);
    }

    public void delete(Long commentId, Long accountId) {
        Account account = accountService.findAccountById(accountId);
        Comment comment = commentService.findCommentById(commentId);

        CommentHeart commentHeart = commentHeartRepository.findByWriterAndComment(account, comment)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다."));

        commentHeartRepository.delete(commentHeart);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByWriterAndComment(Account account, Comment comment) {
        return commentHeartRepository.existsByWriterAndComment(account, comment);
    }

    @Transactional(readOnly = true)
    public Integer countCommentHeart(Comment comment) {
        Integer count = commentHeartRepository.countByComment(comment);
        return count;
    }
}
