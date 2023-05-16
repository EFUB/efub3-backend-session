package com.efub.blogsession.domain.post.repository;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostIdAndWriter_AccountId(Long postId, Long accountId);
    List<Post> findAllByWriter(Account writer);

}
