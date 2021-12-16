package edu.miu.cs.cs544.EAProject.controller.admin;

import edu.miu.cs.cs544.EAProject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admins/events")
@RestController
public class AdminEventController {

    private final AdminService adminService;

    @PostMapping("/start")
    public void startRegistrationProcess() {
        adminService.processLatestEventRegistration();
    }

    @PostMapping("/{eventId}/start")
    public void startRegistrationProcess(@PathVariable int eventId) {
        adminService.processRegistration(eventId);
    }
}
