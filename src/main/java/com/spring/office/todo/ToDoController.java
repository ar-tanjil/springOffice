package com.spring.office.todo;


import com.spring.office.todo.domain.ToDo;
import com.spring.office.todo.domain.ToDoDto;
import com.spring.office.todo.domain.ToDoStatus;
import com.spring.office.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoController {

    private final TodoService service;

    @GetMapping("/employee/{id}")
    public Iterable<ToDoDto> getToDoByEmployee(
            @PathVariable("id") Long id
    ){
        return service.getToDoByEmployee(id);
    }

    @PostMapping
    public void saveTodo(
            @RequestBody ToDoDto toDo
    ){
        service.saveTodo(toDo);
    }


    @GetMapping("/status/{id}")
    public void statusChange(
            @PathVariable("id") Long id
    ){
        service.makeToDoComplete(id);
    }

    @GetMapping("/active/{id}")
    public Iterable<ToDoDto> getActive(
            @PathVariable("id") Long id
    ){
        return service.getActiveTask(id);
    }

    @GetMapping("/completed/{id}")
    public Iterable<ToDoDto> getCompleted(
            @PathVariable("id") Long id
    ){
        return service.getCompletedTodo(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(
            @PathVariable("id") Long id
    ){
        service.deleteTodo(id);
    }

}
