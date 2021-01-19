package org.echocat.kata.java.part1.data;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.echocat.kata.java.part1.abs.Finder;
import org.echocat.kata.java.part1.abs.InformationLayer;
import org.echocat.kata.java.part1.data.dto.Author;
import org.echocat.kata.java.part1.data.dto.BasicAuthorInformations;
import org.echocat.kata.java.part1.data.dto.Book;
import org.echocat.kata.java.part1.data.dto.Magazine;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvInformationLayer implements InformationLayer {

	public Iterator<Author> readAuthors() throws IOException {
		CsvMapper m = new CsvMapper();
		CsvSchema k = m.schemaFor(Author.class).withColumnSeparator(';').withLineSeparator("\n")
				.sortedBy("email", "firstname", "lastname").withHeader();
		return m.reader(k).createParser(getClass().getResourceAsStream("authors.csv")).readValuesAs(Author.class);
	}

	public Iterator<Book> readBooks() throws IOException {
		CsvMapper m = new CsvMapper();
		CsvSchema k = m.schemaFor(Book.class).withColumnSeparator(';').withLineSeparator("\n")
				.sortedBy("title", "isbn", "authors", "description").withHeader();
		return m.reader(k).createParser(getClass().getResourceAsStream("books.csv")).readValuesAs(Book.class);
	}

	public Iterator<Magazine> readMagazines() throws IOException {
		CsvMapper m = new CsvMapper();
		CsvSchema k = m.schemaFor(Magazine.class).withColumnSeparator(';').withLineSeparator("\n").withHeader()
				.sortedBy("title", "isbn", "authors", "publishedAt");
		return m.reader(k).createParser(getClass().getResourceAsStream("magazines.csv")).readValuesAs(Magazine.class);
	}

	public Set<BasicAuthorInformations> findIsbns(String isbn) {
		return search(dto -> dto.isbn.equals(isbn));
	}

	private Set<BasicAuthorInformations> search(Finder r) {
		try {
			Set<BasicAuthorInformations> result = new LinkedHashSet<BasicAuthorInformations>();
			Iterator<Book> books = readBooks();
			while (books.hasNext()) {
				Book book = books.next();
				if (r.found(book)) {
					result.add(book);
				}
			}
			Iterator<Magazine> magazines = readMagazines();
			while (magazines.hasNext()) {
				Magazine m = magazines.next();
				if (r.found(m)) {
					result.add(m);
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Set<BasicAuthorInformations> findByEMail(String email) {
		return search(dto -> Arrays.binarySearch(dto.authors.split(","), email) > 0);
	}

	public Set<BasicAuthorInformations> listAllSortedTitle() {
		SortedSet<BasicAuthorInformations> treeSet = new TreeSet<>((above, below) -> {
			return above.title.trim().compareTo(below.title.trim());
		});
		treeSet.addAll(search(dto -> true));
		return treeSet;
	}

}
