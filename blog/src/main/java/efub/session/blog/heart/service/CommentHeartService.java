package efub.session.blog.heart.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.request.AccountInfoRequestDto;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.service.CommentService;
import efub.session.blog.heart.domain.CommentHeart;
import efub.session.blog.heart.repository.CommentHeartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        if(isExistsByWriterAndComment(account, comment)){
            throw new RuntimeException("이미 좋아요 눌렀어");
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

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public boolean isExistsByWriterAndComment(Account account, Comment comment) {
        return commentHeartRepository.existsByWriterAndComment(account, comment);

    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Integer countCommentHeart(Comment comment) {
        return commentHeartRepository.countByComment(comment);
    }
}
