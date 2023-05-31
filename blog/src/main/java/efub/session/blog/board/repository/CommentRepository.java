package efub.session.blog.board.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.board.domain.Comment;
import efub.session.blog.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByWriter(Account account);
    List<Comment> findByPost(Post post);
}
