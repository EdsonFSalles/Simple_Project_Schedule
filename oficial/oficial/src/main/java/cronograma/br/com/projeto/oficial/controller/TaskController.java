package cronograma.br.com.projeto.oficial.controller;

import cronograma.br.com.projeto.oficial.model.Task;
import cronograma.br.com.projeto.oficial.model.modelDTO.TaskDTO;
import cronograma.br.com.projeto.oficial.model.modelRepository.TaskRepository;
import cronograma.br.com.projeto.oficial.model.modelService.TaskService;
import cronograma.br.com.projeto.oficial.model.state;
import cronograma.br.com.projeto.oficial.model.typeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/principal")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping("/listarTarefas")
    public String listaTarefas(Model model){
        List<Task> AllTasks = taskRepository.findAll();
        model.addAttribute("newTask", new Task());
        model.addAttribute("states", state.values());
        model.addAttribute("types", typeTask.values());
        model.addAttribute("task", AllTasks);
        return "html/task";
    }

    @PostMapping("/cadastrarTarefas")
    public String cadastrarTarefas(@ModelAttribute Task ts){
        taskService.AddNewTask(ts);
        return "redirect:/principal/listarTarefas";
    }

    @PostMapping("/atualizarTask/{tsId}")
    public String atualizarTask(@PathVariable Long tsId, @ModelAttribute TaskDTO DTO ){
        DTO.setTaskId(tsId);
        taskService.updateTask(DTO);
        return "redirect:/principal/listarTarefas";
    }

    @PostMapping("/deletarTarefas/{tsId}")
    public String detelarTarefas(@PathVariable Long tsId){
        Task task = taskRepository.findById(tsId).get();
        taskRepository.delete(task);
        return "redirect:/principal/listarTarefas";
    }


}
