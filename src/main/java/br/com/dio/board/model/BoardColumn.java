package br.com.dio.board.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "board_columns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer columnOrder;  // ordem da coluna no board

    @Enumerated(EnumType.STRING)
    private BoardColumnKind kind; // enum para tipo da coluna (INITIAL, PENDING, FINAL, CANCEL)

    // Cada coluna pertence a um board
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnoreProperties("columns")
    private Board board;

    // Uma coluna tem vários cards
    @OneToMany(mappedBy = "boardColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;
}
