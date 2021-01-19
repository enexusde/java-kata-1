package org.echocat.kata.java.part1.abs;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.echocat.kata.java.part1.data.dto.Author;
import org.echocat.kata.java.part1.data.dto.BasicAuthorInformations;
import org.echocat.kata.java.part1.data.dto.Book;
import org.echocat.kata.java.part1.data.dto.Magazine;

public interface InformationLayer {
	Iterator<Author> readAuthors() throws IOException;

	Iterator<Book> readBooks() throws IOException;

	Iterator<Magazine> readMagazines() throws IOException;

	Set<BasicAuthorInformations> findIsbns(String isbn);

	Set<BasicAuthorInformations> findByEMail(String email);

	Set<BasicAuthorInformations> listAllSortedTitle();
}
