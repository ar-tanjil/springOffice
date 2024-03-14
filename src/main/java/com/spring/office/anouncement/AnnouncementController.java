package com.spring.office.anouncement;


import com.spring.office.anouncement.dto.AnnouncementDto;
import com.spring.office.anouncement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/announcements")
@CrossOrigin(origins = "http://localhost:4200")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public void saveAnnouncement(
         @RequestBody  AnnouncementDto dto
    ) {
        announcementService.saveAnnouncement(dto);
    }

    @GetMapping
    public Iterable<AnnouncementDto> getAllAnnouncement() {
        return announcementService.getAllAnnouncement();
    }

    @GetMapping("/department/{dep_id}")
    public Iterable<AnnouncementDto> getAnnouncementByDep(
            @PathVariable("dep_id") Long id
    ) {
        return announcementService.getAllAnnouncementByDepartment(id);
    }

    @GetMapping("/{id}")
    public  AnnouncementDto getAnnouncementById(
            @PathVariable("id") Long id
    ){
        return announcementService.getAnnouncementById(id);
    }
}
