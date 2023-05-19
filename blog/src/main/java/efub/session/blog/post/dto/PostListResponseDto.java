package efub.session.blog.post.dto;

import efub.session.blog.post.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PostListResponseDto {
    private List<PostResponseDto> posts;
    private Integer count;

    public static PostListResponseDto of(List<Post> postList) {
        return PostListResponseDto.builder()
                .posts(new ArrayList<PostResponseDto>())
                .count(postList.size())
                .build();
    }
}
