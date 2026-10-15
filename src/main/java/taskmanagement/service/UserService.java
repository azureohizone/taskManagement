
package taskmanagement.service;

import org.springframework.stereotype.Service;

import taskmanagement.dto.UserRequest;
import taskmanagement.dto.UserResponse;
import taskmanagement.model.User;
import taskmanagement.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User(
            null,
            request.getName(),
            request.getEmail()
        );

        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);
    }
}
