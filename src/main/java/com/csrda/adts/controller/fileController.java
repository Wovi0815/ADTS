package com.csrda.adts.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class fileController {
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file")MultipartFile file) throws IOException {
		String filePath="src/main/resources/public/"+file.getOriginalFilename();
		BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(filePath));
		outputStream.write(file.getBytes());
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping("/download")
	public ResponseEntity fileDownload() throws Exception {
		FileSystemResource file=new FileSystemResource("src/main/resources/public/新建 Microsoft Word 文档.xml");
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=out_example_resume.xml");
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
		
	}
}
