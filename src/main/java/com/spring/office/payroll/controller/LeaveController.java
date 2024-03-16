package com.spring.office.payroll.controller;


import com.spring.office.notification.NotificationController;
import com.spring.office.notification.notificaion.NotificationDto;
import com.spring.office.notification.notificaion.NotificationEntity;
import com.spring.office.notification.notificaion.NotificationService;
import com.spring.office.notification.notificaion.Type;
import com.spring.office.payroll.dto.LeaveDto;
import com.spring.office.payroll.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/emp_leaves")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveController {

    private final LeaveService leaveService;
    private final NotificationController notificationController;
    @PostMapping
    public LeaveDto saveLeave(
            @RequestHeader("Name") String name,
            @RequestBody LeaveDto dto
    ){

        var save = leaveService.saveLeave(dto);
        NotificationEntity notify = new NotificationEntity();
        notify.setContent("Leave Request From " + name.toUpperCase());
        notify.setType(Type.LEAVE);
        notify.setSenderId(name.toLowerCase());
        notify.setRecipientId("admin");

        notificationController.sendNotificationToAdmin(notify);

        return save;
    }

    @GetMapping("grant/{leaveId}")
    public boolean grantLeave(
            @PathVariable("leaveId") Long id
    ){
        return leaveService.grantLeave(id);
    }


    @GetMapping("reject/{leaveId}")
    public boolean rejectLeave(
            @PathVariable("leaveId") Long id
    ){
        return leaveService.rejectLeave(id);
    }


    @GetMapping
    public Iterable<LeaveDto> getAllLeave(){
        return leaveService.getAllLeave();
    }


    @GetMapping("/on_leave")
    public Integer getOnLeave(){
        var date = LocalDate.now();
        return leaveService.getTodayLeaveCount(date);
    }


}
