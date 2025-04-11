-- Insert Users (Passwords hashed with BCrypt for 'password123')
INSERT INTO users (first_name, last_name, email, role, password) VALUES
                                                                     ('John', 'Doe', 'john.doe@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Jane', 'Smith', 'jane.smith@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Admin', 'User', 'admin@example.com', 'ADMIN', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Alice', 'Brown', 'alice.brown@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Bob', 'Johnson', 'bob.johnson@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Charlie', 'Davis', 'charlie.davis@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('David', 'Wilson', 'david.wilson@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Eve', 'Martinez', 'eve.martinez@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Michael', 'Garcia', 'michael.garcia@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Olivia', 'Rodriguez', 'olivia.rodriguez@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Liam', 'Williams', 'liam.williams@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Sophia', 'Jones', 'sophia.jones@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Noah', 'Brown', 'noah.brown@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Isabella', 'Garcia', 'isabella.garcia@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Ethan', 'Rodriguez', 'ethan.rodriguez@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Emma', 'Taylor', 'emma.taylor@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('James', 'Anderson', 'james.anderson@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Ava', 'Thomas', 'ava.thomas@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('William', 'Jackson', 'william.jackson@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Mia', 'White', 'mia.white@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Alexander', 'Harris', 'alexander.harris@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Charlotte', 'Martin', 'charlotte.martin@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Daniel', 'Thompson', 'daniel.thompson@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Amelia', 'Moore', 'amelia.moore@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Henry', 'Lee', 'henry.lee@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Harper', 'Clark', 'harper.clark@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Benjamin', 'Lewis', 'benjamin.lewis@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Evelyn', 'Walker', 'evelyn.walker@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Lucas', 'Hall', 'lucas.hall@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Scarlett', 'Allen', 'scarlett.allen@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Mason', 'Young', 'mason.young@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Lily', 'King', 'lily.king@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Logan', 'Wright', 'logan.wright@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Zoe', 'Scott', 'zoe.scott@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Jackson', 'Green', 'jackson.green@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Aria', 'Baker', 'aria.baker@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Grayson', 'Adams', 'grayson.adams@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Layla', 'Nelson', 'layla.nelson@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Gabriel', 'Hill', 'gabriel.hill@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Chloe', 'Ramirez', 'chloe.ramirez@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Samuel', 'Campbell', 'samuel.campbell@example.com', 'INSTRUCTOR', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Penelope', 'Mitchell', 'penelope.mitchell@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Owen', 'Roberts', 'owen.roberts@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Abigail', 'Carter', 'abigail.carter@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Elijah', 'Phillips', 'elijah.phillips@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Hannah', 'Evans', 'hannah.evans@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Matthew', 'Turner', 'matthew.turner@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Sofia', 'Parker', 'sofia.parker@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri'),
                                                                     ('Julian', 'Edwards', 'julian.edwards@example.com', 'STUDENT', '$2a$10$GBaAJ7H122Q2XaseNMk2LOYGaiHdmm0wRGrxnYRuABwRiieokrVri');

-- Insert Courses
INSERT INTO courses (code, department, title, credits, description, start_date, end_date) VALUES
                                                                                              ('CS101A', 'Computer Science', 'Intro to Programming', 3, 'Basics of coding using Python', '2025-09-01', '2025-12-15'),
                                                                                              ('CS102', 'Computer Science', 'Web Development', 3, 'HTML, CSS, and JavaScript basics', '2025-09-01', '2025-12-15'),
                                                                                              ('CS201', 'Computer Science', 'Data Structures', 4, 'Fundamental data structures', '2026-01-15', '2026-05-01'),
                                                                                              ('CS301', 'Computer Science', 'Database Systems', 4, 'Database design and SQL', '2026-09-01', '2026-12-15'),
                                                                                              ('CS401', 'Computer Science', 'Operating Systems', 4, 'OS concepts and design', '2026-09-01', '2026-12-15'),
                                                                                              ('MATH101', 'Mathematics', 'Algebra I', 3, 'Linear equations and inequalities', '2025-09-01', '2025-12-15'),
                                                                                              ('MATH201', 'Mathematics', 'Calculus I', 4, 'Differential calculus', '2025-09-01', '2025-12-15'),
                                                                                              ('MATH202', 'Mathematics', 'Calculus II', 4, 'Integral calculus', '2026-01-15', '2026-05-01'),
                                                                                              ('MATH301', 'Mathematics', 'Linear Algebra', 3, 'Vectors and matrices', '2026-09-01', '2026-12-15'),
                                                                                              ('MATH401', 'Mathematics', 'Probability', 3, 'Probability theory basics', '2026-09-01', '2026-12-15'),
                                                                                              ('ENG101', 'English', 'Freshman Composition', 3, 'Academic writing skills', '2025-09-01', '2025-12-15'),
                                                                                              ('ENG201', 'English', 'American Literature', 3, 'Survey of American lit', '2026-01-15', '2026-05-01'),
                                                                                              ('ENG301', 'English', 'Creative Writing', 3, 'Fiction and poetry writing', '2026-09-01', '2026-12-15'),
                                                                                              ('HIST101', 'History', 'World History I', 3, 'Ancient to medieval history', '2025-09-01', '2025-12-15'),
                                                                                              ('HIST201', 'History', 'European History', 3, 'Renaissance to modern era', '2026-01-15', '2026-05-01'),
                                                                                              ('HIST301', 'History', 'US History', 3, 'Colonial to present', '2026-09-01', '2026-12-15'),
                                                                                              ('BIO101', 'Biology', 'Introductory Biology', 4, 'Cell structure and function', '2025-09-01', '2025-12-15'),
                                                                                              ('BIO201', 'Biology', 'Genetics', 4, 'Heredity and gene function', '2026-01-15', '2026-05-01'),
                                                                                              ('BIO301', 'Biology', 'Ecology', 4, 'Ecosystems and interactions', '2026-09-01', '2026-12-15'),
                                                                                              ('PHYS101', 'Physics', 'Introductory Physics', 4, 'Mechanics and motion', '2025-09-01', '2025-12-15'),
                                                                                              ('PHYS201', 'Physics', 'Electricity and Magnetism', 4, 'EM fundamentals', '2026-01-15', '2026-05-01'),
                                                                                              ('CHEM101', 'Chemistry', 'General Chemistry I', 4, 'Atomic structure and bonding', '2025-09-01', '2025-12-15'),
                                                                                              ('CHEM201', 'Chemistry', 'Organic Chemistry', 4, 'Carbon compounds', '2026-01-15', '2026-05-01'),
                                                                                              ('ECON101', 'Economics', 'Microeconomics', 3, 'Supply and demand basics', '2025-09-01', '2025-12-15'),
                                                                                              ('ECON201', 'Economics', 'Macroeconomics', 3, 'National economy overview', '2026-01-15', '2026-05-01'),
                                                                                              ('PSY101', 'Psychology', 'Intro to Psychology', 3, 'Mind and behavior basics', '2025-09-01', '2025-12-15'),
                                                                                              ('SOC101', 'Sociology', 'Intro to Sociology', 3, 'Society and culture', '2025-09-01', '2025-12-15'),
                                                                                              ('ART101', 'Art', 'Art History', 3, 'Major art movements', '2025-09-01', '2025-12-15'),
                                                                                              ('MUS101', 'Music', 'Music Theory', 3, 'Fundamentals of music', '2025-09-01', '2025-12-15'),
                                                                                              ('PHIL101', 'Philosophy', 'Intro to Philosophy', 3, 'Key philosophical concepts', '2025-09-01', '2025-12-15');

-- Link Instructors to Courses (Individual statements)
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'jane.smith@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'jane.smith@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'charlie.davis@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'liam.williams@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CS401'), (SELECT id FROM users WHERE email = 'ethan.rodriguez@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'MATH101'), (SELECT id FROM users WHERE email = 'william.jackson@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'MATH201'), (SELECT id FROM users WHERE email = 'william.jackson@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'MATH202'), (SELECT id FROM users WHERE email = 'daniel.thompson@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'MATH301'), (SELECT id FROM users WHERE email = 'daniel.thompson@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'MATH401'), (SELECT id FROM users WHERE email = 'benjamin.lewis@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'ENG101'), (SELECT id FROM users WHERE email = 'logan.wright@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'ENG201'), (SELECT id FROM users WHERE email = 'logan.wright@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'ENG301'), (SELECT id FROM users WHERE email = 'samuel.campbell@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'HIST101'), (SELECT id FROM users WHERE email = 'jane.smith@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'HIST201'), (SELECT id FROM users WHERE email = 'charlie.davis@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'HIST301'), (SELECT id FROM users WHERE email = 'liam.williams@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'BIO101'), (SELECT id FROM users WHERE email = 'ethan.rodriguez@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'BIO201'), (SELECT id FROM users WHERE email = 'ethan.rodriguez@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'BIO301'), (SELECT id FROM users WHERE email = 'william.jackson@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'daniel.thompson@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'PHYS201'), (SELECT id FROM users WHERE email = 'benjamin.lewis@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CHEM101'), (SELECT id FROM users WHERE email = 'benjamin.lewis@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'CHEM201'), (SELECT id FROM users WHERE email = 'logan.wright@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'ECON101'), (SELECT id FROM users WHERE email = 'samuel.campbell@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'ECON201'), (SELECT id FROM users WHERE email = 'samuel.campbell@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'PSY101'), (SELECT id FROM users WHERE email = 'jane.smith@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'SOC101'), (SELECT id FROM users WHERE email = 'charlie.davis@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'ART101'), (SELECT id FROM users WHERE email = 'liam.williams@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'MUS101'), (SELECT id FROM users WHERE email = 'ethan.rodriguez@example.com'));
INSERT INTO course_instructor (course_id, instructor_id) VALUES
    ((SELECT id FROM courses WHERE code = 'PHIL101'), (SELECT id FROM users WHERE email = 'logan.wright@example.com'));

-- Enroll Students in Courses (Sample enrollments for variety)
INSERT INTO course_enrollment (course_id, student_id) VALUES
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'john.doe@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH201'), (SELECT id FROM users WHERE email = 'john.doe@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG101'), (SELECT id FROM users WHERE email = 'john.doe@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'alice.brown@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO101'), (SELECT id FROM users WHERE email = 'alice.brown@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'alice.brown@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'bob.johnson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH101'), (SELECT id FROM users WHERE email = 'bob.johnson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST101'), (SELECT id FROM users WHERE email = 'bob.johnson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'david.wilson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH202'), (SELECT id FROM users WHERE email = 'david.wilson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG201'), (SELECT id FROM users WHERE email = 'david.wilson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'eve.martinez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CHEM101'), (SELECT id FROM users WHERE email = 'eve.martinez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ECON101'), (SELECT id FROM users WHERE email = 'eve.martinez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'michael.garcia@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH301'), (SELECT id FROM users WHERE email = 'michael.garcia@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO201'), (SELECT id FROM users WHERE email = 'michael.garcia@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG201'), (SELECT id FROM users WHERE email = 'olivia.rodriguez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST201'), (SELECT id FROM users WHERE email = 'olivia.rodriguez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PSY101'), (SELECT id FROM users WHERE email = 'olivia.rodriguez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'sophia.jones@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH201'), (SELECT id FROM users WHERE email = 'sophia.jones@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG101'), (SELECT id FROM users WHERE email = 'sophia.jones@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'noah.brown@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO101'), (SELECT id FROM users WHERE email = 'noah.brown@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST101'), (SELECT id FROM users WHERE email = 'noah.brown@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'isabella.garcia@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH202'), (SELECT id FROM users WHERE email = 'isabella.garcia@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG201'), (SELECT id FROM users WHERE email = 'isabella.garcia@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'emma.taylor@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'emma.taylor@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CHEM101'), (SELECT id FROM users WHERE email = 'emma.taylor@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'james.anderson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH101'), (SELECT id FROM users WHERE email = 'james.anderson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ECON101'), (SELECT id FROM users WHERE email = 'james.anderson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'ava.thomas@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO201'), (SELECT id FROM users WHERE email = 'ava.thomas@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST201'), (SELECT id FROM users WHERE email = 'ava.thomas@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'mia.white@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH301'), (SELECT id FROM users WHERE email = 'mia.white@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG301'), (SELECT id FROM users WHERE email = 'mia.white@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'alexander.harris@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS201'), (SELECT id FROM users WHERE email = 'alexander.harris@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CHEM201'), (SELECT id FROM users WHERE email = 'alexander.harris@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'charlotte.martin@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH101'), (SELECT id FROM users WHERE email = 'charlotte.martin@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PSY101'), (SELECT id FROM users WHERE email = 'charlotte.martin@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'amelia.moore@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO201'), (SELECT id FROM users WHERE email = 'amelia.moore@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ECON201'), (SELECT id FROM users WHERE email = 'amelia.moore@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'henry.lee@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH401'), (SELECT id FROM users WHERE email = 'henry.lee@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST301'), (SELECT id FROM users WHERE email = 'henry.lee@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'harper.clark@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'harper.clark@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'SOC101'), (SELECT id FROM users WHERE email = 'harper.clark@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'evelyn.walker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH201'), (SELECT id FROM users WHERE email = 'evelyn.walker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ART101'), (SELECT id FROM users WHERE email = 'evelyn.walker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'lucas.hall@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO301'), (SELECT id FROM users WHERE email = 'lucas.hall@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MUS101'), (SELECT id FROM users WHERE email = 'lucas.hall@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'scarlett.allen@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH301'), (SELECT id FROM users WHERE email = 'scarlett.allen@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHIL101'), (SELECT id FROM users WHERE email = 'scarlett.allen@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS401'), (SELECT id FROM users WHERE email = 'mason.young@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS201'), (SELECT id FROM users WHERE email = 'mason.young@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CHEM201'), (SELECT id FROM users WHERE email = 'mason.young@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'lily.king@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH101'), (SELECT id FROM users WHERE email = 'lily.king@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG101'), (SELECT id FROM users WHERE email = 'lily.king@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'zoe.scott@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO101'), (SELECT id FROM users WHERE email = 'zoe.scott@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST101'), (SELECT id FROM users WHERE email = 'zoe.scott@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'jackson.green@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH202'), (SELECT id FROM users WHERE email = 'jackson.green@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG201'), (SELECT id FROM users WHERE email = 'jackson.green@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'aria.baker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO201'), (SELECT id FROM users WHERE email = 'aria.baker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ECON201'), (SELECT id FROM users WHERE email = 'aria.baker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS401'), (SELECT id FROM users WHERE email = 'grayson.adams@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'grayson.adams@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CHEM101'), (SELECT id FROM users WHERE email = 'grayson.adams@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'layla.nelson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH201'), (SELECT id FROM users WHERE email = 'layla.nelson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PSY101'), (SELECT id FROM users WHERE email = 'layla.nelson@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'gabriel.hill@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO101'), (SELECT id FROM users WHERE email = 'gabriel.hill@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'SOC101'), (SELECT id FROM users WHERE email = 'gabriel.hill@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'chloe.ramirez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH202'), (SELECT id FROM users WHERE email = 'chloe.ramirez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ART101'), (SELECT id FROM users WHERE email = 'chloe.ramirez@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'penelope.mitchell@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO301'), (SELECT id FROM users WHERE email = 'penelope.mitchell@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MUS101'), (SELECT id FROM users WHERE email = 'penelope.mitchell@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS401'), (SELECT id FROM users WHERE email = 'owen.roberts@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS201'), (SELECT id FROM users WHERE email = 'owen.roberts@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHIL101'), (SELECT id FROM users WHERE email = 'owen.roberts@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'abigail.carter@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH101'), (SELECT id FROM users WHERE email = 'abigail.carter@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG101'), (SELECT id FROM users WHERE email = 'abigail.carter@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS102'), (SELECT id FROM users WHERE email = 'elijah.phillips@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO101'), (SELECT id FROM users WHERE email = 'elijah.phillips@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'HIST101'), (SELECT id FROM users WHERE email = 'elijah.phillips@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS201'), (SELECT id FROM users WHERE email = 'hannah.evans@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH202'), (SELECT id FROM users WHERE email = 'hannah.evans@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ENG201'), (SELECT id FROM users WHERE email = 'hannah.evans@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS301'), (SELECT id FROM users WHERE email = 'matthew.turner@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'BIO201'), (SELECT id FROM users WHERE email = 'matthew.turner@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'ECON201'), (SELECT id FROM users WHERE email = 'matthew.turner@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS401'), (SELECT id FROM users WHERE email = 'sofia.parker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PHYS101'), (SELECT id FROM users WHERE email = 'sofia.parker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CHEM101'), (SELECT id FROM users WHERE email = 'sofia.parker@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'CS101A'), (SELECT id FROM users WHERE email = 'julian.edwards@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'MATH201'), (SELECT id FROM users WHERE email = 'julian.edwards@example.com')),
                                                          ((SELECT id FROM courses WHERE code = 'PSY101'), (SELECT id FROM users WHERE email = 'julian.edwards@example.com'));

