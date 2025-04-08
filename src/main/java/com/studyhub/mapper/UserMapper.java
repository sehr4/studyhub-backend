package com.studyhub.mapper;

import com.studyhub.dto.UserDTO;
import com.studyhub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
//import org.springframework.stereotype.Component;

// UserMapper for converting between User and UserDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    // Converts a User entity to a UserDTO
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

//    public UserDTO toDTO(User user) {
//        if (user == null) {
//            return null;
//        }
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setFirstName(user.getFirstName());
//        userDTO.setLastName(user.getLastName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setRole(user.getRole());
//
//        return userDTO;
//    }

    // Converts a UserDTO to a User entity
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);

//    public User toEntity(UserDTO userDTO) {
//        if (userDTO == null) {
//            return null;
//        }
//
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmail(userDTO.getEmail());
//        user.setRole(userDTO.getRole());
//        // 'Password' is not set here, handled separately during registration
//
//        return user;
//    }


    @Mapping(target = "password", ignore = true)
    void updateEntityFromDTO(UserDTO userDTO, @MappingTarget User user);
}
