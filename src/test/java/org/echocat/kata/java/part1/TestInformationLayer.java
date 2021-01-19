package org.echocat.kata.java.part1;

import java.io.IOException;

import org.echocat.kata.java.part1.abs.InformationLayer;
import org.echocat.kata.java.part1.data.CsvInformationLayer;

public class TestInformationLayer {

	private InformationLayer informationLayer = new CsvInformationLayer();

	public void testBooksCanBeRead() throws IOException {
		assert informationLayer.readBooks().hasNext() : "No books could be read!";
	}

	public void testAuthorsCanBeRead() throws IOException {
		assert informationLayer.readAuthors().hasNext() : "No authors could be read!";
	}

	public void testMagazinesCanBeRead() throws IOException {
		assert informationLayer.readMagazines().hasNext() : "No magazines could be read!";
	}

	public void testWrongEMailFindNoProduct() throws IOException {
		assert informationLayer.findByEMail("meNoEmail").isEmpty() : "Wrong email gives results, that is not expected!";
	}

	public void testWrongISBNFindNoProduct() throws IOException {
		assert informationLayer.findIsbns("I_am_no_valid_isbn")
				.isEmpty() : "Wrong isbn gives results, that is not expected!";
	}
}
