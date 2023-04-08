import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hangman {
	
	static int gallowsNum;
	static JFrame frame;
	static String word;
	static char[] hangmanWord;
	static boolean complete;
	static ArrayList<Character> guessedLetters;

	public static void main(String[] args) throws Exception {

		frame = new JFrame("Hangman!");


		Scanner scan = new Scanner(System.in);

		Scanner s = new Scanner(new File("words.txt"));


		Random rand = new Random();
		int num = rand.nextInt(417147);

		for (int i = 0; i < num; i++) {
			word = s.nextLine();
		}

		word = word.toLowerCase();

		hangmanWord = new char[word.length()];
		for (int i = 0; i < hangmanWord.length; i++) {
			hangmanWord[i] = '_';
		}

		complete = false;
		gallowsNum = 0;
		guessedLetters = new ArrayList<Character>();

		updateFrame();
	}

	static void updateFrame() {
		frame.getContentPane().removeAll();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());

		boolean ending = checkEnding();

		if (ending == false) {

			JLabel label;
			label = new JLabel(getWord(hangmanWord));

			JPanel gallows = printGallows();

			JLabel label2 = new JLabel("Guess a letter: ");

			JTextField f = new JTextField(10);

			JButton button = new JButton();
			button.setText("Submit");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char guess = f.getText().toString().charAt(0);
					boolean contained = false;
					f.setText("");
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == guess) {
							hangmanWord[i] = guess;
							contained = true;
						}
						complete = checkWord(hangmanWord, word);
					}
					if (!contained) {
						gallowsNum++;
						if (guessedLetters.contains(guess) == false)
							guessedLetters.add(guess);
					}
					updateFrame();
				}
			});

			JPanel guessedLettersPanel = printGuessedLetters();

			panel.add(label);
			panel.add(label2);
			panel.add(f);
			panel.add(button);
			mainPanel.add(gallows);
			mainPanel.add(guessedLettersPanel);
			mainPanel.add(panel);
		}
		else {
			JLabel p = getEnding();
			mainPanel.add(p);
		}

		frame.add(mainPanel);

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	static boolean checkEnding() {
		if (gallowsNum == 6 || complete) {
			return true;
		}
		return false;
	}

	static JLabel getEnding() {
		JLabel p;

		if (complete) {
			p = new JLabel("Congratulations! You won!");
		}
		else {
			p = new JLabel("You lost! The word was " + word);
		}
		p.setFont(new Font("Serif", Font.PLAIN, 25));

		return p;
	}

	static String getWord(char[] word) {
		String s = "";
		for (int i = 0; i < word.length; i++) {
			s += word[i] + " ";
		}
		return s;
	}

	static JPanel printGallows() {
		JPanel t = new JPanel();
		t.setLayout(new BoxLayout(t, BoxLayout.Y_AXIS));

		JLabel g1 = new JLabel("    ____\n");
		JLabel g2 = new JLabel("   |          |\n");

		JLabel g3;
		if (gallowsNum > 0)
			g3 = new JLabel(" o           |");
		else g3 = new JLabel("              |");
		

		JLabel g4;
		if (gallowsNum > 3)
			g4 = new JLabel("  -|-         |");
		else if (gallowsNum > 2)
			g4 = new JLabel("  -|          |");
		else if (gallowsNum > 1)
			g4 = new JLabel("   |          |");
		else g4 = new JLabel("              |");
		
		JLabel g5;
		if (gallowsNum > 5)
			g5 = new JLabel("  / \\        |");
		else if (gallowsNum > 4)
			g5 = new JLabel("  /           |");
		else g5 = new JLabel("              |");

		JLabel g6 = new JLabel("              |");
		JLabel g7 = new JLabel("----------");

		t.add(g1);
		t.add(g2);
		t.add(g3);
		t.add(g4);
		t.add(g5);
		t.add(g6);
		t.add(g7);

		return t;
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

	static JPanel printGuessedLetters() {
		JPanel j = new JPanel();

		JLabel g1 = new JLabel("Guessed Letters: ");
		JLabel g2 = new JLabel("");

		if (!guessedLetters.isEmpty()) {
			String s = "";
			for (int i = 0; i < guessedLetters.size(); i++) {
				s += guessedLetters.get(i) + "  ";
			}
			g2 = new JLabel(s);
		}

		j.add(g1);
		j.add(g2);
		
		return j;
	}
}