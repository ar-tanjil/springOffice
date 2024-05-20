package com.spring.office.todo.service;

import com.spring.office.employee.Employee;
import com.spring.office.todo.domain.ToDo;
import com.spring.office.todo.domain.ToDoDto;
import com.spring.office.todo.domain.ToDoStatus;
import com.spring.office.todo.repo.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepo todoRepo;
    private final ToDoMapper toDoMapper;
     public void saveTodo(ToDoDto toDo){
         toDo.setStatus(ToDoStatus.ACTIVE);
         todoRepo.save(toDoMapper.dtoToToDo(toDo));
     }

     public List<ToDoDto> getToDoByEmployee(Long empId){

         Employee emp = new Employee();
         emp.setId(empId);

         return todoRepo.findByEmployee(emp,
                 Sort.by(Sort.Direction.DESC, "createTime"))
                 .stream()
                 .map(toDoMapper::toDoToDto)
                 .toList();
     }

     public void makeToDoComplete(Long id){
         todoRepo.updateTodo(id, ToDoStatus.COMPLETED);
     }

    public Iterable<ToDoDto> getActiveTask(Long id) {
         Employee employee = new Employee();
         employee.setId(id);

        return todoRepo
                .findByEmployeeAndStatus(employee, ToDoStatus.ACTIVE)
                .stream()
                .map(toDoMapper::toDoToDto)
                .toList();
     }

     public Iterable<ToDoDto> getCompletedTodo(Long id){
         Employee employee = new Employee();
         employee.setId(id);

         return todoRepo
                 .findByEmployeeAndStatus(employee, ToDoStatus.COMPLETED)
                 .stream()
                 .map(toDoMapper::toDoToDto)
                 .toList();
     }

    public void deleteTodo(Long id) {
        todoRepo.deleteById(id);
     }
}
