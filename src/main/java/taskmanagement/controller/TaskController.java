
package taskmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskmanagement.dto.TaskRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import taskmanagement.dto.TaskResponse;

import taskmanagement.model.Task;
import taskmanagement.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. Get all tasks

    @GetMapping
    public List<TaskResponse> getAllTasks(
            @RequestParam(required = false) String status) {
        return taskService.getTaskResponses(status);
    }

    @GetMapping("/page")
    public Page<TaskResponse> getTasksPaginated(
            @RequestParam(required = false) String status,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return taskService.getTaskResponsesPaginated(status, pageable);
    }

    // 2. Create a task

    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody TaskRequest request) {

        Task createdTask = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    // 3. Get one task by ID
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskResponseById(id);
    }

    // 4. Update a task
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    // 5. Delete a task

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

}
