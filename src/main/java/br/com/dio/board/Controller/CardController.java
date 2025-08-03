package br.com.dio.board.Controller;

import br.com.dio.board.model.Card;
import br.com.dio.board.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "*")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/columns/{columnId}/cards")
    public List<Card> getCardsByColumnId(@PathVariable Long columnId) {
        return service.findByBoardColumnId(columnId);
    }

    @GetMapping
    public List<Card> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        Optional<Card> card = service.findById(id);
        return card.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Card create(@RequestBody Card card) {
        return service.save(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Card> card = service.findById(id);
        if (card.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card card) {
        Optional<Card> existingOpt = service.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Card existing = existingOpt.get();
        existing.setTitle(card.getTitle());
        existing.setDescription(card.getDescription());
        // atualize outros campos que quiser permitir editar

        Card updated = service.save(existing);
        return ResponseEntity.ok(updated);
    }

}

