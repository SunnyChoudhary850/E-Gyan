package com.egyan.service;

import com.egyan.entity.Course;
import com.egyan.entity.User;
import com.egyan.repository.CourseRepository;
import com.egyan.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public CourseService(CourseRepository courseRepository,
                         UserRepository userRepository,
                         EmailService emailService) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public Course createCourse(Course course, Long teacherId) {
        Optional<User> teacherOptional = userRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            User teacher = teacherOptional.get();
            if (!"FACULTY".equalsIgnoreCase(teacher.getRole())) {
                throw new RuntimeException("Only FACULTY can create courses");
            }
            course.setTeacher(teacher);
            Course saved = courseRepository.save(course);
            emailService.sendCourseCreatedConfirmation(
                    teacher.getEmail(), teacher.getName(), saved.getTitle()
            );
            return saved;
        } else {
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
        Course saved = courseRepository.save(course);

        emailService.sendEnrollmentConfirmation(
                student.getEmail(),
                student.getName(),
                course.getTitle(),
                course.getTeacher().getName()
        );

        return saved;
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

    public void deleteCourseByAdmin(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }
    public Course updateCourse(Long courseId, Long teacherId, Course updatedCourse) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        if (!course.getTeacher().getId().equals(teacherId)) {
            throw new RuntimeException("Unauthorized");
        }
        if (updatedCourse.getDescription() != null) {
            course.setDescription(updatedCourse.getDescription());
        }
        return courseRepository.save(course);
    }
}