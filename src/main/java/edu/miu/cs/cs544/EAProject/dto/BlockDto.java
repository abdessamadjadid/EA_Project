package edu.miu.cs.cs544.EAProject.dto;

import edu.miu.cs.cs544.EAProject.domain.Semester;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class BlockDto {

    private String code;
    private String name;
    private Semester semester;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getCode() {
        long duration = ChronoUnit.WEEKS.between(startDate, endDate);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startDate.getYear())
                .append("-")
                .append(startDate.getMonthValue()).append("A")
                .append("-")
                .append(endDate.getMonthValue()).append(toAlphabetic((int) duration));
        return stringBuilder.toString();
    }

    public String toAlphabetic(int i) {
        int quot = i / 26;
        int rem = i % 26;
        char letter = (char) ((int) 'A' + rem);
        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot - 1) + letter;
        }
    }

}
