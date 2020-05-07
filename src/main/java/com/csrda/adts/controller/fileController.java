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
	public void upload(@RequestParam("file")MultipartFile file,String temp) throws IOException {
		String fileName=null;
		if(temp=="temp") {
			fileName="temp.docx";
		}else if(temp=="typeData") {
			fileName="typeDataTemp.docx";
		}else if(temp=="midWare") {
			fileName="midWareTemp.docx";
		}else if(temp=="message") {
			fileName="messageTemp.docx";
		}
//		System.out.println(temp);
//		if(temp.equals("temp")) {
//			fileName="test.docx";
//		}else if(temp.equals("typeData")) {
//			fileName="test1.docx";
//		}else if(temp.equals("midWare")) {
//			fileName="test2.docx";
//		}else if(temp.equals("message")) {
//			fileName="test3.docx";
//		}
		String filePath="src/main/resources/public/temp/"+fileName;
		BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(filePath));
		outputStream.write(file.getBytes());
		outputStream.flush();
		outputStream.close();
	} 
	
	@RequestMapping("/downloadA")
	public ResponseEntity fileDownloadA() throws Exception {
		FileSystemResource file=new FileSystemResource("src/main/resources/public/output/"+"output"+".docx");
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=out.docx");
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
		
	}
	
	@RequestMapping("/downloadT")
	public ResponseEntity fileDownloadT() throws Exception {
		FileSystemResource file=new FileSystemResource("src/main/resources/public/output/"+"typeData"+".docx");
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=out.docx");
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
		
	}
	
	@RequestMapping("/downloadM")
	public ResponseEntity fileDownloadM() throws Exception {
		FileSystemResource file=new FileSystemResource("src/main/resources/public/output/"+"midWare"+".docx");
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=out.docx");
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
		
	}
	
	@RequestMapping("/downloadS")
	public ResponseEntity fileDownloadS() throws Exception {
		FileSystemResource file=new FileSystemResource("src/main/resources/public/output/"+"message"+".docx");
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=out.docx");
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
		
	}
}
