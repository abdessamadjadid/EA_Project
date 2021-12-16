package edu.miu.cs.cs544.EAProject.config;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Lazy
@Profile("dev")
@RequiredArgsConstructor
@Configuration
public class DataFeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
    private final AcademicBlockRepository academicBlockRepository;
    private final CourseOfferingRepository courseOfferingRepository;

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Collections.singletonList(new Admin()));

            userRepository.save(admin);
        }

        var event = new RegistrationEvent("November-2021-entry",
                new Audit(LocalDate.of(2021, 12, 13).atStartOfDay(), LocalDate.of(2021, 12, 16).atStartOfDay()));

        var decemberBlock = new AcademicBlock("2021-12A-12D", "December 2021", Semester.WINTER, LocalDate.of(2021, 12, 1), LocalDate.of(2021, 12, 28));
        var januaryBlock = new AcademicBlock("2022-01A-01D", "January 2021", Semester.WINTER, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 28));
        var marchBlock = new AcademicBlock("2022-03A-03D", "March 2021", Semester.SPRING, LocalDate.of(2022, 3, 1), LocalDate.of(2022, 3, 28));

        var mpp = new RegistrationGroup("MPP", Set.of(decemberBlock, januaryBlock, marchBlock), event);

        event.addGroup(mpp);

        var li = new Faculty("Mei Li", "mei.li@email.cok", "Assistant Professor");
        var salek = new Faculty("Payman Salek", "payman.salek@email.com", "Professor");
        var guthrie = new Faculty("Gregory Guthrie", "gregory.guthrie@email.com", "Professor");

        var ea = new Course("CS544", "Enterprise Architecture",
                "This course focuses on teaching the principles and practices used when developing larger scale enterprise applications");
        var algorithms = new Course("CS435", "Algorithms",
                "This course presents methods for analyzing the efficiency of algorithms (including worst-case and average-case analysis) and introduces a variety of known, highly efficient algorithms");
        var asd = new Course("CS525", "Advanced Software development",
                "This course considers the current methods and practices for good design of software systems");

        var adam = new Student("61-123", "Adam", "adam@email.com");
        var eve = new Student("61-124", "Eve", "eve@email.com");
        var abel = new Student("61-125", "Abel", "abel@email.com");

        adam.addRegistrationGroup(mpp);
        eve.addRegistrationGroup(mpp);
        abel.addRegistrationGroup(mpp);

        var courseOfferings = List.of(
                new CourseOffering(2, li, algorithms, decemberBlock, List.of(
                        new RegistrationRequest(adam, 1),
                        new RegistrationRequest(eve, 1),
                        new RegistrationRequest(abel, 2))
                ),
                new CourseOffering(3, salek, ea, decemberBlock, List.of(
                        new RegistrationRequest(adam, 2),
                        new RegistrationRequest(eve, 3),
                        new RegistrationRequest(abel, 1))
                ),
                new CourseOffering(5, guthrie, asd, decemberBlock, List.of(
                        new RegistrationRequest(adam, 3),
                        new RegistrationRequest(eve, 2),
                        new RegistrationRequest(abel, 3))
                ),

                new CourseOffering(2, li, algorithms, januaryBlock, List.of(
                        new RegistrationRequest(adam, 2),
                        new RegistrationRequest(eve, 2),
                        new RegistrationRequest(abel, 1))
                ),
                new CourseOffering(3, guthrie, asd, januaryBlock, List.of(
                        new RegistrationRequest(adam, 1),
                        new RegistrationRequest(eve, 1),
                        new RegistrationRequest(abel, 2))
                ),

                new CourseOffering(5, guthrie, algorithms, marchBlock, List.of(
                        new RegistrationRequest(adam, 1),
                        new RegistrationRequest(eve, 1),
                        new RegistrationRequest(abel, 1))
                ),
                new CourseOffering(2, salek, asd, marchBlock, List.of(
                        new RegistrationRequest(adam, 2),
                        new RegistrationRequest(eve, 2),
                        new RegistrationRequest(abel, 2))
                )
        );

        eventRepository.save(event);
        studentRepository.saveAll(List.of(adam, eve, abel));
        academicBlockRepository.flush();
        courseOfferingRepository.saveAll(courseOfferings);
    }
}
