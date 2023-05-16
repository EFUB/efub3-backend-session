package efub.session.blog.post.controller;

import efub.session.blog.heart.service.PostHeartService;
import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.PostModifyRequestDto;
import efub.session.blog.post.dto.PostRequestDto;
import efub.session.blog.post.dto.PostResponseDto;
import efub.session.blog.post.service.PostService;
import efub.session.blog.heart.dto.HeartRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostHeartService postHeartService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostResponseDto postAdd(@RequestBody PostRequestDto requestDto){
        Post post = postService.addPost(requestDto);
        return PostResponseDto.from(post);
    }

    @GetMapping
    @ResponseStatus(value=HttpStatus.OK)
    public List<PostResponseDto> postListFind(){
        List<Post> postList=postService.findPostList();
        List<PostResponseDto> responseDtoList=new ArrayList<>();
        //stream 사용 가능함
        for(Post post: postList){
            responseDtoList.add(PostResponseDto.from(post));
        }
        return responseDtoList;
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value=HttpStatus.OK)
    public PostResponseDto postFind(@PathVariable Long postId){
        Post post=postService.findPost(postId);
        return PostResponseDto.from(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(value=HttpStatus.OK)
    public String postRemove(@PathVariable Long postId, @RequestParam Long accountId){
        postService.removePost(postId, accountId);

        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{postId}")
    @ResponseStatus(value=HttpStatus.OK)
    public PostResponseDto postModify(@PathVariable Long postId, @RequestBody PostModifyRequestDto requestDto){
        Post post=postService.modifyPost(postId, requestDto);
        return PostResponseDto.from(post);
    }

    @PostMapping("/{postId}/hearts")
    @ResponseStatus(value=HttpStatus.CREATED)
    public String createPostHeart(@PathVariable final Long postId, @RequestBody final HeartRequestDto requestDto){
        postHeartService.create(postId, requestDto.getAccountId());
        return "좋아요를 눌렀습니다.";
    }

    @DeleteMapping("/{postId}/hearts")
    @ResponseStatus(value=HttpStatus.OK)
    public String deletePostHeart(@PathVariable final Long postId, @RequestParam final Long accountId){
        postHeartService.delete(postId, accountId);
        return "좋아요가 취소되었습니다.";
    }

}
