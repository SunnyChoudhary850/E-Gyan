package com.egyan.service;

import com.egyan.entity.Course;
import com.egyan.entity.User;
import com.egyan.repository.CourseRepository;
import com.egyan.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
@Service
public class ClassName {

    private final DependencyClass dependencyObject;

    // Constructor Injection
    public ClassName(DependencyClass dependencyObject) {
        this.dependencyObject = dependencyObject;
    }

    // Business Method
    public ReturnType methodName(ParameterType param) {
        return dependencyObject.someMethod(param);
    }
}
 */


@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // Constructor Injection
    public CourseService(CourseRepository courseRepository,UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // Business Method

    public Course createCourse(Course course, Long teacherId) {
        //  Find teacher from DB
        Optional<User> teacherOptional = userRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            User teacher = teacherOptional.get();
                // Step 2: Check role
            if (!"FACULTY".equalsIgnoreCase(teacher.getRole())) {
                throw new RuntimeException("Only FACULTY can create courses");
            }
                // Step 3: Assign teacher
            course.setTeacher(teacher);
                // Step 4: Save course
            return courseRepository.save(course);
    }
        else {
            throw new RuntimeException("Teacher not found with id: " + teacherId);
        }
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
            throw new RuntimeException("Only STUDENT can enroll in courses");
        }
        boolean alreadyEnrolled = course.getStudents().stream()
                .anyMatch(s -> s.getId().equals(student.getId()));
        if (alreadyEnrolled) {
            throw new RuntimeException("Student is already enrolled in this course");
        }
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    public List<Course> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }
    public List<Course> getCoursesByStudent(Long studentId) {

        return courseRepository.findByStudentsId(studentId);
    }
    public void deleteCourse(Long courseId, Long teacherId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!course.getTeacher().getId().equals(teacherId)) {
            throw new RuntimeException("Only the course teacher can delete this course");
        }

        courseRepository.delete(course);
    }
}
