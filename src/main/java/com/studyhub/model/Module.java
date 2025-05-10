package com.studyhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Module entity, maps to 'modules' in the db
@Data
@Entity
@Table(name = "modules",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "title"}))
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @Column(nullable = false)
    @NotNull(message = "Module number cannot be null")
    private Integer moduleNumber;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Resource> resources = new ArrayList<>();


}
