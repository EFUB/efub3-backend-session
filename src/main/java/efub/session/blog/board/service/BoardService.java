package efub.session.blog.board.service;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.dto.BoardRequestDto;
import efub.session.blog.board.repository.BoardRepository;
import efub.session.blog.member.MemberRepository;
import efub.session.blog.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Board addBoard(BoardRequestDto requestDto){
        Member owner = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        return boardRepository.save(
                Board.builder()
                        .name(requestDto.getName())
                        .description(requestDto.getDescription())
                        .notice((requestDto.getNotice()))
                        .owner(owner)
                        .build()
        );
    }
    public List<Board> findBoardList() {
        return boardRepository.findAll();
    }

    public Board findBoard(Long boardId){
        return boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판입니다."));
    }

    public void removeBoard(Long boardId, Long memberId) {
        Board board = boardRepository.findByBoardIdAndOwner_MemberId(boardId,memberId)
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        boardRepository.delete(board);

    }

    public Board modifyBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = boardRepository.findByBoardIdAndOwner_MemberId(boardId,requestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        board.updateBoard(requestDto);
        return board;
    }
}
