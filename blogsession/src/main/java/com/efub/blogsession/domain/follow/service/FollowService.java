package com.efub.blogsession.domain.follow.service;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.account.service.AccountService;
import com.efub.blogsession.domain.follow.domain.Follow;
import com.efub.blogsession.domain.follow.dto.FollowRequestDto;
import com.efub.blogsession.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 자동으로 생성
public class FollowService {
    // 의존관계 : FollowService->FollowRepository
    private final FollowRepository followRepository;
    // 의존관계 : FollowService->AccountService
    public final AccountService accountService;

    // 팔로우 추가
    public Long add(Long accountId, FollowRequestDto followRequestDto){
        Account follower=accountService.findAccountById(accountId);
        Account following=accountService.findAccountById(followRequestDto.getFollowingId());
        Follow follow=followRepository.save(followRequestDto.toEntity(follower, following));
        return follow.getFollowId();
    }
    // 팔로우 여부
    @Transactional(readOnly = true)
    public boolean isFollowing(Long followerId, Long followingId){
        Account follower = accountService.findAccountById(followerId);
        Account following = accountService.findAccountById(followingId);
        return followRepository.existsByFollowerAndFollowing(follower, following);
    }
    // 팔로워 목록
    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowerId(Long accountId){
        Account findAccount = accountService.findAccountById(accountId);
        return followRepository.findAllByFollower(findAccount);
    }
    // 팔로잉 목록
    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowingId(Long accountId){
        Account findAccount = accountService.findAccountById(accountId);
        return followRepository.findAllByFollowing(findAccount);
    }

    // 팔로우 취소
    @Transactional(readOnly = true)
    public Follow findByFollowerAndFollowingId(Long followerId, Long followingId){
        Account follower = accountService.findAccountById(followerId);
        Account following = accountService.findAccountById(followingId);
        return followRepository.findByFollowerAndFollowing(follower, following);
    }
    public void delete(Long accountId, Long followingId){
        Follow findFollow = findByFollowerAndFollowingId(accountId, followingId);
        followRepository.deleteByFollowId(findFollow.getFollowId());
    }
}
