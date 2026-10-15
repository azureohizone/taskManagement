
package taskmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import taskmanagement.exception.TaskNotFoundException;
import taskmanagement.model.Task;
import taskmanagement.repository.TaskRepository;
import taskmanagement.dto.TaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import taskmanagement.dto.TaskResponse;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 2. Create a task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // 3. Get one task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // 4. Update an existing task
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    // 5. Delete a task
    public void deleteTask(Long id) {
        Task existingTask = getTaskById(id);

        taskRepository.delete(existingTask);
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setStatus(request.getStatus());

        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(String status) {
        if (status == null || status.isBlank()) {
            return taskRepository.findAll();
        }

        return taskRepository.findByStatusIgnoreCase(status);
    }
    

    public Page<Task> getTasksPaginated(String status, Pageable pageable) {
    if (status == null || status.isBlank()) {
        return taskRepository.findAll(pageable);
    }

    return taskRepository.findByStatusIgnoreCase(status, pageable);
}

public TaskResponse getTaskResponseById(Long id) {
    Task task = getTaskById(id);
    return TaskResponse.from(task);
}


public List<TaskResponse> getTaskResponses(String status) {
    return getTasksByStatus(status)
            .stream()
            .map(TaskResponse::from)
            .collect(Collectors.toList());
}


public Page<TaskResponse> getTaskResponsesPaginated(
        String status, Pageable pageable) {

    return getTasksPaginated(status, pageable)
            .map(TaskResponse::from);
}




}
