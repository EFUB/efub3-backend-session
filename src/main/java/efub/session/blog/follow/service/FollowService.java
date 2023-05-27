package efub.session.blog.follow.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.follow.domain.Follow;
import efub.session.blog.follow.dto.FollowRequestDto;
import efub.session.blog.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final AccountService accountService;

    // 팔로우 관계 추가
    public Long add(Long accountId, FollowRequestDto requestDto){
        Account follower = accountService.findAccountById(accountId);
        Account following = accountService.findAccountById(requestDto.getFollowingId());
        Follow follow = followRepository.save(requestDto.toEntity(follower, following));
        return follow.getFollowId();
    }

    // 팔로우 취소
    public void delete(Long accountId, Long followingId) {
        Follow findFollow = findByFollowerIdAndFollowingId(accountId, followingId);
        followRepository.deleteByFollowId(findFollow.getFollowId());
    }

    /*
    readOnly 메소드들
     */
    // 특정 유저가 다른 특정 유저를 팔로우 중인지 판별하여 리턴
    @Transactional(readOnly = true)
    public boolean isFollowing(Long followerId, Long followingId) {
        Account follower = accountService.findAccountById(followerId);
        Account following = accountService.findAccountById(followingId);
        return followRepository.existsByFollowerAndFollowing(follower, following);
    }

    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowerId(Long accountId) {
        Account findAccount = accountService.findAccountById(accountId);
        return followRepository.findAllByFollower(findAccount);
    }

    @Transactional(readOnly = true)
    public List<Follow> findAllByFollowingId(Long accountId){
        Account findAccount = accountService.findAccountById(accountId);
        return followRepository.findAllByFollowing(findAccount);
    }

    @Transactional(readOnly = true)
    public Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        Account follower = accountService.findAccountById(followerId);
        Account following = accountService.findAccountById(followingId);
        return followRepository.findByFollowerAndFollowing(follower, following);
    }




}
