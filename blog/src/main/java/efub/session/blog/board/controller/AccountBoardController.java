package efub.session.blog.board.controller;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;

import efub.session.blog.board.domain.Comment;
import efub.session.blog.board.domain.Post;

import efub.session.blog.board.dto.response.CommentListResponseDto;
import efub.session.blog.board.dto.response.PostListResponseDto;
import efub.session.blog.board.service.CommentService;
import efub.session.blog.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}")
@RequiredArgsConstructor
public class AccountBoardController {
    private final PostService postService;
    private final CommentService commentService;
    private final AccountService accountService;

    @GetMapping("/posts")//작성자 별 글 조회
    @ResponseStatus(value = HttpStatus.OK)
    public PostListResponseDto readPostList(@PathVariable final Long accountId) {
        List<Post> postList = postService.findByWriter(accountId);
        return PostListResponseDto.of(postList);
    }
    @GetMapping("/comments")//작성자 별 댓글 조회
    @ResponseStatus(value = HttpStatus.OK)
    public CommentListResponseDto.Account readCommentsList(@PathVariable final Long accountId) {
        Account account = accountService.findAccountById(accountId);
        List<Comment> commentList = commentService.findByWriter(account);
        return CommentListResponseDto.Account.of(account.getNickname(), commentList);
    }
}
