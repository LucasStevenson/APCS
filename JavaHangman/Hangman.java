import java.util.*;
import java.io.File;

class Hangman {
    private static int count = 0;
    private static String word;

    public static void draw() {
        if (count == 1) {
            System.out.println("\nWRONG! Try again");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("___|___\n");
        }
        if (count == 2) {
            System.out.println("\nWRONG! Try again");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___\n");
        }
        if (count == 3) {
            System.out.println("\nWRONG! Try again");
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___\n");
        }
        if (count == 4) {
            System.out.println("\nWRONG! Try again");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___\n");
        }
        if (count == 5) {
            System.out.println("\nWRONG! Try again");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___\n");
        }
        if (count == 6) {
            System.out.println("\nWRONG! Try again");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\\n");
        }
        if (count == 7) {
            System.out.println("\nGAME OVER!");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println("TOUGH LUCK...the word was " + word);
        }
    }

    public static void main(String[] args) throws java.io.FileNotFoundException {
        Scanner inFile = new Scanner(new File("words.txt"));
        Scanner input = new Scanner(System.in);
        int num = (int)Math.floor(Math.random() * 100);
        for (int i = 0; i <= num; i++) {
            word = inFile.nextLine().toLowerCase();
        }
        String showWord = new String(new char[word.length()]).replace("\0", "_");
        // idk y "_".repeat(word.length()) doesnt work...whack asf

        while (count < 7 && showWord != word) {
            System.out.println(showWord.replace("", " ").trim());
            System.out.println("Enter a character or a word");
            String guess = input.next().toLowerCase();
            String guessWord = "";

            if (guess.equals(word)) {
                System.out.println("\nCorrect! You win! The word was " + word);
                break;
            }

            for (int i = 0; i < word.length(); i++) {
                if (guess.charAt(0) == word.charAt(i) && guess.length() == 1) {
                    guessWord += guess;
                } else if (showWord.charAt(i) != '_') {
                    guessWord += word.charAt(i);
                } else {
                    guessWord += "_";
                }
            }
            //System.out.println(guessWord);

            if (showWord.equals(guessWord)) {
                count++;
                draw();
            } else {
                System.out.println("\nVery nice");
                showWord = guessWord;
            }
            if (guessWord.equals(word)) {
                System.out.println("\nCorrect! You win! The word was " + word);
                break;
            }
        }
    }
}