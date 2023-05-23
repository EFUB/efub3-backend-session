package efub.session.blog.board.repository;

import efub.session.blog.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Optional<Board> findByBoardIdAndOwner_MemberId(Long boardId,Long memberId);
}
