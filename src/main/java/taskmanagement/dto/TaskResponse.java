
package taskmanagement.dto;

import taskmanagement.model.Task;

public class TaskResponse {

    private Long id;
    private String title;
    private String status;

    public TaskResponse(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getID(),
            task.getTitle(),
            task.getStatus()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
