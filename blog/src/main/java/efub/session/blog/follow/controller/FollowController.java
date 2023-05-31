package efub.session.blog.follow.controller;


import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.follow.dto.FollowListResponseDto;
import efub.session.blog.follow.dto.FollowRequestDto;
import efub.session.blog.follow.dto.FollowStatusResponseDto;
import efub.session.blog.follow.entity.Follow;
import efub.session.blog.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService; // 의존관계: FollowController -> FollowService
    private final AccountService accountService; // 의존관계: FollowController -> AccountService

    // 팔로우 걸기
    @PostMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public FollowStatusResponseDto addFollow(@PathVariable Long accountId, @RequestBody final FollowRequestDto followRequestDto){
        Long id = followService.add(accountId, followRequestDto);
        Boolean isFollow = followService.isFollowing(accountId, followRequestDto.getFollowingId());
        Account findAccount = accountService.findAccountById(followRequestDto.getFollowingId());
        return new FollowStatusResponseDto(findAccount, isFollow);

    }
    // 팔로우 목록 조회
    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public FollowListResponseDto getFollowList(@PathVariable final Long accountId){
        List<Follow> followerList = followService.findAllByFollowerId(accountId);
        List<Follow> followingList = followService.findAllByFollowingId(accountId);
        return FollowListResponseDto.of(followerList, followingList);
    }

    // 팔로우 여부 조회
    @GetMapping("/{accountId}/search")
    @ResponseStatus(value = HttpStatus.OK)//이메일 유저 검색
    public FollowStatusResponseDto searchAccount(@PathVariable final Long accountId, @RequestParam final String email){
        Account searchAccount = accountService.findByEmail(email);
        Boolean isFollow = followService.isFollowing(accountId, searchAccount.getAccountId());
        return new FollowStatusResponseDto(searchAccount, isFollow);
    }

    // 팔로우 취소
    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public FollowStatusResponseDto deleteFollow(@PathVariable final Long accountId, @RequestParam Long followingId){
        followService.delete(accountId, followingId);
        Account foundAccount = accountService.findAccountById(followingId);
        Boolean isFollow = followService.isFollowing(accountId, followingId);
        return new FollowStatusResponseDto(foundAccount, isFollow);
    }
}
