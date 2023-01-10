package com.picture_publishing.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class FileUploadUtil {

    public static void save(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadedPath = getOrCreateUploadedDirectoryIfNotExists(uploadDir);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path path = uploadedPath.resolve(fileName);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }

    }

    private static Path getOrCreateUploadedDirectoryIfNotExists(String uploadDir) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        return uploadPath;

    }

}