package cronograma.br.com.projeto.oficial.model.modelService;

import cronograma.br.com.projeto.oficial.model.Task;
import cronograma.br.com.projeto.oficial.model.modelDTO.TaskDTO;
import cronograma.br.com.projeto.oficial.model.modelRepository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void AddNewTask(Task ts){
        boolean confirmTask = taskRepository.findAll().stream().anyMatch(
                t -> t.getTitle().equals(ts.getTitle()) &&
                        t.getDataStart() .equals(ts.getDataStart()) &&
                        t.getDataEnd() .equals(ts.getDataEnd()));
        if(confirmTask){ throw new IllegalArgumentException("Essa Atividade já existe");}
        taskRepository.save(ts);
    }

    public void updateTask(TaskDTO ts){
       Task task = taskRepository.findById(ts.getTaskId()).orElseThrow( () ->
               new IllegalArgumentException("Tarefa não encontrada"));
       task.setState(ts.getState());
       taskRepository.save(task);
    }

}
