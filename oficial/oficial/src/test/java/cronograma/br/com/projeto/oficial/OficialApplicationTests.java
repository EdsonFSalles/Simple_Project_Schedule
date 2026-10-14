package cronograma.br.com.projeto.oficial;

import cronograma.br.com.projeto.oficial.controller.TaskController;
import cronograma.br.com.projeto.oficial.model.Task;
import cronograma.br.com.projeto.oficial.model.modelDTO.TaskDTO;
import cronograma.br.com.projeto.oficial.model.modelRepository.TaskRepository;
import cronograma.br.com.projeto.oficial.model.modelService.TaskService;
import cronograma.br.com.projeto.oficial.model.state;
import cronograma.br.com.projeto.oficial.model.typeTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.NotExtensible;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OficialApplicationTests {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	private TaskController taskController;

	private Task task1;
	private TaskDTO taskDTO1;

	@BeforeEach
	void setUp() {
		task1 = Task.builder()
				.id(1L)
				.title("taskTest001")
				.type(typeTask.ATIVIDADE)
				.state(state.EM_ANDAMENTO)
				.dataStart(LocalDate.now())
				.dataEnd(LocalDate.now().plusDays(20))
				.build();
		taskDTO1 =  TaskDTO.builder()
				.taskId(1L)
				.state(state.FINALIZADO)
				.build();
	}

	@Test
	@DisplayName("New task register")
	void newTaskRegisterTest() {
		when(taskRepository.save(any(Task.class))).thenReturn(task1);
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

		Task taskSave = taskRepository.save(task1);


		assertThat(taskSave.getId()).isEqualTo(task1.getId());
		assertThat(taskRepository.findById(task1.getId())).isPresent();
	}


	@Test
	@DisplayName("Delete task")
	void deleteTaskTest() {
		doNothing().when(taskRepository).delete(task1);
		taskRepository.delete(task1);

		verify(taskRepository, times(1)).delete(task1);
	}

	@Test
	@DisplayName("Update task")
	void deveAtualizarEstadoDaTask() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
		when(taskRepository.save(any(Task.class))).thenReturn(task1);

		taskService.updateTask(taskDTO1);

		assertThat(task1.getState()).isEqualTo(state.FINALIZADO);
		verify(taskRepository, times(1)).findById(1L);
		verify(taskRepository, times(1)).save(task1);
	}

}
