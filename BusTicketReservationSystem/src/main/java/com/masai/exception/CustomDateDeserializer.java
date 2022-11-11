package com.masai.exception;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomDateDeserializer extends StdDeserializer<LocalDate>{

	 private static DateTimeFormatter formatter
     = DateTimeFormatter.ofPattern("YYYY-MM-DD");

	 public CustomDateDeserializer() {
		 this(null);
	 }

	 public CustomDateDeserializer(Class<?> vc) {
		 super(vc);
	 }

	 @Override
	    public LocalDate deserialize(
	            JsonParser jsonparser, DeserializationContext context)
	            throws IOException {

	        String date = jsonparser.getText();
	        try {
	            return LocalDate.parse(date, formatter);
	        } catch (DateTimeParseException e) {
	            throw new RuntimeException("Your custom exception");
	        }
	    }

	
	
	
}
