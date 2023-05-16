package efub.session.blog.account.controller;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.AccountCommentsResponseDto;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/{accountId}/comments")
public class AccountCommentController {
    private final CommentService commentService;
    private final AccountService accountService;

    // 특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountCommentsResponseDto readAccountComments(@PathVariable Long accountId) {
        Account writer = accountService.findAccountById(accountId);
        List<Comment> commentList = commentService.findCommentListByWriter(writer);
        return AccountCommentsResponseDto.of(writer.getNickname(), commentList);
    }
}
