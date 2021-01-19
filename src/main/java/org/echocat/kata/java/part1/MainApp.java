package org.echocat.kata.java.part1;

import java.io.IOException;

import org.echocat.kata.java.part1.abs.InformationLayer;
import org.echocat.kata.java.part1.abs.UserInterface;
import org.echocat.kata.java.part1.data.CsvInformationLayer;

public class MainApp {

	public static void main(String[] args) throws IOException {
		InformationLayer il = new CsvInformationLayer();
		UserInterface ui = new ConsoleUserInterface(il);
		ui.printWelcome();
		ui.getExceptionHandlers().add(System.err::println);
		while (true) {
			if (ui.getInputHolder().listAuthors) {
				ui.listAuthors();
			}
			if (ui.getInputHolder().listBooks) {
				ui.listBooks();
			}
			if (ui.getInputHolder().listMagazines) {
				ui.listMagazines();
			}
			if (ui.getInputHolder().searchEMail) {
				String email = ui.readEMail();
				ui.printResult(il.findByEMail(email));
			}
			if (ui.getInputHolder().searchIsbn) {
				String isbn = ui.readIsbn();
				ui.printResult(il.findIsbns(isbn));
			}
			if (ui.getInputHolder().listSortedTitle) {
				ui.printResult(il.listAllSortedTitle());
			}
			ui.showPrompt();
			ui.readInput();
			if (ui.userLikeToExit()) {
				ui.printBye();
				return;
			}
		}
	}
}
