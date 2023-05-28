package efub.session.blog.follow.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByFollowerAndFollowing(Account follower, Account following);
    // 팔로우 존재 확인
    Boolean existsByFollowerAndFollowing(Account follower,Account following);

    // 팔로우 목록
    List<Follow> findAllByFollower(Account follower);

    List<Follow> findAllByFollowing(Account following);

    // 팔로우 취소
    void deleteByFollowId(Long followId);

}