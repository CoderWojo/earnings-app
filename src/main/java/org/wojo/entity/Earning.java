package org.wojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class Earning {
    private int id;

    private Date lesson_date;

    private String student_name;

    private int amount;

    private String subject;


    public Earning(Date lesson_date, String student_name, int amount, String subject) {
        this.lesson_date = lesson_date;
        this.student_name = student_name;
        this.amount = amount;
        this.subject = subject;
    }
}

