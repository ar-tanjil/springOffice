package com.spring.office.todo.domain;

import com.spring.office.employee.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ToDoDto {
    private Long id;
    private String description;
    private ToDoStatus status;
    private LocalDateTime createTime;
    private Long employeeId;

}
