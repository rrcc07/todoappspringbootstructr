package com.todoapp.demo.repository;

import com.todoapp.demo.model.entity.Task;
import com.todoapp.demo.model.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends JpaRepository<Task, Long> {

    public Optional<Task> findById(Long id);

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASKS SET FINISHED=true WHERE id= :id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE TASKS SET STATUS= :status WHERE id= :id", nativeQuery = true)
    public void markTaskStatusAsOnLate(@Param("id") Long id, @Param("status") String status);


}
