package com.spring.office.employee;

import com.spring.office.dto.table.EmployeeTable;
import com.spring.office.dto.Message;
import com.spring.office.security.auth.EmployeeUserService;
import com.spring.office.security.auth.dto.UserEmpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService empService;
    private final EmployeeUserService userService;


    @GetMapping
    public List<EmployeeTable> getAll() {
        return empService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable("id") Long id){
        return empService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeDto saveEmployee(
           @Valid @RequestBody EmployeeDto dto){
        return userService.createNewUser(dto);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") Long id,
                                      @RequestBody EmployeeDto dto){
        return empService.updateEmployee(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") Long id){
        boolean success = empService.deleteEmployeeById(id);
        if (success){
            Message successMsg = new Message("Success");
            return new ResponseEntity<>(successMsg,HttpStatus.ACCEPTED);
        }
        Message failedMsg = new Message("Not Found");
        return new ResponseEntity<>(failedMsg, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/without_sal")
    public Iterable<EmployeeShortDetails> getSalaryLessEmp(){
        return empService.employeeWithoutSalary();
    }

    @GetMapping("/without_leave")
    public Iterable<EmployeeShortDetails> getLevePolicyLessEmp(){
        return empService.employeeWithoutLeavePolicy();
    }

    @GetMapping("/count/employee")
    public Integer countAllEmployee(){
        return empService.countAllEmployee();
    }

    @GetMapping("/job/{id}")
    public Iterable<EmployeeShortDetails> getByJob(
            @PathVariable("id") Long id
    ){
        return empService.getEmployeeByJob(id);
    }


    @GetMapping("/department/{id}")
    public Iterable<EmployeeShortDetails> getByDepartment(
            @PathVariable("id") Long id
    ){
        return empService.getEmployeeByDepartment(id);
    }


    @GetMapping("/all/user")
    public Iterable<UserEmpDto> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/change/user/role/{id}")
    public void changeUserRole(
            @PathVariable("id") Long id
    ){
        userService.changerUserRole(id);
    }

}
