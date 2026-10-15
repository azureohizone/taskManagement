
package taskmanagement.service;

import org.springframework.stereotype.Service;

import taskmanagement.dto.UserRequest;
import taskmanagement.dto.UserResponse;
import taskmanagement.model.User;
import taskmanagement.repository.UserRepository;
import java.util.List;
import taskmanagement.dto.TaskResponse;
import taskmanagement.exception.UserNotFoundException;
import taskmanagement.repository.TaskRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(
            UserRepository userRepository,
            TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User(
                null,
                request.getName(),
                request.getEmail());

        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);
    }

    public List<TaskResponse> getTasksByUserId(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return taskRepository.findByUser_Id(userId)
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

}
