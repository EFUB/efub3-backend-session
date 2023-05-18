package efub.session.blog.board.controller;


import efub.session.blog.board.domain.Post;
import efub.session.blog.board.dto.request.PostModifyRequestDto;
import efub.session.blog.board.dto.request.PostRequestDto;
import efub.session.blog.board.dto.response.PostResponseDto;
import efub.session.blog.board.service.PostService;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto postRequestDto){
        Post post = postService.addPost(postRequestDto);
        return new PostResponseDto(post);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind(){
        List<Post> postList = postService.findPostList();
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId){
        Post post = postService.findPost(postId);
        return new PostResponseDto(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId){
        postService.removePost(postId, accountId);
        return "삭제 완료";
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId,@RequestBody PostModifyRequestDto requestDto){
        Post post = postService.modifyPost(postId, requestDto);
        return new PostResponseDto(post);
    }
}
