package efub.session.blog.heart.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.post.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Integer countByComment(Comment comment);
    List<CommentHeart> findByWriter(Account account);
    boolean existsByWriterAndComment(Account account, Comment comment);
    Optional<CommentHeart> findByWriterAndComment(Account account, Comment comment);

}
