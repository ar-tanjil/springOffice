package com.spring.office.payroll.controller;


import com.spring.office.notification.notificaion.NotificationDto;
import com.spring.office.notification.notificaion.NotificationEntity;
import com.spring.office.notification.notificaion.NotificationService;
import com.spring.office.payroll.dto.LeaveDto;
import com.spring.office.payroll.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/emp_leaves")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveController {

    private final LeaveService leaveService;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;
    @PostMapping
    public LeaveDto saveLeave(
            @RequestBody LeaveDto dto
    ){
        var save = leaveService.saveLeave(dto);

        NotificationEntity notify = new NotificationEntity();
        notify.setContent(save.getId()+ "-" + save.getEmployeeId());
        notify.setSenderId(save.getEmployeeId().toString());
        notify.setRecipientId("admin");

        var notification = notificationService.save(notify);

        messagingTemplate.convertAndSendToUser(
                "admin", "/topic",
                new NotificationDto(
                        notification.getId(),
                        notification.getSenderId(),
                        notification.getRecipientId(),
                        notification.getContent()
                )
        );
        return save;
    }

    @PutMapping("/{leaveId}")
    public LeaveDto grantLeave(
            @PathVariable("leaveId") Long id
    ){
        return leaveService.grantLeave(id);
    }


}
