package com.shivaji.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.shivaji.model.Person;
import com.shivaji.service.QRService;

@RestController
public class QRCodeController {
	
	@Autowired
	private QRService qrService;
	
	@GetMapping("/")
	public String home() {
		return "Welcome to the page of QR and bar code generator.";
	}
	
	 @GetMapping("/generateQRCodeByText")
	    public ResponseEntity<byte[]> generateQRCodeByText(@RequestParam("text") String text) throws Exception {
	    	
	            byte[] qrCodeImage = qrService.generateQRCode(text);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_PNG);

	            ContentDisposition contentDisposition = ContentDisposition
	                    									.inline()
	                    										.filename("barcode.png")
	                    											.build();

	            headers.setContentDisposition(contentDisposition);

	            return ResponseEntity.ok()
	                    	.headers(headers)
	                    		.body(qrCodeImage);
	    }
	    
	    // Api for generating the barcode from the JSON Data
	    
	    @PostMapping("/generateQRCodeByObject")
	    public ResponseEntity<byte[]> generateQRCodeByObject(@RequestBody Person person) throws IOException, WriterException{
	    	byte[] barCodeImage = qrService.generateQRCodeByObject(person);
	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.IMAGE_PNG);
	    	
	    	ContentDisposition contentDisposition = ContentDisposition
	    												.inline()
	    													.filename("barcode.png")
	    														.build();
	    	headers.setContentDisposition(contentDisposition);
	    	
	    	return ResponseEntity.ok().headers(headers).body(barCodeImage);
	    }
}