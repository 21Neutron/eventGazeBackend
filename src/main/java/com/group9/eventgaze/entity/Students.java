package com.group9.eventgaze.entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users users;

}
