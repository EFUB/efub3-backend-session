package efub.session.blog.domain.follow.dto;

import efub.session.blog.domain.follow.domain.Follow;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowListResponseDto {
    private List<FollowListResponseDto.SingleFollower> followerList;
    private List<FollowListResponseDto.SingleFollowing> followingList;
    private Integer followerCount;
    private Integer followingCount;

    @Getter
    public static class SingleFollower{
        private Long followId;
        private String followerNickname;
        private String email;

        public SingleFollower(Follow follow){
            this.followId=follow.getFollowId();
            this.followerNickname=follow.getFollower().getNickname();
            this.email=follow.getFollower().getEmail();
        }

        public static FollowListResponseDto.SingleFollower of(Follow follow){
            return new FollowListResponseDto.SingleFollower(follow);
        }
    }

    @Getter
    public static class SingleFollowing{
        private Long followId;
        private String followingNickname;
        private String email;

        public SingleFollowing(Follow follow){
            this.followId=follow.getFollowId();
            this.followingNickname=follow.getFollowing().getNickname();
            this.email=follow.getFollowing().getEmail();
        }

        public static FollowListResponseDto.SingleFollowing of(Follow follow){
            return new FollowListResponseDto.SingleFollowing(follow);
        }
    }



    public static FollowListResponseDto of(List<Follow> followerList, List<Follow> followingList) {
        return FollowListResponseDto.builder()
                .followerList(followerList.stream().map(SingleFollower::of).collect(Collectors.toList()))
                .followingList(followingList.stream().map(SingleFollowing::of).collect(Collectors.toList()))
                .followerCount(followerList.size())
                .followingCount(followingList.size())
                .build();
    }
}
