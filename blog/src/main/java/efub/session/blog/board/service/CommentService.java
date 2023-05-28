package efub.session.blog.board.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.board.domain.Comment;
import efub.session.blog.board.domain.Post;
import efub.session.blog.board.dto.request.CommentRequestDto;
import efub.session.blog.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final PostService postService;
    private final AccountService accountService;


    public Long create(CommentRequestDto requestDto, Long postId) {
        Account account = accountService.findAccountById(requestDto.getAccountId());
        Post post = postService.findPost(postId);
        Comment comment = commentRepository.save(requestDto.toEntity(account));
        comment.setPost(post);
        return comment.getId();
    }

    public void update(CommentRequestDto requestDto, Long commentId) {
        Comment comment = findById(commentId);
        checkValidMember(requestDto.getAccountId(), comment.getWriter().getAccountId());
        comment.updateComment(requestDto.getContent());
    }

    public void delete(Long commentId, Long accountId) {
        Comment comment = findById(commentId);
        checkValidMember(accountId, comment.getWriter().getAccountId());
        commentRepository.delete(comment);
    }

    private void checkValidMember(Long currentAccountId, Long tagetAccountId){
        if(currentAccountId != tagetAccountId){
            throw new IllegalArgumentException();
        }
    }

    @Transactional(readOnly = true)
    public List<Comment> findByPostObj(Long postId) {
        Post post = postService.findPost(postId);
        List<Comment> commentList = post.getCommentList();
        return commentList;
    }


    @Transactional(readOnly = true)
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + commentId));
    }

    @Transactional(readOnly = true)
    public List<Comment> findByWriter(Account author) {
        return commentRepository.findByWriter(author);
    }

    @Transactional(readOnly = true)
    public List<Comment> findByPost(Long postId) {
        Post post = postService.findPost(postId);
        return commentRepository.findByPost(post);
    }

}
