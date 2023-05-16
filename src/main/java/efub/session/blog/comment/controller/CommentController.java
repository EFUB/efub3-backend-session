package efub.session.blog.comment.controller;

import efub.session.blog.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor    // 생성자를 통한 의존관계 주입
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;    // 의존관계: CommentController -> CommentService

    // 특정 게시글의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostCommentsResponseDto readPostComments(@PathVariable Long postId) {
        List<Comment> commentList = commentService.findCommentListByPost(postId);
        return PostCommentsResponseDto.of(postId, commentList);
    }

    // 특정 게시글의 댓글 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createPostComment(@PathVariable Long postId, @RequestBody @Valid CommentRequestDto requestDto) {
        Long commentId = commentService.createComment(postId, requestDto);
        Comment comment = commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }

    // 댓글 수정

    // 댓글 삭제
}
