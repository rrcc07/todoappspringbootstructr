package com.todoapp.demo.persistence.repository;

import com.todoapp.demo.model.entity.TaskStatus;
import com.todoapp.demo.persistence.dao.TaskDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JPATaskRepository extends JpaRepository<TaskDAO, Long> {
    /**QUERY METHOD*/
    List<TaskDAO> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASKS SET STATUS= :status WHERE id= :id", nativeQuery = true)
    void markTaskStatusAsOnLate(@Param("id") Long id, @Param("status") String status);

    @Modifying
    @Query(value = "UPDATE TASKS SET FINISHED=true WHERE id= :id", nativeQuery = true)
    void markTaskAsFinished(@Param("id") Long id);
}
