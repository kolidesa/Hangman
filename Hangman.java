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
	static JTextField f;

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);

		frame = new JFrame("Hangman!");

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());


		Scanner s = new Scanner(new File("words.txt"));


		Random rand = new Random();
		int num = rand.nextInt(417147);

		for (int i = 0; i < num; i++) {
			word = s.nextLine();
		}

		hangmanWord = new char[word.length()];
		for (int i = 0; i < hangmanWord.length; i++) {
			hangmanWord[i] = '_';
		}

		complete = false;
		gallowsNum = 0;
		guessedLetters = new ArrayList<Character>();





		JLabel label;
		label = new JLabel(getWord(hangmanWord));

		JPanel gallows = printGallows();
		//gallows.setPreferredSize(new Dimension(5, 100));

		JLabel label2 = new JLabel("Guess a letter: ");

		f = new JTextField(10);

		JButton button = new JButton();
		button.setText("Submit");
		button.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		            char guess=f.getText().toString().charAt(0);
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
		    }  
		});


		panel.add(label);
		panel.add(label2);
		panel.add(f);
		panel.add(button);

		frame.add(gallows);
		frame.add(panel);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//do {

			//gallows = new JPanel(printGallows());

			//Add gallows
			
			
			/*panel.add(label);
			panel.add(label2);
			panel.add(f);
			panel.add(button);*/
			//frame.add(gallows);
			//frame.add(panel);
			//frame.revalidate();
			//frame.setVisible(false);
			//frame.setVisible(true);

			if (gallowsNum == 6) {
				System.out.println("You lost! The word was " + word);
				//break;
			}

			//printGuessedLetters(guessedLetters);

			//printWord(hangmanWord);

			/*System.out.print("Guess a letter: ");
			char guess = scan.next().charAt(0);*/

			

		//} while (!complete);

		if (complete) {
			printWord(hangmanWord);
			System.out.println("Congratulations! You won!");
		}

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

		JLabel g1 = new JLabel("    __\n");
		JLabel g2 = new JLabel("   |  |\n");

		JLabel g3;
		if (gallowsNum > 0)
			g3 = new JLabel(" o  |");
		else g3 = new JLabel("      |");
		

		JLabel g4;
		if (gallowsNum > 3)
			g4 = new JLabel("  -|- |");
		else if (gallowsNum > 2)
			g4 = new JLabel("  -|  |");
		else if (gallowsNum > 1)
			g4 = new JLabel("   |  |");
		else g4 = new JLabel("      |");
		
		JLabel g5;
		if (gallowsNum > 5)
			g5 = new JLabel("  / \\ |");
		else if (gallowsNum > 4)
			g5 = new JLabel("  /   |"); 
		else g5 = new JLabel("      |");


		JLabel g6 = new JLabel("      |");
		JLabel g7 = new JLabel("-----");


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

	/*public void actionPerformed(ActionEvent e) {
		
    }*/
}