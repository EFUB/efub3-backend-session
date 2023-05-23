package efub.session.blog.comment.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.heart.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import efub.session.blog.comment.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Integer countByComment(Comment commmnent);
    List<CommentHeart> findByWriter(Account account);

    boolean existsByWriterAndComment(Account account, Comment comment);

    Optional<CommentHeart> findByWriterAndComment(Account account, Comment comment);


}