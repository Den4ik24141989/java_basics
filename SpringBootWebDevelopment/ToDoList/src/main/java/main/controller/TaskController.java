package main.controller;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public ResponseEntity getTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        ArrayList<Task> list = new ArrayList<>();
        tasks.forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity addTask(Task task) {
        taskRepository.save(task);
        System.out.println("Задача " + task.getTitle() + " создана");
        return new ResponseEntity<>("Задача " + task.getTitle() + " создана", HttpStatus.resolve(201));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Задача отсутствует");
        }
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<String> patchTask(Task task) {
        int taskId = task.getId();
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Задача отсутствует");
        }
        task.setCreationTime(optionalTask.get().getCreationTime());
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Задача отсутствует");
        }
        taskRepository.deleteById(id);
        return new ResponseEntity("Задача удалена", HttpStatus.OK);
    }
}
