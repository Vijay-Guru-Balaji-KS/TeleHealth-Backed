package com.vijay.TeleHealth.service;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FileStorageService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Value("${file.upload-dir.certificates}")
	private String certificatesDir;

	@Value("${file.upload-dir.profile-photos}")
	private String profilePhotosDir;

	@Value("${file.upload-dir.medical_history}")
	private String medicalHistoryDir;

	@Value("${file.upload-dir.default")
	private String defaultDummyDir;

	@Value("${file.upload-dir.prescriptions}")
	private String prescriptionDir;

	public String storeFile(MultipartFile file, String subDir) throws IOException {
		String directory = defaultDummyDir;

		if (subDir.equals("certificates"))
			directory = certificatesDir;
		else if (subDir.equals("profile_photos"))
			directory = profilePhotosDir;
		else if (subDir.equals("medical_history"))
			directory = medicalHistoryDir;
		else if (subDir.equals("prescriptions"))
			directory = prescriptionDir;

		Files.createDirectories(Paths.get(directory));

		String fileName = UUID.randomUUID() + "-" + StringUtils.cleanPath(file.getOriginalFilename());

		Path targetPath = Paths.get(directory).resolve(fileName).normalize();
		Files.createDirectories(targetPath.getParent());
		Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

		return "/TeleHealth/uploads/" + subDir + "/" + fileName;
	}
}
