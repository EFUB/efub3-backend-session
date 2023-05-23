package efub.session.blog.domain.post.controller;

<<<<<<< HEAD
import efub.session.blog.domain.heart.dto.HeartRequestDto;
import efub.session.blog.domain.heart.service.PostHeartService;
import efub.session.blog.domain.post.domain.Post;
import efub.session.blog.domain.post.dto.request.PostModifyRequestDto;
import efub.session.blog.domain.post.dto.request.PostRequestDto;
import efub.session.blog.domain.post.dto.response.PostResponseDto;
=======
import efub.session.blog.domain.post.domain.Post;
import efub.session.blog.domain.post.dto.PostModifyRequestDto;
import efub.session.blog.domain.post.dto.PostRequestDto;
import efub.session.blog.domain.post.dto.PostResponseDto;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
import efub.session.blog.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;
import java.util.stream.Collectors;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
<<<<<<< HEAD

    private final PostService postService;
    private final PostHeartService postHeartService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto postRequestDto) {
        Post post = postService.addPost(postRequestDto);
        return PostResponseDto.from(post);
=======
    private final PostService postService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto){
        Post post = postService.addPost(requestDto);
        return new PostResponseDto(post);
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostResponseDto> postListFind() {
        List<Post> postList = postService.findPostList();
<<<<<<< HEAD
        return postList.stream().map(PostResponseDto::from).collect(Collectors.toList());
=======
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        //TODO: stream으로
        for(Post post : postList) {
            responseDtoList.add(new PostResponseDto(post));
        }

        return responseDtoList;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
<<<<<<< HEAD
    public PostResponseDto postFind(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        return PostResponseDto.from(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId) {
        postService.removePost(postId, accountId);
        return "성공적으로 삭제가 완료되었습니다.";
=======
    public PostResponseDto postFind(@PathVariable Long postId){
        Post post = postService.findPost(postId);
        return new PostResponseDto(post);
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
<<<<<<< HEAD
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto) {
        Post post = postService.modifyPost(postId, requestDto);
        return PostResponseDto.from(post);
    }

    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto){
        postHeartService.create(postId, requestDto.getAccountId());
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value = HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final Long accountId){
        postHeartService.delete(postId, accountId);
        return "좋아요가 취소되었습니다.";
    }
=======
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto){
        Post post = postService.modifyPost(postId, requestDto);
        return new PostResponseDto(post);
    }

    @DeleteMapping("/{postId}?accountId={accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId){
        postService.removePost(postId, accountId);
        return "성공적으로 삭제되었습니다.";
    }

>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
}
