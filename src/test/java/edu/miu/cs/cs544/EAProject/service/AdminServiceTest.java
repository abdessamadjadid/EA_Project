package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.error.ClientException;
import edu.miu.cs.cs544.EAProject.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Autowired
    EventService eventService;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    AcademicBlockRepository academicBlockRepository;

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;


    User admin = new User();
    User user = new User();

    @Before
    public void initData(){

    }

    @Test
    public void TestEventClosed(){

        var event = eventService.getEventById(3);

        // Make sure event closed
        assertThat(event.getStatus(), is(EventStatus.CLOSED));

        // Process registration
        adminService.processRegistration(3);
    }

    @Test
    public void TestEventOpen(){

        var event = eventService.getEventById(3);
        //Open Event
        event.getStartEndDate().setModifiedDate(event.getStartEndDate().getModifiedDate().plusDays(2));

        // Process registration
        Exception exception = assertThrows(
                ClientException.class,
                () -> adminService.processRegistration(3)
        );

        assertTrue(exception.getMessage().contains("open"));
        assertThat(event.getStatus(), is(EventStatus.OPEN));

    }

    @Test
    public void TestRegisteredCount(){

        adminService.processRegistration(3);

        //Test registrationn count
        var registered =  registrationRepository.findAll();
        var courseResiteredCount = 0L;
        var blocks = academicBlockRepository.findAll();

        var offeringCount = 0;

        for (AcademicBlock block : blocks){
           var courseOfferings = block.getCourseOfferings();
           offeringCount += courseOfferings.size();
           for (CourseOffering courseOffering : courseOfferings){
               courseResiteredCount += courseOffering.getRegistrations().size();
           }
        }
        var registeredCount = StreamSupport.stream(registered.spliterator(), false).count();
       // var requestCount = StreamSupport.stream(requests.spliterator(), false).count();

        assertThat(blocks.size(), is(3));
        assertThat(offeringCount, is( courseOfferingRepository.findAll().size()));
        assertThat(courseResiteredCount, is(registeredCount));

    }


    @Test
    public void TestStudentReceiveCoursePerBlock(){
        adminService.processRegistration(3);

        var students = studentRegistrationRepository.findAll();
        var registered =  registrationRepository.findAll();
        var blockCount = academicBlockRepository.findAll().size();
        List<AcademicBlock> list = new ArrayList<>();

        for(Student student : students){
            int count = 0;
           var registrations =  student.getRegistrations();
           for (Registration registration : registrations){
               list.add(registration.getCourseOffering().getAcademicBlock());
               count++;
           }
            assertThat(count, is(blockCount));
        }

        var registeredCount = StreamSupport.stream(registered.spliterator(), false).count();

        assertThat(Long.valueOf(list.size()), is(registeredCount));

    }



}
