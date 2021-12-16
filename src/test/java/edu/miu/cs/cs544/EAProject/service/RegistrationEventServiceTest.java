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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RegistrationEventServiceTest {

    @Autowired
    EventService eventService;

    @Test
    public void TestOpenEvent(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();
        audit.setModifiedDate(audit.getModifiedDate().plusDays(5));

        event.setName("Entry-2021");
        event.setStartEndDate(audit);
        eventService.createEvent(event);

        assertThat(event.getName(), is("Entry-2021"));
        assertThat(event.getStatus(), is(EventStatus.OPEN));

    }

    @Test
    public void TestClosedEvent(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();

        event.setName("Entry-2021");
        event.setStartEndDate(audit);
        eventService.createEvent(event);

        assertThat(event.getName(), is("Entry-2021"));
        assertThat(event.getStatus(), is(EventStatus.CLOSED));

    }

    @Test
    public void TestLatestEvent(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();

        event.setName("May-Entry-2021");
        event.setStartEndDate(audit);
        eventService.createEvent(event);

        RegistrationEvent event2 = new RegistrationEvent();
        Audit audit2 = new Audit();
        event2.setName("August-Entry-2021");
        audit2.setModifiedDate(audit2.getModifiedDate().plusDays(2));
        event2.setStartEndDate(audit2);
        eventService.createEvent(event2);

        RegistrationEvent event3 = new RegistrationEvent();
        Audit audit3 = new Audit();
        event3.setName("November-Entry-2021");
        audit3.setModifiedDate(audit3.getModifiedDate().plusDays(3));
        event3.setStartEndDate(audit3);
        eventService.createEvent(event3);


        assertThat(event.getStatus(), is(EventStatus.CLOSED));
        assertThat(event2.getStatus(), is(EventStatus.OPEN));
        assertThat(eventService.getLatestEvent().getName(), is(event3.getName()));

    }

    @Test
    public void TestAllEvent(){
        Long size = 10L;
        for(int i = 0; i < size; i++){
           eventService.createEvent(new RegistrationEvent());
        }

        assertThat(eventService.getAllEvents(Pageable.ofSize(20)).getTotalElements(), is(size));

    }

    @Test
    public void TestOneEvent(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();

        event.setName("May-Entry-2021");
        event.setStartEndDate(audit);
        eventService.createEvent(event);

        RegistrationEvent event2 = new RegistrationEvent();
        Audit audit2 = new Audit();
        event2.setName("August-Entry-2021");
        audit2.setModifiedDate(audit2.getModifiedDate().plusDays(2));
        event2.setStartEndDate(audit2);
        eventService.createEvent(event2);

        RegistrationEvent event3 = new RegistrationEvent();
        Audit audit3 = new Audit();
        event3.setName("November-Entry-2021");
        audit3.setModifiedDate(audit3.getModifiedDate().plusDays(3));
        event3.setStartEndDate(audit3);
        eventService.createEvent(event3);

        assertThat(eventService.getEventById(event.getId()).getName(), is(event.getName()));
        assertThat(eventService.getEventById(event3.getId()).getStatus(), is(EventStatus.OPEN));

    }

    @Test
    public void TestUpdateEvent(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();

        event.setName("May-Entry-2021");
        event.setStartEndDate(audit);
        eventService.createEvent(event);


        event.setName("August-Entry-2021");
        audit.setModifiedDate(audit.getModifiedDate().plusDays(2));
        event.setStartEndDate(audit);
        RegistrationEvent newEvent  = eventService.updateEvent(event);

        assertThat(eventService.getEventById(newEvent.getId()).getName(), is(event.getName()));
        assertThat(eventService.getEventById(newEvent.getId()).getId(), is(event.getId()));
        assertThat(eventService.getEventById(newEvent.getId()).getStatus(), is(EventStatus.OPEN));


    }

    @Test
    public void TestDeleteEvent(){
        RegistrationEvent event = new RegistrationEvent();
        Audit audit = new Audit();

        event.setName("May-Entry-2021");
        event.setStartEndDate(audit);
        eventService.createEvent(event);

        RegistrationEvent event2 = new RegistrationEvent();
        Audit audit2 = new Audit();
        event2.setName("August-Entry-2021");
        audit2.setModifiedDate(audit2.getModifiedDate().plusDays(2));
        event2.setStartEndDate(audit2);
        eventService.createEvent(event2);

        RegistrationEvent event3 = new RegistrationEvent();
        Audit audit3 = new Audit();
        event3.setName("November-Entry-2021");
        audit3.setModifiedDate(audit3.getModifiedDate().plusDays(3));
        event3.setStartEndDate(audit3);
        eventService.createEvent(event3);

        eventService.deleteEvent(event.getId());

        assertThat(eventService.getAllEvents(Pageable.ofSize(20)).getTotalElements(), is(2L));


    }

    @Test
    public void TestEventAcademicBloc(){
        RegistrationEvent event = new RegistrationEvent();
        RegistrationGroup group = new RegistrationGroup();
        Audit audit = new Audit();
        audit.setModifiedDate(audit.getModifiedDate().plusDays(5));
        //
        group.setName("FPP");

        int size = 8;
        Collection<AcademicBlock> blocks = new ArrayList<>();
        for(int i =0; i < size; i++){
           blocks.add(new AcademicBlock());
        }

        group.setAcademicBlocks(blocks);

        event.setName("Entry-2021");
        event.setStartEndDate(audit);
        event.setRegistrationGroups(Arrays.asList(group));
        eventService.createEvent(event);

        List<AcademicBlock> academicBlockList =  eventService.getEventAcademicBlock(event);

        assertThat(academicBlockList.size(), is(size));

    }


}
