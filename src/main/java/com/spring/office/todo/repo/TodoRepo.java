package com.spring.office.todo.repo;

import com.spring.office.employee.Employee;
import com.spring.office.todo.domain.ToDo;
import com.spring.office.todo.domain.ToDoDto;
import com.spring.office.todo.domain.ToDoStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepo extends JpaRepository<ToDo,Long> {
    List<ToDo> findByEmployee(Employee emp, Sort createTime);

    @Modifying
    @Transactional
    @Query("update ToDo t set  t.status = :status  where t.id = :id")
    void updateTodo(Long id, ToDoStatus status);

    List<ToDo> findByEmployeeAndStatus(Employee employee, ToDoStatus toDoStatus);
}
