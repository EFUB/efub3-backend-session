package efub.session.blog.board.controller;

import efub.session.blog.board.domain.Comment;
import efub.session.blog.board.dto.request.CommentRequestDto;
import efub.session.blog.board.dto.response.CommentListResponseDto;
import efub.session.blog.board.dto.response.CommentResponseDto;
import efub.session.blog.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class PostCommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentResponseDto createComment(@PathVariable final Long postId,
                                            @RequestBody @Valid final CommentRequestDto requestDto
    ) {

        Long commentId = commentService.create(requestDto, postId);
        Comment comment = commentService.findById(commentId);
        return CommentResponseDto.of(comment);
    }


    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CommentListResponseDto.Post readCommentList(@PathVariable final Long postId) {

        List<Comment> commentList = commentService.findByPost(postId);
        return CommentListResponseDto.Post.of(postId, commentList);
    }
}