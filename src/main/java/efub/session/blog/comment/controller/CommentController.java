package efub.session.blog.comment.controller;

import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.dto.CommentModifyRequestDto;
import efub.session.blog.comment.dto.CommentResponseDto;
import efub.session.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;    // 의존관계: CommentController -> CommentService

    // ID를 기준으로 댓글 조회
    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto getComment(@PathVariable Long commentId) {
        Comment findComment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(findComment);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentResponseDto updateComment(@PathVariable final Long commentId, @RequestBody @Valid final CommentModifyRequestDto requestDto){
        Long id = commentService.update(commentId, requestDto);
        Comment comment = commentService.findCommentById(id);
        return CommentResponseDto.of(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable Long commentId, @RequestParam Long memberId) {
        commentService.deleteComment(commentId, memberId);
        return "성공적으로 삭제가 완료되었습니다.";
    }
}
