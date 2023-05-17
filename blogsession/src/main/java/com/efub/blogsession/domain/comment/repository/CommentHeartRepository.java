package com.efub.blogsession.domain.comment.repository;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.comment.domain.Comment;
import com.efub.blogsession.domain.comment.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


// 3. 의존성 주입
public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Integer countByComment(Comment comment);
    List<CommentHeart> findByWriter(Account account);
    boolean existsByWriterAndComment(Account account,Comment comment);
    Optional<CommentHeart> findByWriterAndComment(Account account, Comment comment);
}
