package efub.session.blog.follow.controller;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.follow.domain.Follow;
import efub.session.blog.follow.dto.FollowListResponseDto;
import efub.session.blog.follow.dto.FollowRequestDto;
import efub.session.blog.follow.dto.FollowStatusResponseDto;
import efub.session.blog.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private AccountService accountService;

    // 팔로우 관계 하나 추가
    @PostMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public FollowStatusResponseDto addFollow(@PathVariable final Long accountId, @RequestBody final FollowRequestDto requestDto) {
        Long id = followService.add(accountId, requestDto);
        Boolean isFollow = followService.isFollowing(accountId, requestDto.getFollowingId());
        Account findAccount = accountService.findAccountById(requestDto.getFollowingId());
        return new FollowStatusResponseDto(findAccount, isFollow);
    }

    // 특정 계정의 팔로잉과 팔로우 리스트를 리턴
    @GetMapping("/{accountId")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowListResponseDto getFollowList(@PathVariable final Long accountId) {
        List<Follow> followerList = followService.findAllByFollowingId(accountId);
        List<Follow> followingList = followService.findAllByFollowerId(accountId);
        return FollowListResponseDto.of(followerList, followingList);
    }

    // 이메일로 계정 하나를 탐색해서, 그 계정의 팔로우 여부를 리턴
    @GetMapping("/{accountId}/search")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowStatusResponseDto searchAccount(@PathVariable final Long accountId, @RequestParam final String email) {
        Account searchAccount = accountService.findAccountByEmail(email);
        Boolean isFollow = followService.isFollowing(accountId, searchAccount.getAccountId());
        return new FollowStatusResponseDto(searchAccount, isFollow);
    }

    // 팔로우 취소
    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowStatusResponseDto deleteFollow(@PathVariable final Long accountId, @RequestParam final Long followingId) {
        followService.delete(accountId, followingId);
        Account findAccount = accountService.findAccountById(followingId);
        Boolean isFollow = followService.isFollowing(accountId, followingId);
        return new FollowStatusResponseDto(findAccount, isFollow);
    }
}
