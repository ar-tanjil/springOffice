package com.spring.office.todo.service;

import com.spring.office.dto.table.EmployeeTable;
import com.spring.office.employee.Employee;
import com.spring.office.todo.domain.ToDo;
import com.spring.office.todo.domain.ToDoDto;
import org.springframework.stereotype.Service;

@Service
public class ToDoMapper {

    public ToDo dtoToToDo(ToDoDto dto){
        if (dto.getEmployeeId() == null){
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getEmployeeId());

        return ToDo.builder()
                .id(dto.getId())
                .employee(employee)
                .status(dto.getStatus())
                .description(dto.getDescription())
                .build();

    }


    public ToDoDto toDoToDto(ToDo toDo){
        return ToDoDto.builder()
                .id(toDo.getId())
                .description(toDo.getDescription())
                .status(toDo.getStatus())
                .createTime(toDo.getCreateTime())
                .build();
    }


}
