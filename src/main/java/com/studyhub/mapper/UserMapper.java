package com.studyhub.mapper;

import com.studyhub.dto.UserDTO;
import com.studyhub.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between User entities and UserDTOs.
 */
@Component
public class UserMapper {

    // Logger to print messages to the console for understanding mapping operations
    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDTO
     */
    public UserDTO toDTO(User user) {
        logger.debug("Converting User entity to UserDTO: {}", user);
        if (user == null) {
            logger.warn("User entity is null, returning null UserDTO");
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        logger.info("Converted User entity to UserDTO: {}", userDTO);
        return userDTO;
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return the corresponding User entity
     */
    public User toEntity(UserDTO userDTO) {
        logger.debug("Converting UserDTO to User entity: {}", userDTO);
        if (userDTO == null) {
            logger.warn("UserDTO is null, returning null User entity");
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        // 'Password' is not set here as it's handled separately (e.g., during registration)

        logger.info("Converted UserDTO to User entity: {}", user);
        return user;
    }
}