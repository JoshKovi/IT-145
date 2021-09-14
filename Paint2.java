//Joshua Kovacevich, 20201010, This is the Paint2 class for determining the Cans of paint and gallons of paint required for a wall
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Math;

public class Paint2 {

    public static void main(String[] args) {
        //These are the variables for the program, note I only declare wallHeight and wallWidth as it does not need initiallized 
        Scanner scnr = new Scanner(System.in);
        double wallHeight;
        double wallWidth;
        double wallArea = 0.0;
        double gallonsPaintNeeded = 0.0;
        
        final double SQUARE_FEET_PER_GALLON = 350.0; //Just to make it clear its a constant I capitalized and underscored it
        
        //Here I call a function rather than copy paste similar code
        wallHeight = doWhileDouble(scnr, "Enter wall height (feet): ");
        wallWidth = doWhileDouble(scnr, "Enter wall width (feet): ");//Mistake was removed with 2x wallHeight assignment

        // Calculate and output wall area, the print statement is commeneted out due to the output not being required for this assignment.
        wallArea = wallHeight * wallWidth;
        //System.out.printf("Wall area: %s square feet\n", wallArea);//I allow the float to match exactly with %s, the missing variable was added

        // Calculate and output the amount of paint (in gallons) needed to paint the wall
        gallonsPaintNeeded = wallArea/SQUARE_FEET_PER_GALLON;
        System.out.printf("Paint needed: %s gallons\n", gallonsPaintNeeded);//I allow the float to match exactly with %s, the variable spelling was corrected

        //I chose to use Math.ceil(double) as it always rounds up to the next integer, another method could have been floor + 1, but I prefer a direct approach
        System.out.printf("Cans needed: %s can(s)", Math.ceil(gallonsPaintNeeded)); 

        //Closing the scanner
        scnr.close();
    }

    public static double doWhileDouble(Scanner scnr, String prompt){

        //userDouble is the double for user input
        Double userDouble = 0.0;

        //This do/while loop has a nested try statement with two catches and a finally it continues if userFloat = 0.0
        do{
            try{
                //This try statement prompts the user with an input string than validates input 2 ways
                //first if the input value is not a double it throws a InputMismatchException and second
                //If the value is 0 it throws a Throwable with a message
                System.out.println(prompt);
                userDouble = scnr.nextDouble();
                if(userDouble == 0.0){
                    throw new Throwable("Please enter non-zero number: ");
                }
            }
            catch(InputMismatchException e){
                //This catches wrong input types, prompts the user for valid input and effectively emptys the scnr
                //The variable e is required but unused in this example
                System.out.println("Please enter valid(Integer or Decimal) input.");
                scnr.next();
            }
            catch(Throwable errorMessage){
                //This throws the error message for a non-zero number
                System.out.println(errorMessage.getMessage());
            }
            finally{
                //This resets the scanner, it helps eliminate scanner problems but may be unnecessary in own method.
                scnr.reset();
            }

        }while(userDouble <= 0.0);

        //This returns userDouble for proper assignment
        return userDouble;
    }
}
