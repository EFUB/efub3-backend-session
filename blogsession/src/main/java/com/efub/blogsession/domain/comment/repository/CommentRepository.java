package com.efub.blogsession.domain.comment.repository;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 작성자(writer)별 댓글 목록 조회
    List<Comment> findAllByWriter(Account writer);
    // 게시글(post)별 댓글 목록 조회
    List<Comment> findAllByPost(Post post);
}
