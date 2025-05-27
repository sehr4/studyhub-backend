package com.studyhub.mapper;

import com.studyhub.constant.Grade;
import com.studyhub.dto.submission.SubmissionDTO;
import com.studyhub.dto.submission.SubmissionUpdateDTO;
import com.studyhub.model.Submission;
import org.mapstruct.*;

import java.util.List;

// SubmissionMapper for converting between Submission and SubmissionDTO
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubmissionMapper {

    @Mapping(target = "assignmentId", source = "assignment.id")
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "grade", source = "grade", qualifiedByName = "gradeToString")
    SubmissionDTO toDTO(Submission submission);

    List<SubmissionDTO> toDTOList(List<Submission> submissions);

    @Mapping(target = "assignment", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "submittedFileContent", ignore = true)
    @Mapping(target = "submittedFileName", ignore = true)
    @Mapping(target = "submissionDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "grade", source = "grade", qualifiedByName = "stringToGrade")
    void updateSubmissionFromDTO(SubmissionUpdateDTO submissionUpdateDTO, @MappingTarget Submission submission);

    @Named("gradeToString")
    default String gradeToString(Grade grade) {
        return grade != null ? grade.name() : null;
    }

    @Named("stringToGrade")
    default Grade stringToGrade(String grade) {
        return grade != null ? Grade.fromString(grade) : null;
    }
}
