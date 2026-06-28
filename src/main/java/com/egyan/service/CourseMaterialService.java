package com.egyan.service;

import com.egyan.entity.Course;
import com.egyan.entity.CourseMaterial;
import com.egyan.repository.CourseMaterialRepository;
import com.egyan.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class CourseMaterialService {
    private final CourseMaterialRepository materialRepository;
    private final CourseRepository courseRepository;

    // Files will be saved here on your machine
    private final String UPLOAD_DIR = "uploads/";

    public CourseMaterialService(CourseMaterialRepository materialRepository,
            CourseRepository courseRepository) {
        this.materialRepository = materialRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void uploadMaterial(Long courseId, MultipartFile file,
            String title, String fileType) throws IOException {

        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found with id: " + courseId);
        }

        // Create uploads directory properly
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        System.out.println("=== Inserting material with courseId: " + courseId);

        materialRepository.insertMaterial(
                title,
                file.getOriginalFilename(),
                fileName,
                fileType.toUpperCase(),
                file.getSize(),
                courseId);
        System.out.println("=== Insert done");
    }

    public List<CourseMaterial> getMaterialsByCourse(Long courseId) {
        return materialRepository.findByCourse_Id(courseId);
    }

    public void deleteMaterial(Long materialId) {
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
        new File(UPLOAD_DIR + material.getFilePath()).delete();

        materialRepository.delete(material);
    }

    public CourseMaterial getMaterialById(Long materialId) {
        return materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
    }

}
