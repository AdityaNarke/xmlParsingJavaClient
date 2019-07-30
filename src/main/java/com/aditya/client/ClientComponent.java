package com.aditya.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.PostConstruct;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;;

@Component
public class ClientComponent {
	
	@PostConstruct
	public void sendXmlPayload() throws IOException {
		
		  HttpHeaders headers = new HttpHeaders(); //
		  headers.setContentType(MediaType.MULTIPART_FORM_DATA); 
		  MultiValueMap<String, Object> body = new LinkedMultiValueMap<>(); 
		 
		  body.add("file", new  FileSystemResource(new File("employee_test.xml")));
		  
		  HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		  
		  sendRestRequest(requestEntity);
		  
		
	}
	
	

	public void sendRestRequest(HttpEntity<MultiValueMap<String, Object>> requestEntity) {
		  RestTemplate restTemplate = new RestTemplate(); 
		  ResponseEntity<ResponseInfo> response = restTemplate.postForEntity("http://localhost:8080/employee/save",
		  requestEntity, ResponseInfo.class);
		  System.out.println(response.getBody().toString());
	}
	
	public static FileSystemResource getUserFileResource() throws IOException {
        //todo replace tempFile with a real file
        Path tempFile = Files.createTempFile("employee_test", ".xml");
        System.out.println("uploading: " + tempFile);
        File file = new File("employee_test.xml");
        //to upload in-memory bytes use ByteArrayResource instead
        return new FileSystemResource(file);
        
    }
	
	public void testRestCall() {
		RestTemplate restTemplate = new RestTemplate();
		String test = restTemplate.getForObject("http://localhost:8080/employee/getName", String.class);
		System.out.println(test);
	}

}
