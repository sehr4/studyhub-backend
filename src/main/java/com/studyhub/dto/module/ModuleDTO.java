package com.studyhub.dto.module;

import lombok.Data;

@Data
public class ModuleDTO {

    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer moduleNumber;
}

