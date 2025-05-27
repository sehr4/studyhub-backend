package com.studyhub.dto.user;

import com.studyhub.constant.RoleConstant;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private RoleConstant role;
}
