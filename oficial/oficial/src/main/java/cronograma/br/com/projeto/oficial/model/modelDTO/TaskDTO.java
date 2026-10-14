package cronograma.br.com.projeto.oficial.model.modelDTO;

import cronograma.br.com.projeto.oficial.model.state;
import cronograma.br.com.projeto.oficial.model.typeTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    private Long taskId;

    private String title;

    private LocalDate dataStart;

    private LocalDate dataEnd;

    private typeTask type;

    private state state;

    private state finish;
}
