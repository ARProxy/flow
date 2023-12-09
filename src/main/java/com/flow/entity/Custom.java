package com.flow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "custom")
@Getter
@Setter
public class Custom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fix_extension_id", referencedColumnName = "id")
    private Fix fix;

    @Column(name = "custom_extension_name", nullable = false, length = 20)
    private String customName;
}
