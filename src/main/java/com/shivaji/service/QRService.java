package com.shivaji.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.shivaji.model.Person;

@Service
public class QRService {

	public byte[] generateQRCode(String text) throws WriterException, IOException {
		MultiFormatWriter barCodeWriter = new MultiFormatWriter();
		BitMatrix bitMatrix = barCodeWriter.encode(text, BarcodeFormat.QR_CODE, 175, 175);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
		return byteArrayOutputStream.toByteArray();
	}

	public byte[] generateQRCodeByObject(Person person) throws IOException,WriterException{
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(person);
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		BitMatrix bitMatrix = multiFormatWriter.encode(jsonString, BarcodeFormat.CODE_128, 175, 175);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
		
		return byteArrayOutputStream.toByteArray();
	}
	
}

// BitMatrix bitMatrix = barCodeWriter.encode(text, BarcodeFormat.QR_CODE, 175, 175);
// At the palce where QR_CODE is written we have to write another format like (CODE_128/PDF_417/CODABAR) to get the different outputs.
