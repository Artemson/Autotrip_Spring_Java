package com.main.app.repo;

import com.main.app.models.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findAllByUserId(Long user_id);
    List<Task> findByStatusAndObserver(String status, String observer);
    List<Task> findByStatusAndExecutor(String status, String executor);
    List<Task> findAllByAuthor(String author);
    List<Task> findAllByObserver(String observer);
    List<Task> findAllByExecutor(String executor);
}
