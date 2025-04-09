package com.studyhub.mapper;

import com.studyhub.dto.UserDTO;
import com.studyhub.dto.UserUpdateDTO;
import com.studyhub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

// UserMapper for converting between User and UserDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    // Converts a User entity to a UserDTO
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

    // Converts a UserDTO to a User entity
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);

    // Set persistence from UserUpdateDTO to User if not 'null' for each data field
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDTO(UserUpdateDTO userDTO, @MappingTarget User user);
}
