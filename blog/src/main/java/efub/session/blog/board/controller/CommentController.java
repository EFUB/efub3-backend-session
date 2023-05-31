package efub.session.blog.board.controller;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.board.domain.Comment;
import efub.session.blog.board.dto.request.AccountInfoRequestDto;
import efub.session.blog.board.dto.request.CommentRequestDto;
import efub.session.blog.board.dto.response.CommentResponseDto;
import efub.session.blog.board.service.CommentHeartService;
import efub.session.blog.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final AccountService accountService;
    private final CommentHeartService commentHeartService;


    @PutMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto updateComment(@PathVariable final Long commentId, @RequestBody @Valid final CommentRequestDto requestDto) {
        commentService.update(requestDto, commentId);
        Comment comment = commentService.findById(commentId);
        Account account = accountService.findAccountById(requestDto.getAccountId());
        Integer heartCount = commentHeartService.countCommentHeart(comment);
        boolean isHeart = commentHeartService.isExistsByWriterAndComment(account, comment);

        CommentResponseDto responseDto = CommentResponseDto.of(comment);
        responseDto.uploadHeart(heartCount, isHeart);
        return responseDto;
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteComment(@PathVariable final Long commentId, @RequestParam final Long accountId) {
        commentService.delete(commentId, accountId);
        return "성공적으로 삭제되었습니다.";
    }

    @PostMapping("/{commentId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentLike(@PathVariable final Long commentId, @RequestBody final AccountInfoRequestDto requestDto) {
        commentHeartService.create(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("/{commentId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentLike(@PathVariable final Long commentId, @RequestParam final Long accountId) {
        commentHeartService.delete(commentId, accountId);
        return "좋아요가 취소되었습니다.";
    }

}
