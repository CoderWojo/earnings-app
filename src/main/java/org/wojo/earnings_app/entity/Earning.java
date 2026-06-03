package org.wojo.earnings_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Earning {
    @Id
//    ta kolumna jest generowana przez persistence provider . nie używaj tej kolumny w insert przy save()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

