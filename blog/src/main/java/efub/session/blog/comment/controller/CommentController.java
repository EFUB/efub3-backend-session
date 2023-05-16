package efub.session.blog.comment.controller;

import efub.session.blog.account.dto.AccountInfoRequestDto;
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
    private final CommentService commentHeartService;

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
        Long id = commentService.updateComment(commentId, requestDto);
        Comment comment = commentService.findCommentById(id);
        return CommentResponseDto.of(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable Long commentId, @RequestParam Long memberId) {
        commentService.deleteComment(commentId, memberId);
        return "좋아요가 취소되었습니다.";
    }
    @PostMapping("/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createCommentHeart(@PathVariable final Long commentId, @RequestBody final AccountInfoRequestDto requestDto){
        commentHeartService.create(commentId, requestDto);
        return "좋아요를 눌렀습니다.";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updatePostComment(@PathVariable final Long commentId, @RequestBody @Valid final CommentResponseDto requestDto){
        commentService.updateComment(requestDto, commentId);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable final Long commentId){
        commentService.deleteComment(commentId);
        return "성공적으로 삭제 되었습니다.";
    }
}
