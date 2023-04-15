package efub.session.blog.post.controller;

import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.PostModifyRequestDto;
import efub.session.blog.post.dto.PostRequestDto;
import efub.session.blog.post.dto.PostResponseDto;
import efub.session.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor    // final로 선언된 필드들 (Service) 을 외부에서 받아오는 생성자를 자동생성하는 lombok 어노테이션
public class PostController {

    private final PostService postService;

    // 새 게시글 추가
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto) {
        Post post = postService.addPost(requestDto);    // service를 사용해 저장소에 게시글을 추가하고, 추가된 게시글 데이터를 Post 객체로 받아옴
        return new PostResponseDto(post);
    }

    // 모든 게시글을 담은 리스트 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind () {
        List<Post> postList = postService.findPostList();
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for(Post post : postList) { // stream을 사용하면 이 for문을 한 줄로 바꿀 수 있다... 과제할 때 한 번 해보셈
            responseDtoList.add(new PostResponseDto(post));
        }
        return responseDtoList;
    }

    // 하나의 게시글을 id를 기준으로 조회
    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        return new PostResponseDto(post);
    }

    @DeleteMapping("/{postId}") // RequestParam의 대상 변수는 맵핑하는 url에 적지 않음
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId) {
        postService.removePost(postId,accountId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto) {
        Post post = postService.modifyPost(postId, requestDto);
        return new PostResponseDto(post);
    }

}
