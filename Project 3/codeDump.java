import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class codeDump extends Driver{
    public static void mainLoop(Scanner scan, ArrayList Passengers){
        String userInput;
        char userChar = 'z';
        int passengerNum = Passengers.size();

        do{
            
            displayMenu();
            userInput = scan.nextLine();
            if(userInput.equals("")){
                System.out.println("Empty input, please try again.");
                continue;
            }
            userChar = userCharCheck(scan, userInput, "main");
            switch(userChar){
                case '1':
                    addShip(scan);
                    continue;
                case '2':
                    editShip();
                    continue;
                case '3':
                    addCruise(scan);
                    continue;
                case '4':
                    editCruise();
                    continue;
                case '5':
                    addPassenger(scan);
                    continue;
                case '6':
                    editPassenger();
                    continue;
                case 'A':
                    printShipList("name");
                    continue;
                case 'B':
                    printShipList("active");
                    continue;
                case 'C':
                    printShipList("full");
                    continue;
                case 'D':
                    printCruiseList("list");
                    continue;
                case 'E':
                    printCruiseList("details");
                    continue;
                case 'F':
                    printPassengerList();
                    continue;
                case 'X':
                    continue;
                default:
                    System.out.println("Invalid Entry");
            }
            scan.nextLine();
        }while(userChar != 'X');
    }
    
    public static char userCharCheck(Scanner scan, String userInput, String menu) {
        char userChar = 'z';
        try{
            if(userInput.length()>1){
                throw new Error("Invalid input, input should be 1 character long: ");
            }
            userChar = userInput.toUpperCase().charAt(0);
            //userChar = checkOption(userChar, menu);
        }
        catch(InputMismatchException error){
            System.out.println("Invalid character, please select from options in \"[]\": ");
            userInput = scan.nextLine();
            userCharCheck(scan, userInput, menu);
        }
        catch(Error error){
            System.out.println(error.getMessage());
            userInput = scan.nextLine();
            userCharCheck(scan, userInput, menu);
        }
        
        return userChar;
    }
    /*
    public static char checkOption(char userChar, String menu) {
        if(menu.equals("main")){
            //This switch case ensures a valid option for this menu is selected
            switch(userChar){
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'X':
                    break;
                default:
                    displayMenu();
                    throw new InputMismatchException();
            }
        }
        else if(menu.equals("passenger")){
            //FIXME: Placeholder for future menu options
        }
        else if(menu.equals("cruise")){
            //FIXME: Placeholder for future menu options
        }
        else if(menu.equals("ship")){
            //FIXME: Placeholder for future menu options
        }
        else{
            System.out.println("Invalid option");
        }
        return userChar;
    }*/
    
}
