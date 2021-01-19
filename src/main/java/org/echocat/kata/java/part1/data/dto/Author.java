package org.echocat.kata.java.part1.data.dto;

public class Author {
	public String email;
	public String firstname;
	public String lastname;

	@Override
	public String toString() {
		return "#" + hashCode() + " \t " + firstname + " " + lastname + " (" + email + ")";
	}
}
