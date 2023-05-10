package efub.session.blog.post.controller;

import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.dto.CommentRequestDto;
import efub.session.blog.comment.dto.CommentResponseDto;
import efub.session.blog.comment.service.CommentService;
import efub.session.blog.post.dto.PostCommentsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
// url: /posts/{postId}/comments
public class PostCommentController {

    // 의존관계 : PostCommentController -> CommentService
    private final CommentService commentService;

    // 특정 게시글의 댓글 생성
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostCommentsResponseDto readPostComments(@PathVariable Long postId){
        List<Comment> commentList = commentService.findCommentListByPost(postId);
        return PostCommentsResponseDto.of(postId,commentList);
    }

    // 특정 게시글의 댓글 목록 조회
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createPostComment(@PathVariable Long postId,@RequestBody @Valid CommentRequestDto requestDto){
        Long commentId=commentService.createComment(postId,requestDto);
        Comment comment=commentService.findCommentById(commentId);
        return CommentResponseDto.of(comment);
    }


}