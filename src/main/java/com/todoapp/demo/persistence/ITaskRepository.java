package com.todoapp.demo.persistence;

import com.todoapp.demo.model.entity.TaskStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository<TypeTask> {

    Optional<TypeTask> get(Long id);
    TypeTask create(TypeTask typeTask);
    Collection<TypeTask> getAll();
    boolean delete(long id);
    boolean exist(long id);

    /**List<TypeTask> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASKS SET STATUS= :status WHERE id= :id", nativeQuery = true)
    public void markTaskStatusAsOnLate(@Param("id") Long id, @Param("status") String status);
    @Modifying
    @Query(value = "UPDATE TASKS SET FINISHED=true WHERE id= :id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);*/



}
