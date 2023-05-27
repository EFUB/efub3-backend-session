package efub.session.blog.follow.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByFollowerAndFollowing(Account follower, Account following);
    void deleteByFollowId(Long followId);

    List<Follow> findAllByFollower(Account follower);
    List<Follow> findAllByFollowing(Account following);

    Boolean existsByFollowerAndFollowing(Account follower, Account following);
}
