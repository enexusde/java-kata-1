package org.echocat.kata.java.part1.abs;

import java.util.Set;

import org.echocat.kata.java.part1.data.dto.BasicAuthorInformations;
import org.echocat.kata.java.part1.ex.ExceptionHandler;
import org.echocat.kata.java.part1.statefull.InputHolder;

public interface UserInterface {
	Set<ExceptionHandler> getExceptionHandlers();

	void listBooks();

	void listAuthors();

	void writeSecure(UnsaveExecution e);

	void printWelcome();

	void showPrompt();

	boolean userLikeToExit();

	void printBye();

	void readInput();

	InputHolder getInputHolder();

	void listMagazines();

	String readIsbn();

	void printResult(Set<BasicAuthorInformations> elements);

	String readEMail();
}