-- Bulk Enrollments for Popular Courses
INSERT INTO course_enrollment (course_id, student_id)
SELECT (SELECT id FROM courses WHERE code = 'CS101A'), id
FROM users
WHERE role = 'STUDENT'
  AND id NOT IN (SELECT student_id FROM course_enrollment WHERE course_id = (SELECT id FROM courses WHERE code = 'CS101A'));

INSERT INTO course_enrollment (course_id, student_id)
SELECT (SELECT id FROM courses WHERE code = 'MATH201'), id
FROM users
WHERE role = 'STUDENT'
  AND id NOT IN (SELECT student_id FROM course_enrollment WHERE course_id = (SELECT id FROM courses WHERE code = 'MATH201'));

INSERT INTO course_enrollment (course_id, student_id)
SELECT (SELECT id FROM courses WHERE code = 'ENG101'), id
FROM users
WHERE role = 'STUDENT'
  AND id NOT IN (SELECT student_id FROM course_enrollment WHERE course_id = (SELECT id FROM courses WHERE code = 'ENG101'));

INSERT INTO course_enrollment (course_id, student_id)
SELECT (SELECT id FROM courses WHERE code = 'BIO101'), id
FROM users
WHERE role = 'STUDENT'
  AND id NOT IN (SELECT student_id FROM course_enrollment WHERE course_id = (SELECT id FROM courses WHERE code = 'BIO101'));