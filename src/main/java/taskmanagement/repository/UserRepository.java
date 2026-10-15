
package taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
