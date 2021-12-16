package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.dto.*;
import edu.miu.cs.cs544.EAProject.repository.StudentRegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import edu.miu.cs.cs544.EAProject.service.CourseService;
import edu.miu.cs.cs544.EAProject.service.FacultyService;
import edu.miu.cs.cs544.EAProject.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    @Autowired
    private StudentRegistrationRepository repository;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AcademicBlockService blockService;

    @Override
    public List<StudentRegistrationDto> getRegistrationListByStudentId(Integer id) {
        List<StudentRegistrationDto> dtos = new ArrayList<>();
        Student student = repository.getById(id);
        List<CourseOffering> offerings = student.getCourseOfferings().stream().toList();
        for (CourseOffering offering : offerings) {
            Faculty faculty = facultyService.getFaultyById(offering.getFaculty().getId());
            Course course = courseService.getCourseById(offering.getCourse().getId());
            AcademicBlock block = blockService.getAcademicBlockById(offering.getAcademicBlock().getId());
            StudentRegistrationDto dto = new StudentRegistrationDto(block.getName(),
                    course.getCode(), course.getName(), faculty.getName(), block.getTimespan().getCreatedDate());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public StudentRegistrationEventGroupDto getRegistrationListDto(Integer id) {
        Student student = repository.getById(id);

        StudentRegistrationEventGroupDto dto = new StudentRegistrationEventGroupDto();
        dto.setStudentName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRegistrationGroupDtos(convertRegistrationGroupDto(student.getRegistrationGroups().stream().toList()));

        return dto;
    }

    private List<RegistrationGroupDto> convertRegistrationGroupDto(List<RegistrationGroup> groups) {
        List<RegistrationGroupDto> groupDtos = new ArrayList<>();
        for (RegistrationGroup group : groups) {
            RegistrationGroupDto groupDto = new RegistrationGroupDto();
            groupDto.setName(group.getName());
            groupDto.setId(group.getId());
            RegistrationEvent event = group.getRegistrationEvent();
            groupDto.setRegistrationEventDto(new RegistrationEventDto(event.getId(), event.getName(),
                    event.getStartEndDate().getCreatedDate(), event.getStartEndDate().getModifiedDate()));
            groupDto.setAcademicBlockDtos(convertAcademicBlockDto(group.getAcademicBlocks().stream().toList()));
            groupDtos.add(groupDto);
        }
        return groupDtos;
    }

    private List<AcademicBlockDto> convertAcademicBlockDto(List<AcademicBlock> blocks) {
        List<AcademicBlockDto> blockDtos = new ArrayList<>();
        for (AcademicBlock block : blocks) {
            AcademicBlockDto blockDto = new AcademicBlockDto();
            blockDto.setId(blockDto.getId());
            blockDto.setCode(block.getCode());
            blockDto.setName(block.getName());
            blockDto.setSemester(block.getSemester());
            blockDto.setStartDate(block.getAudit().getCreatedDate());
            blockDto.setEndDate(block.getAudit().getModifiedDate());
            blockDto.setCourseOfferingDtos(convertCourseOfferingDto(block.getCourseOfferings().stream().toList(), block.getId()));
            blockDtos.add(blockDto);
        }
        return blockDtos;
    }

    private List<CourseOfferingDto> convertCourseOfferingDto(List<CourseOffering> offerings, int blockId) {
        List<CourseOfferingDto> courseOfferingDtos = new ArrayList<>();
        for (CourseOffering offering : offerings) {
            Course course = offering.getCourse();
            Faculty faculty = offering.getFaculty();

            CourseOfferingDto courseOfferingDto = new CourseOfferingDto();
            courseOfferingDto.setCourseOfferingCode(offering.getCode());
            courseOfferingDto.setCapacity(offering.getCapacity());
            courseOfferingDto.setFacultyInitials(offering.getFacultyInitials());
            courseOfferingDto.setCourseId(course.getId());
            courseOfferingDto.setAcademicBlockId(blockId);
            courseOfferingDto.setCourseCode(course.getCode());
            courseOfferingDto.setCourseName(course.getName());
            courseOfferingDto.setFacultyId(faculty.getId());
            courseOfferingDto.setFacultyName(faculty.getName());

            courseOfferingDtos.add(courseOfferingDto);
        }
        return courseOfferingDtos;
    }
}
