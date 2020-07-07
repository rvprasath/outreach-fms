package com.fms.service;

import org.springframework.web.multipart.MultipartFile;

public interface saveExcelToFileSystemService {

	public void save(MultipartFile file);

}
