package efub.session.blog.domain.comment.controller;

import efub.session.blog.domain.account.dto.AccountInfoRequestDto;
import efub.session.blog.domain.comment.Comment;
import efub.session.blog.domain.comment.dto.CommentRequestDto;
import efub.session.blog.domain.comment.dto.CommentResponseDto;
import efub.session.blog.domain.comment.service.CommentHeartService;
import efub.session.blog.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;    // 의존관계: CommentController -> CommentService

    private final CommentHeartService commentHeartService;
    // 댓글 수정
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updatePostComment(@PathVariable final Long commentId, @RequestBody @Valid final CommentRequestDto requestDto){
        commentService.updateComment(requestDto,commentId);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }

    // 댓글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable final Long commentId){
        commentService.deleteComment(commentId);
        return "성공적으로 삭제되었습니다.";
    }
    //여기 Id 무슨 수정 있는디 이건 나중에 다시 고치기,,,
    @PostMapping("/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentHeart(@PathVariable final Long commentId, @RequestBody final AccountInfoRequestDto requestDto){
        commentHeartService.create(commentId,requestDto);
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCommentHeart(@PathVariable final Long commentId, @RequestParam final Long accountId){
        commentHeartService.delete(commentId,accountId);
        return "좋아요가 취소되었습니다.";
    }

}