package efub.session.blog.post.controller;

import efub.session.blog.heart.dto.HeartRequestDto;
import efub.session.blog.heart.service.PostHeartService;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.PostModifyRequestDto;
import efub.session.blog.post.dto.PostRequestDto;
import efub.session.blog.post.dto.PostResponseDto;
import efub.session.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostHeartService postHeartService;

    // 게시글 작성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto) {   // request가 JSON으로 들어간다는 의미
        Post post = postService.addPost(requestDto);
        return PostResponseDto.from(post);
    }

    // 게시글 목록 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind() {
        List<Post> postList = postService.findPostList();
        return postList.stream().map(PostResponseDto::from).collect(Collectors.toList());
    }

    // 게시글 조회
    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        return PostResponseDto.from(post);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto) {
        Post post = postService.modifyPost(postId, requestDto);
        return PostResponseDto.from(post);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @PathVariable Long accountId) {
        postService.removePost(postId, accountId);
        return "성공적으로 삭제되었습니다.";
    }

    // 게시글 좋아요
    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto) {
        postHeartService.create(postId, requestDto.getAccountId());
        return "좋아요를 눌렀습니다.";
    }
    
    // 게시글 좋아요 취소
    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final Long accountId) {
        postHeartService.delete(postId, accountId);
        return "좋아요가 취소되었습니다.";
    }
}
