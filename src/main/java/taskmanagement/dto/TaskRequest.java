
package taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TaskRequest {

    @NotBlank(message = "Task title is required")
    private String title;

    @NotBlank(message = "Task status is required")
    @Pattern(regexp = "TODO|IN_PROGRESS|DONE", message = "Status must be TODO, IN_PROGRESS, or DONE")
    private String status;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be greater than zero")
    private Long userId;

    public TaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
