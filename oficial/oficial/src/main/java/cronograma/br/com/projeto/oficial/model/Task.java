package cronograma.br.com.projeto.oficial.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
@Table(name = "Tarefas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate dataStart;

    private LocalDate dataEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private typeTask type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private state state;

    private Boolean finish = false;

}
