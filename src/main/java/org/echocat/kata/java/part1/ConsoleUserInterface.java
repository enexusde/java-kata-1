package org.echocat.kata.java.part1;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import org.echocat.kata.java.part1.abs.InformationLayer;
import org.echocat.kata.java.part1.abs.UnsaveExecution;
import org.echocat.kata.java.part1.abs.UserInterface;
import org.echocat.kata.java.part1.data.dto.BasicAuthorInformations;
import org.echocat.kata.java.part1.data.dto.Book;
import org.echocat.kata.java.part1.ex.ExceptionHandler;
import org.echocat.kata.java.part1.statefull.InputHolder;

public class ConsoleUserInterface implements UserInterface {
	private final Set<ExceptionHandler> handlers = new LinkedHashSet<>();

	private InformationLayer informationLayer;
	private InputHolder holder = new InputHolder();

	@Override
	public Set<ExceptionHandler> getExceptionHandlers() {
		return handlers;
	}

	public ConsoleUserInterface(InformationLayer informationLayer) {
		this.informationLayer = informationLayer;
	}

	@Override
	public void listBooks() {
		this.writeSecure(() -> informationLayer.readBooks());
	}

	@Override
	public void listAuthors() {
		this.writeSecure(() -> informationLayer.readAuthors());
	}

	@Override
	public void writeSecure(UnsaveExecution e) {
		try {
			Iterator<?> iterrator = e.execute();
			while (iterrator.hasNext()) {
				System.out.println(iterrator.next());
			}
		} catch (Exception ex) {
			handle(ex);
		}
	}

	private void handle(Exception ex) {
		for (ExceptionHandler exceptionHandler : handlers) {
			exceptionHandler.notify(ex);
		}
	}

	@Override
	public void printWelcome() {
		System.out.println("Welcome!");
		try {
			Book b = informationLayer.readBooks().next();
			System.out.println(b.title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showPrompt() {
		System.out.println("What do you like to do now?");
		System.out.println(" b - Shows all books.");
		System.out.println(" a - Shows all authors.");
		System.out.println(" m - Shows all magazines.");
		System.out.println(" f - Find a book/magazine by ISBN.");
		System.out.println(" s - Search a book/magazine by email.");
		System.out.println(" t - List book/magazine sorted by title.");
		System.out.println(" q - Quit.");
	}

	@Override
	public boolean userLikeToExit() {
		return getInputHolder().quit;
	}

	@Override
	public void printBye() {
		System.out.println("Bye!");
	}

	@Override
	public void readInput() {
		System.out.println("Decision:");
		Scanner userinput = new Scanner(System.in);
		String nextLine = userinput.nextLine();
		nextLine = nextLine.trim();
		holder.quit = nextLine.equals("q");
		holder.listBooks = nextLine.equals("b");
		holder.listAuthors = nextLine.equals("a");
		holder.listMagazines = nextLine.equals("m");
		holder.searchIsbn = nextLine.equals("f");
		holder.searchEMail = nextLine.equals("s");
		holder.listSortedTitle = nextLine.equals("t");
	}

	@Override
	public InputHolder getInputHolder() {
		return holder;
	}

	@Override
	public void listMagazines() {
		this.writeSecure(() -> informationLayer.readMagazines());
	}

	@Override
	public String readIsbn() {
		System.out.print("Please enter ISBN:");
		return new Scanner(System.in).nextLine();
	}

	@Override
	public void printResult(Set<BasicAuthorInformations> elements) {
		for (BasicAuthorInformations basicAuthorInformations : elements) {
			System.out.println(basicAuthorInformations);
		}
	}

	@Override
	public String readEMail() {
		System.out.print("Please enter eMail of author: ");
		return new Scanner(System.in).nextLine();
	}
}
