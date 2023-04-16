package efub.session.blog.domain.post.repository;

import efub.session.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndWriter_AccountId(Long postId, Long accountId);
}
