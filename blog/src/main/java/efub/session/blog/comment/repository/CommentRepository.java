package efub.session.blog.comment.repository;

import efub.session.blog.account.domain.Account;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByWriter(Account writer);

    List<Comment> findAllByPost(Post post);
}
