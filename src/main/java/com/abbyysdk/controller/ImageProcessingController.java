package com.abbyysdk.controller;



import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.ResponseData;
import exception.ImageProcessingException;
import jakarta.annotation.Resource;
import service.ImageProcessingService;

@JsonIgnoreProperties(ignoreUnknown = true)
@Resource
@CrossOrigin("http://localhost:4200")
@RestController
public class ImageProcessingController {

	private final ImageProcessingService imageProcessingService;

	public ImageProcessingController() {
		super();

		this.imageProcessingService = new ImageProcessingService();
	}

	public ImageProcessingController(ImageProcessingService imageProcessingService) {
		this.imageProcessingService = imageProcessingService;
	}

	@PostMapping("/data")
	public ResponseEntity<?> fetchAbbyyData(@RequestParam("images") MultipartFile images,
			@RequestParam("projectPath") String projectPath) {


		

			try {
				
				String jsonData = imageProcessingService.processImagesAndGetJsonData(images, projectPath);

				
					
				 //    String base = imageProcessingService.convertImageToPdf(images);
				//	String base64 = imageProcessingService.convertPdfToBase64(base);

					// Create custom response object with JSON data and PDF bytes

					return ResponseEntity.ok(jsonData);
				
			} catch (ImageProcessingException ex) {
				// Return ResponseEntity with error message and internal server error status
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
	}
	
	
	
	@PostMapping("/pdf")
	public ResponseEntity<?> fetchAbbyyDataPdf(@RequestParam("images") MultipartFile images) {
	    try {
	        // Process images and get PDF data
	        String base = imageProcessingService.convertTIFFtoPDF(images);
	        String base64 = imageProcessingService.convertPdfToBase64(base);
	        
	        
	        
	        // Create a JSON object with the required structure
	        JSONObject jsonResponse = new JSONObject();
	        jsonResponse.put("pdfData", base64);
	        
	        
	        return ResponseEntity.ok(jsonResponse.toString());
	    } catch (ImageProcessingException ex) {
	        // Return ResponseEntity with error message and internal server error status
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    } catch (Exception e) {
	        // Return ResponseEntity with error message and not found status
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}


}
