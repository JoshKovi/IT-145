//Joshua Kovacevich, 20201010, This is an input/output program for lab 6.6
import java.util.Scanner;

public class ParseStrings {
    public static void main(String[] args) {
        //Here I declare/initialize all require variables
        Scanner scan = new Scanner(System.in);
        Scanner fullWordInSS = null;
        String firstWord;
        String lastWord;
        String userInput;



        //This is the programs main do/while loop until userInput equals "Q" or "q"
        do{
            //This prompts the user for a String and assigns it to userInput
            System.out.println("Enter input string: ");
            userInput = scan.nextLine();

            //This if statement only executes if userInput contains a comma, the else if simply makes the do/while output nothing
            //if initial input is "Q" or "q", the else is for input missing a comma
            if(userInput.contains(",")){
                userInput = userInput.replace(",", " ");
                fullWordInSS = new Scanner(userInput);
                firstWord = fullWordInSS.next();
                lastWord = fullWordInSS.nextLine();
                lastWord = lastWord.stripLeading();
                System.out.printf("First word: %s\nSecond word: %s\n\n\n", firstWord, lastWord);
            }else if(userInput.equalsIgnoreCase("q")){
                continue;
            }
            else{
                System.out.println("Error: No comma in string"); 
            }
        }while(!(userInput.equalsIgnoreCase("q")));
        scan.close();
    }
}
