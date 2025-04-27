package com.studyhub.dto;

import lombok.Data;

@Data
public class ModuleUpdateDTO {

    private Long id;
    private String title;
    private String description;
    private Integer moduleNumber;
}
