package com.flow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fix")
@Getter
@Setter
public class Fix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fix_name", nullable = false, length = 20)
    private String fixName;

    @Column(name = "fix_status", nullable = false)
    private boolean fixStatus;

}
