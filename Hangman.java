import java.util.*;
import java.io.*;

public class Hangman {
	
	static int gallowsNum;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to Hangman!");

		//Read in hangman word from text file
		

		//Scanner s = new Scanner(new File(""));

		String word = "temp";//s.nextLine();

		char[] hangmanWord = new char[word.length()];
		for (int i = 0; i < hangmanWord.length; i++) {
			hangmanWord[i] = '_';
		}

		boolean complete = false;
		gallowsNum = 0;
		ArrayList<Character> guessedLetters = new ArrayList<Character>();
		do {

			printGallows();

			if (gallowsNum == 6) {
				System.out.println("You lost! The word was " + word);
				break;
			}

			printGuessedLetters(guessedLetters);

			printWord(hangmanWord);

			System.out.print("Guess a letter: ");
			char guess = scan.next().charAt(0);

			boolean contained = false;

			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == guess) {
					hangmanWord[i] = guess;
					contained = true;
				}
			}

			if (!contained) {
				gallowsNum++;
				if (guessedLetters.contains(guess) == false)
					guessedLetters.add(guess);
			}
			System.out.println();

			complete = checkWord(hangmanWord, word);

		} while (!complete);

		if (complete) {
			printWord(hangmanWord);
			System.out.println("Congratulations! You won!");
		}

	}

	static void printGallows() {
		System.out.println("  __");
		System.out.println(" |  |");
		if (gallowsNum > 0)
			System.out.println(" o  |");
		else System.out.println("    |");

		
		if (gallowsNum > 3)
			System.out.println("-|- |");
		else if (gallowsNum > 2)
			System.out.println("-|  |");
		else if (gallowsNum > 1)
			System.out.println(" |  |");
		else System.out.println("    |");

		if (gallowsNum > 5)
			System.out.println("/ \\ |");
		else if (gallowsNum > 4)
			System.out.println("/   |"); 
		else System.out.println("    |");

		System.out.println("    |");
		System.out.println("-----");
	}

	static void printWord(char[] word) {
		System.out.println();
		for (int i = 0; i < word.length; i++) {
			System.out.print(word[i] + " ");
		}
		System.out.println();
	}

	static boolean checkWord(char[] hangmanWord, String word) {
		for (int i = 0; i < hangmanWord.length; i++) {
			if (hangmanWord[i] != word.charAt(i))
				return false;
		}
		return true;
	}

	static void printGuessedLetters(ArrayList<Character> guesses) {
		System.out.println();
		System.out.println("Guessed Letters");
		System.out.println("---------------");
		if (!guesses.isEmpty()) {
			for (int i = 0; i < guesses.size(); i++) {
				System.out.print(guesses.get(i) + "  ");
			}
		}
		System.out.println();
		System.out.println("---------------");

	}


}