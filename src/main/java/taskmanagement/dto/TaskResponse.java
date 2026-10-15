
package taskmanagement.dto;

import taskmanagement.model.Task;

public class TaskResponse {

    private Long id;
    private String title;
    private String status;
    private Long userId;
    private String userName;

    public TaskResponse(
            Long id,
            String title,
            String status,
            Long userId,
            String userName) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.userId = userId;
        this.userName = userName;
    }

    public static TaskResponse from(Task task) {
        Long userId = task.getUser() != null
                ? task.getUser().getId() : null;

        String userName = task.getUser() != null
                ? task.getUser().getName() : null;

        return new TaskResponse(
            task.getID(),
            task.getTitle(),
            task.getStatus(),
            userId,
            userName
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

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
