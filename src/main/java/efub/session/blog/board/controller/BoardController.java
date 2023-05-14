package efub.session.blog.board.controller;

import efub.session.blog.board.domain.Board;
import efub.session.blog.board.dto.BoardRequestDto;
import efub.session.blog.board.dto.BoardResponseDto;
import efub.session.blog.board.service.BoardService;
import efub.session.blog.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(value=HttpStatus.CREATED)
    public BoardResponseDto boardAdd(@RequestBody BoardRequestDto requestDto){
        Board board = boardService.addBoard(requestDto);
        return new BoardResponseDto(board);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BoardResponseDto> boardListFind(){
        List<Board> boardList=boardService.findBoardList();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();

        for(Board board:boardList){
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardFind(@PathVariable Long boardId){
        Board board = boardService.findBoard(boardId);
        return new BoardResponseDto(board);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String boardRemove(@PathVariable Long boardId,@RequestParam Long memberId){
        boardService.removeBoard(boardId,memberId);
        return "성공적으로 삭제되었습니다.";
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public BoardResponseDto boardModify(@PathVariable Long boardId,@RequestBody BoardRequestDto requestDto){
        Board board = boardService.modifyBoard(boardId,requestDto);
        return new BoardResponseDto(board);
    }

}
