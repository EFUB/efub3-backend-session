package efub.session.blog.comment.controller;

import efub.session.blog.account.dto.request.AccountInfoRequestDto;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.dto.CommentRequestDto;
import efub.session.blog.comment.dto.CommentResponseDto;
import efub.session.blog.comment.service.CommentService;
import efub.session.blog.heart.service.CommentHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;    // 의존관계: CommentController -> CommentService
    private final CommentHeartService commentHeartService;

    //댓글 수정
    @PutMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto updatePostComment(@PathVariable final Long commentId, @RequestBody @Valid final CommentRequestDto requestDto){
        commentService.updateComment(requestDto, commentId);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable final Long commentId){
        commentService.deleteComment(commentId);
        return "성공적으로 삭제되었습니다.";
    }

    // 좋아요 수정
    @PostMapping("/hearts/{heartId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentHeart(@PathVariable final Long heartId, @RequestBody final AccountInfoRequestDto requestDto){
        return "좋아요를 눌렀습니다.";
    }

    // 좋아요 삭제
    @DeleteMapping("/hearts/{heartId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentHeart(@PathVariable final Long heartId, @RequestBody final Long accountId){
        commentHeartService.delete(heartId, accountId);
        return "좋아요가 취소되었습니다.";
    }
}
