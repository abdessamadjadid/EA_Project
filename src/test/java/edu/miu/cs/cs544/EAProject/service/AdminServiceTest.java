package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Autowired
    EventService eventService;


    User admin = new User();
    User user = new User();

    @Before
    public void initData(){
        //Create user
        admin.addRole(new Admin());

        Student student = new Student();
        user.addRole(student);
        user.setUsername("Raymond");

        RegistrationRequest request = new RegistrationRequest();

        CourseOffering ea =   new CourseOffering();
        ea.setCode("CS544");
        ea.setCapacity(4);

        request.setCourseOffering(ea);
        request.setPriority(1);
        student.addRequest(request);

    }

    @Test
    public void TestCourseAssignment(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();
        audit.setModifiedDate(audit.getModifiedDate().plusDays(5));
        RegistrationGroup[] groups = {
                new RegistrationGroup("FPP"),
                new RegistrationGroup("MPP"),
        };
        //
        Collection<AcademicBlock> academicBlocks = new ArrayList<>();
        for(int i =0; i < 8; i++){
            AcademicBlock block =   new AcademicBlock("Code" + i, "Block" + i);
            academicBlocks.add(block);
            block.setRegistrationGroups(Arrays.asList(groups));
        }
        //
        for(int i =0; i < groups.length; i++){
                groups[i].setAcademicBlocks(academicBlocks);
        }

        Collection<Student> studentgroup1 = new ArrayList<>();
        Collection<Student> studentgroup2 = new ArrayList<>();
        for(int i = 0; i < 40; i ++){
            if(i % 2 == 0){
                studentgroup1.add(new Student("student" + i, "name" + i, "stu@miu.edu"));
            }else{
                studentgroup2.add(new Student("student" + i, "name" + i, "stu@miu.edu"));
            }
        }
        groups[0].setStudents(studentgroup1);
        groups[1].setStudents(studentgroup2);
        //

       var list1 = studentgroup1.stream().collect(Collectors.toList());
       var list2 = studentgroup2.stream().collect(Collectors.toList());





        event.setName("Entry-2021");
        event.setStartEndDate(audit);
        event.setRegistrationGroups(Arrays.asList(groups));

        eventService.createEvent(event);

        adminService.processRegistration(event.getId());

        //assertThat(event.getName(), is("Entry-2021"));
       // assertThat(event.getStatus(), is(EventStatus.OPEN));

    }



}
