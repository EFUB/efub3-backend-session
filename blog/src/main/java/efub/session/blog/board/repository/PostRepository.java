package efub.session.blog.board.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndWriter_AccountId(Long postId, Long accountId);
    List<Post> findByWriter(Account account);
}