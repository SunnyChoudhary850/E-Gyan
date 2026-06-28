package com.egyan.controller;

import com.egyan.entity.CourseMaterial;
import com.egyan.service.CourseMaterialService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@RestController
@RequestMapping("/materials")
public class CourseMaterialController {
    private final CourseMaterialService materialService;

    public CourseMaterialController(CourseMaterialService materialService) {
        this.materialService = materialService;
    }

    // Upload a file to a course
    @PostMapping("/{courseId}")
    public ResponseEntity<String> upload(@PathVariable Long courseId,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam("title") String title,
                                         @RequestParam("fileType") String fileType) throws IOException {
        System.out.println("=== Upload called, courseId: " + courseId + ", title: " + title);
        materialService.uploadMaterial(courseId, file, title, fileType);
        return ResponseEntity.ok("Uploaded successfully");
    }

    // Get all materials for a course
    @GetMapping("/{courseId}")
    public List<CourseMaterial> getMaterials(@PathVariable Long courseId) {
        return materialService.getMaterialsByCourse(courseId);
    }
    // Delete a material
    @DeleteMapping("/{materialId}")
    public void deleteMaterial(@PathVariable Long materialId) {
        materialService.deleteMaterial(materialId);
    }
    @GetMapping("/download/{materialId}")
    public ResponseEntity<Resource> downloadMaterial(@PathVariable Long materialId) throws IOException {
        CourseMaterial material = materialService.getMaterialById(materialId);
        Path filePath = Paths.get("uploads/" + material.getFilePath());
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + material.getFileName() + "\"")
                .body(resource);
    }
}
