package efub.session.blog.board.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.board.domain.Comment;
import efub.session.blog.board.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {

    Integer countByComment(Comment comment);
    List<CommentHeart> findByWriter(Account account);
    boolean existsByWriterAndComment(Account account, Comment comment);
    Optional<CommentHeart> findByWriterAndComment(Account account, Comment comment);
}