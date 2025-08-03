package br.com.dio.board.Controller;
import br.com.dio.board.service.CardService;
import br.com.dio.board.model.BoardColumn;
import br.com.dio.board.model.Card;
import br.com.dio.board.service.BoardColumnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/columns")
@CrossOrigin(origins = "*")
public class BoardColumnController {

    private final BoardColumnService service;
    private final CardService cardService;

    public BoardColumnController(BoardColumnService service, CardService cardService) {
        this.service = service;
        this.cardService = cardService;
    }

    @GetMapping("/{columnId}/cards")
    public List<Card> getCardsByColumnId(@PathVariable Long columnId) {
        return cardService.findByBoardColumnId(columnId);
    }

    // POST /columns/board/{boardId}
    @PostMapping("/board/{boardId}")
    public ResponseEntity<BoardColumn> createForBoard(
            @PathVariable Long boardId,
            @RequestBody BoardColumn column) {
        BoardColumn created = service.createColumnForBoard(boardId, column);
        return ResponseEntity.status(201).body(created);
    }

    // GET /columns
    @GetMapping
    public List<BoardColumn> findAll() {
        return service.findAll();
    }

    // GET /columns/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BoardColumn> findById(@PathVariable Long id) {
        Optional<BoardColumn> column = service.findById(id);
        return column.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /columns/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BoardColumn> update(@PathVariable Long id, @RequestBody BoardColumn column) {
        Optional<BoardColumn> existingOpt = service.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BoardColumn existing = existingOpt.get();
        existing.setName(column.getName());
        // Outros campos que você quiser permitir editar

        BoardColumn updated = service.save(existing);
        return ResponseEntity.ok(updated);
    }

    // DELETE /columns/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<BoardColumn> column = service.findById(id);
        if (column.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
