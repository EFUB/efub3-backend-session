package efub.session.blog.domain.post.repository;

<<<<<<< HEAD
import efub.session.blog.domain.account.domain.Account;
import efub.session.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostIdAndWriter_AccountId(Long postId, Long accountId);

    List<Post> findAllByWriter(Account writer);
=======
import efub.session.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndWriter_AccountId(Long postId, Long accountId);
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
}
