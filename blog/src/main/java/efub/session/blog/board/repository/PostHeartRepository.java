package efub.session.blog.board.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.board.domain.Post;
import efub.session.blog.board.domain.PostHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {
    Integer countByPost(Post post);
    List<PostHeart> findByWriter(Account account);
    boolean existsByWriterAndPost(Account account, Post post);
    Optional<PostHeart> findByWriterAndPost(Account account, Post post);
}