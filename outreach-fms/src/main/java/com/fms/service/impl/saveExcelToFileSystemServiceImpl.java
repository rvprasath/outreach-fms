package com.fms.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fms.properties.FilePathProperties;
import com.fms.service.saveExcelToFileSystemService;

@Service(value = "FilesStorageServiceImpl")
public class saveExcelToFileSystemServiceImpl implements saveExcelToFileSystemService {

	@Autowired
	FilePathProperties filePathProperties;
	private Path root = null;

	@Override
	public void save(MultipartFile file) {
		root = Paths.get(filePathProperties.getPath());
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
}