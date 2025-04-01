package com.studyhub.repository;

import com.studyhub.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the User entity.
 * Provides methods to find users by username and email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Logger to print messages to the console for understanding repository operations
    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    /**
     * Finds a user by their email.
     *
     * @param email the email to search for
     * @return an Optional containing the User if found, or empty if not found
     */
    default Optional<User> findByEmail(String email) {
        logger.debug("Finding user by email: {}", email);
        Optional<User> user = findUserByEmail(email);
        if (user.isPresent()) {
            logger.info("Found user with email: {}", email);
        } else {
            logger.info("No user found with email: {}", email);
        }
        return user;
    }

    /**
     * Internal method for finding a user by email.
     * This method is called by the default findByEmail method to allow logging.
     *
     * @param email the email to search for
     * @return an Optional containing the User if found, or empty if not found
     */
    Optional<User> findUserByEmail(String email);

//    /**
//     * Finds a user by their id.
//     *
//     * @param id the id to search for
//     * @return an Optional containing the User if found, or empty if not found
//     */
//    default Optional<User> findById(Long id) {
//        logger.debug("Finding user by id: {}", id);
//        Optional<User> user = findUserById(id);
//        if (user.isPresent()) {
//            logger.info("Found user with id: {}", id);
//        } else {
//            logger.info("No user found with id: {}", id);
//        }
//        return user;
//    }
//
//    /**
//     * Internal method for finding a user by username.
//     * This method is called by the default findByUsername method to allow logging.
//     *
//     * @param id the id to search for
//     * @return an Optional containing the User if found, or empty if not found
//     */
//    Optional<User> findUserById(Long id);
}