FROM adoptopenjdk:11
EXPOSE 8011
ADD target/ea_final_project.jar ea_final_project.jar
ENTRYPOINT ["java", "-jar", "/ea_final_project-jar"]