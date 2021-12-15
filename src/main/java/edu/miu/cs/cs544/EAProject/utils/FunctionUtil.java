package edu.miu.cs.cs544.EAProject.utils;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public abstract class FunctionUtil {


    public static Function<RegistrationEvent, List<CourseOffering> > getAllCourseOffering = (event) ->
            event.getRegistrationRequests().stream()
                    .map(request -> request.getCourseOffering())
                    .distinct()
                    .collect(Collectors.toList());


    public static Function<RegistrationEvent,  List<AcademicBlock>> getEventAcademicBlock = (event) ->
           event.getRegistrationGroups()
                   .stream().flatMap(group -> group.getAcademicBlocks().stream())
                   .collect(Collectors.toList());

    public static Function<RegistrationEvent, Map<AcademicBlock, List<CourseOffering>>> getCourseOfferingPerBlock = (event) ->
            getAllCourseOffering.apply(event).stream()
                    .collect(groupingBy(CourseOffering::getAcademicBlock));



    public static Function<AcademicBlock, Map<CourseOffering, Long>> getCourseOfferingCountPerBlock = (block) ->
            block.getCourseOfferings().stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


    public static Function<AcademicBlock, List<CourseOffering>> getCourseOfferingMoreCapacity = (block) ->
                getCourseOfferingCountPerBlock.apply(block).entrySet().stream()
                        .filter(c -> c.getValue() > c.getKey().getCapacity() )
                        .map(k -> k.getKey())
                    .collect(Collectors.toList());


    public static BiFunction<AcademicBlock, Integer, List<CourseOffering>> getCourseOfferingLessCapacity = (block, capacity) ->
            getCourseOfferingCountPerBlock.apply(block).entrySet().stream()
                    .filter(c -> c.getValue() < capacity )
                    .map(k -> k.getKey())
                    .collect(Collectors.toList());



}
