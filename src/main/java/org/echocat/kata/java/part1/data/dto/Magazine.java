package org.echocat.kata.java.part1.data.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Magazine extends BasicAuthorInformations {
	private static final String DATE_OUTPUT_FORMAT = "dd.MM.yyyy";
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_OUTPUT_FORMAT)
	public Date publishedAt;

	@Override
	public String toString() {
		SimpleDateFormat humanReadableFormat = new SimpleDateFormat(DATE_OUTPUT_FORMAT);
		return "Magazine #" + hashCode() + " \t " + title + " (" + isbn + ") from " + authors + " published "
				+ humanReadableFormat.format(publishedAt);
	}
}
