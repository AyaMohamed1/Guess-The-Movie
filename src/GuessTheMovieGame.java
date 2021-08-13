import java.io.*;
import java.util.*;
public class GuessTheMovieGame {
    public static void main (String [] args) throws FileNotFoundException {
        int size, randomNum, count = 0;
        Boolean won = false;
        char letter;
        String movieName, wrongLetters = " ", listMovies[];

        File file = new File("moviesList.txt");
        Scanner input = new Scanner(file);
        Stack <String> stackListMovies = new Stack <String>();
        while (input.hasNext()){
            stackListMovies.push(input.nextLine());
        }
        input.close();

        size = stackListMovies.size();
        listMovies = new String[size];
        for(int i = 0; i < size; i++){
            listMovies[i] = stackListMovies.pop();
        }
        randomNum = (int) (Math.random() * size) + 1;
        movieName = listMovies[randomNum];
        System.out.println(movieName);
        String guessed = "";
        for(int i = 0; i < movieName.length(); i++){
            if(movieName.charAt(i) == ' ')
                guessed += " ";
            else
                guessed += "_";
        }

        input = new Scanner(System.in);
        while(count < 10 && !won){
            System.out.println("You are guessing: " + putSpacesBetweenChars(guessed));
            System.out.print("You have guessed (" + count + ") wrong letters..");
            for(int i = 0; i < wrongLetters.length(); i++){
                System.out.print(wrongLetters.charAt(i) + " ");
            }
            System.out.println("\nGuess a letter: ");
            letter = input.next().charAt(0);
            if(movieName.contains(letter + ""))
                guessed = modifyGuessed(movieName, guessed, letter);

            else {
                if(!wrongLetters.contains(letter + "")) {
                    count++;
                    wrongLetters += letter;
                }
            }

            if(movieName.equals(guessed)){
                won = true;
                break;
            }

        }

        if(won)
            System.out.println("You have guessed " + movieName + " correctly." + "\nYou win! <3");
        else
            System.out.println("Game over!.. You lose:( \nThe movie was " + movieName);

    }

    public static String modifyGuessed(String original, String guessed, char c){
        String afterGuessing = "";
        for (int i = 0; i < original.length(); i++){
            if(original.charAt(i) == c)
                afterGuessing += c;
            else
                afterGuessing += guessed.charAt(i);
        }
        return afterGuessing;
    }

    public static String putSpacesBetweenChars(String s){
        String stringWithSpaces = "";
        for(int i = 0; i <s.length() - 1; i++){
            stringWithSpaces += s.charAt(i) + " ";
        }
        stringWithSpaces += s.charAt(s.length() - 1);
        return stringWithSpaces;
    }

}
