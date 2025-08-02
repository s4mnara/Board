package br.com.dio.board.Controller;

import br.com.dio.board.model.Board;
import br.com.dio.board.model.BoardColumn;
import br.com.dio.board.service.BoardService;
import br.com.dio.board.service.BoardColumnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardColumnService columnService;

    public BoardController(BoardService boardService, BoardColumnService columnService) {
        this.boardService = boardService;
        this.columnService = columnService;
    }

    // GET /boards
    @GetMapping
    public List<Board> findAllBoards() {
        return boardService.findAll();
    }

    // GET /boards/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Board> findBoardById(@PathVariable Long id) {
        return boardService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /boards
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board saved = boardService.save(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // DELETE /boards/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        if (boardService.findById(id).isPresent()) {
            boardService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // POST /boards/{boardId}/columns
    @PostMapping("/{boardId}/columns")
    public ResponseEntity<BoardColumn> addColumnToBoard(@PathVariable Long boardId, @RequestBody BoardColumn column) {
        Optional<Board> boardOpt = boardService.findById(boardId);
        if (boardOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Board board = boardOpt.get();
        column.setBoard(board);
        BoardColumn saved = columnService.save(column);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /boards/{boardId}/columns
    @GetMapping("/{boardId}/columns")
    public ResponseEntity<List<BoardColumn>> getColumnsByBoard(@PathVariable Long boardId) {
        Optional<Board> boardOpt = boardService.findById(boardId);
        if (boardOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<BoardColumn> columns = columnService.findByBoardId(boardId);
        return ResponseEntity.ok(columns);
    }
}
