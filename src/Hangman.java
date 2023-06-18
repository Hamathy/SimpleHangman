import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
// Add a hint option
// Add a score display based on the count
// Add a guess word option
public class Hangman {
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Hangman.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        int randIndex = new Random().nextInt(words.size());
        String selectedWord = words.get(randIndex).toLowerCase();
        int wordSize = selectedWord.length();
        ArrayList<Character> guessedLetters = new ArrayList<>();
        String filled = filledWord(selectedWord, guessedLetters);
        int hangmanCount = 0;
        System.out.println(filled);
        while (!filled.equals(selectedWord)) {
            System.out.println("Guess a letter or Enter a word");
            String guessed = key.nextLine();
            int guessedSize = guessed.length();
            if (guessedSize > 1) {
                if (guessed.equals(selectedWord)) {
                    System.out.println("You Win");
                    break;
                } else {
                    System.out.println("You Lose");
                    break;
                }
            }
            Character letter = guessed.charAt(0);
            if (guessedLetters.contains(letter)) {
                System.out.println("You Already Selected That Letter. Try Something Else");
                continue;
            }
            guessedLetters.add(letter);
            if (!selectedWord.contains(String.valueOf(letter))) {
                hangmanCount++;
            }
            switch (hangmanCount) {
                case 1:
                    System.out.println(" O \n\n\n\n");
                    break;
                case 2:
                    System.out.println(" O \n |\n |\n |");
                    break;
                case 3:
                    System.out.println(" O \n |\n |\n |\n/");
                    break;
                case 4:
                    System.out.println(" O \n |\n |\n |\n/ \\");
                    break;
                case 5:
                    System.out.println(" O \n/|\n |\n |\n/ \\");
                    break;
                case 6:
                    System.out.println(" O \n/|\\\n |\n |\n/ \\");
                    break;
            }
            if (hangmanCount == 6) {
                System.out.println("You Lose");
                break;
            }
            filled = filledWord(selectedWord, guessedLetters);
            System.out.println(filled);
        }
        if (filled.equals(selectedWord)) {
            System.out.println("You Win");
        }

    }
    public static String filledWord(String word, ArrayList<Character> guessedLetters) {
        char[] charArray = new char[word.length()];
        for (int i = 0; i < charArray.length; i++) {
            if (word.charAt(i) == ' ') {
                charArray[i] = ' ';
            } else {
                charArray[i] = '_';
            }
        }
        for (int i = 0; i < guessedLetters.size(); i++) {
            Character currLetter = guessedLetters.get(i);
            int index = word.indexOf(currLetter);
            while (index >= 0) {
                charArray[index] = currLetter;
                index = word.indexOf(currLetter, index + 1);
            }
        }
        String filled = new String(charArray);
        return filled;
    }
}
