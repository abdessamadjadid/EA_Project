package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.repository.StudentRegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import edu.miu.cs.cs544.EAProject.service.AdminService;
import edu.miu.cs.cs544.EAProject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    EventService eventService;

    @Autowired
    AcademicBlockService academicBlockService;

    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;

    @Override
    public void processRegistration() {

        //Get latest Registration Event
       RegistrationEvent event =  eventService.getLatestEvent();
       // Get list academic block
       List<AcademicBlock> academicBlockList = eventService.getEventAcademicBlock(event);

       for (AcademicBlock block : academicBlockList){
            Collection<CourseOffering> courseOfferingList = block.getCourseOfferings();
           for(CourseOffering courseOffering : courseOfferingList){
                manageRequests(courseOffering.getRegistrationRequests());
           }
       }
    }

    private void manageRequests(Collection<RegistrationRequest> registrationRequests) {
        for(RegistrationRequest request : registrationRequests){
            //check priority 1
            if(request.getPriority() == 1 && request.getCourseOffering().getAvailableSeats() > 0){
                //register student
                registerStudent(request);
            }else if(request.getCourseOffering().getAvailableSeats() > 0){
                //register student randomly
                RegistrationRequest randomRequest = getRandomRequest(registrationRequests);
                registerStudent(randomRequest);
            }else{
                //Full capacity
            }
        }
    }

    private void registerStudent(RegistrationRequest request) {
        Student student = request.getStudent();
        if(student.addCourse(request.getCourseOffering())){
            studentRegistrationRepository.flush();
        }
    }

    private RegistrationRequest getRandomRequest(Collection<RegistrationRequest> registrationRequests) {
        Random rand = new Random();

        int randomIndex = rand.nextInt(registrationRequests.size());
        RegistrationRequest randomElement = registrationRequests.iterator().next();
        registrationRequests.remove(randomIndex);
        return randomElement;
    }
}
