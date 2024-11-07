package com.group9.eventgaze.entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "college_list")
public class CollegeLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long collegeId;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "collegeAddress")
    private String collegeAddress;
}
