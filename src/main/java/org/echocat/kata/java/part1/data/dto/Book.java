package org.echocat.kata.java.part1.data.dto;

public class Book extends BasicAuthorInformations {
	public String description;

	@Override
	public String toString() {
		return "Book #" + hashCode() + " \t " + title + " (" + isbn + ") from " + authors;
	}
}
