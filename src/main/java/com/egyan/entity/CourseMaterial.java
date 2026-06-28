package com.egyan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String fileName;      // original file name
    private String filePath;      // where it's stored on disk
    private String fileType;      // "VIDEO" or "DOCUMENT"
    private Long fileSize;        // in bytes

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    public Long getCourseId() {
        return course != null ? course.getId() : null;
    }
}
