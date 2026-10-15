
package taskmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import taskmanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatusIgnoreCase(String status);

    List<Task> findByUser_Id(Long userId);

    Page<Task> findByStatusIgnoreCase(String status, Pageable pageable);
}
