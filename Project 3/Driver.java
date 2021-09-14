//Joshua Kovacevich, 20201015, This is the driver class for LOCO aka project 3, major modifications were made in multiple areas, please see associated comments.
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Driver {

    // These fields were pre-created, I didn't need to implement any more for functionality
    private static ArrayList<Ship> shipList = new ArrayList<Ship>();
    private static ArrayList<Cruise> cruiseList = new ArrayList<Cruise>();
    private static ArrayList<Passenger> passengerList = new ArrayList<Passenger>();


    public static void main(String[] args) {

        initializeShipList();       // initial ships
        initializeCruiseList();     // initial cruises
        initializePassengerList();  // initial passengers

        // add loop and code here that accepts and validates user input
        // and takes the appropriate action. include appropriate
        // user feedback and redisplay the menu as needed
        Scanner scan = new Scanner(System.in);
        String userInput;
        char userChar = 'z';

        //This do/while loop displays the menu, accepts input, validates entry not blank, then validates input
        //finally the switch statement calls appropriate method then uses continue rather than break to reset loop immediately 
        do{
            
            //initial display/prompt and not empty validation
            displayMenu();
            userInput = scan.nextLine();
            if(userInput.equals("")){
                System.out.println("Empty input, please try again.");
                continue;
            }
            
            //char validation
            userChar = userCharCheck(scan, userInput);

            //Main body switch statement.
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
                    System.out.println("Exiting");
                    continue;
                default:
                    System.out.println("Invalid Entry: Press enter to try again.");
            }
            scan.nextLine();
        }while(userChar != 'X');
    }


    // hardcoded ship data for testing
    // Initialize ship list
    public static void initializeShipList() {
        add("Candy Cane", 20, 40, 10, 60, true);
        add("Peppermint Stick", 10, 20, 5, 40, true);
        add("Bon Bon", 12, 18, 2, 24, false);
        add("Candy Corn", 12, 18, 2, 24, false);
    }

    // hardcoded cruise data for testing
    // Initialize cruise list
    public static void initializeCruiseList() {
        //This was modified to pass the shipList that way accurate cruise data could be kept for room booking
        Cruise newCruise = new Cruise("Southern Swirl", "Candy Cane", "Miami", "Cuba", "Miami", shipList.get(0));
        cruiseList.add(newCruise);
    }

    // hardcoded cruise data for testing
    // Initialize passenger list
    public static void initializePassengerList() {
        //Here the Southern Swirl cruise is passed to each passenger for the purposes of accurate room booking for cruises
        Passenger newPassenger1 = new Passenger("Neo Anderson", "Southern Swirl", "STE", cruiseList.get(0));
        passengerList.add(newPassenger1);

        Passenger newPassenger2 = new Passenger("Trinity", "Southern Swirl", "STE", cruiseList.get(0));
        passengerList.add(newPassenger2);

        Passenger newPassenger3 = new Passenger("Morpheus", "Southern Swirl", "BAL", cruiseList.get(0));
        passengerList.add(newPassenger3);
    }

    // custom method to add ships to the shipList ArrayList
    public static void add(String tName, int tBalcony, int tOceanView,
                           int tSuite, int tInterior, boolean tInService) {
        Ship newShip = new Ship(tName, tBalcony, tOceanView, tSuite, tInterior, tInService);
        shipList.add(newShip);
    }


    public static void printShipList(String listType) {
        // printShipList() method prints list of ships from the
        // shipList ArrayList. There are three different outputs
        // based on the listType String parameter:
        // name - prints a list of ship names only
        // active - prints a list of ship names that are "in service"
        // full - prints tabbed data on all ships

        if (shipList.size() < 1) {
            System.out.println("\nThere are no ships to print.");
            return;
        }
        if (listType == "name") {
            System.out.println("\n\nSHIP LIST - Name");
            for (int i = 0; i < shipList.size(); i++) {
                System.out.println(shipList.get(i));
            }
        } else if (listType == "active") {
            System.out.println("\n\nSHIP LIST - Active");

            //Here I use a for loop to cycle through shipList, next I use a nested if statment
            //To determine if the ship is active, finally I print the ship if it is active
            for(int i = 0; i < shipList.size(); i++){
                if(shipList.get(i).getInService()){
                    System.out.println(shipList.get(i));
                }
            }
        } else if (listType == "full") {
            System.out.println("\n\nSHIP LIST - Full");
            System.out.println("-----------------------------------------------");
            System.out.println("                    Number of Rooms     In");
            System.out.print("SHIP NAME           Bal OV  Ste Int     Service");
            System.out.println("\n-----------------------------------------------");
            for (Ship eachShip: shipList)
                eachShip.printShipData();

        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printCruiseList(String listType) {
        if (cruiseList.size() < 1) {
            System.out.println("\nThere are no cruises to print.");
            return;
        }
        if (listType == "list") {
            System.out.println("\n\nCRUISE LIST");
            for (int i=0; i < cruiseList.size(); i++) {
                System.out.println(cruiseList.get(i));
            }
        } else if (listType == "details") {
            //This print statement was modified to show room availability as it is tracked information per customer specification
            System.out.println("\n\nCRUISE LIST - Details");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                      |----------------------PORTS-----------------------| Number of Rooms Available");
            System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN     Bal OV  Ste Int");
            System.out.println("\n-------------------------------------------------------------------------------------------------------------------------");
            for (Cruise eachCruise: cruiseList)
                eachCruise.printCruiseDetails();
        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printPassengerList() {
        if (passengerList.size() < 1) {
            System.out.println("\nThere are no passengers to print.");
            return;
        }
        System.out.println("\n\nPASSENGER LIST");
        System.out.println("-----------------------------------------------------");
        System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
        System.out.println("\n-----------------------------------------------------");
        for (Passenger eachPassenger: passengerList)
            eachPassenger.printPassenger();
    }

    // display text-based menu
    public static void displayMenu() {
        //While not required or even encouraged to do this I could not help myself, the alignment
        //Of the menu was bothering me as it did not meet the specification document
        String[] printList = {"Luxury Ocean Cruise Outings", "System Menu", "[1] Add Ship","[A] Print Ship Names",
            "[2] Edit Ship", "[B] Print Ship In Service List", "[3] Add Cruise", "[C] Print Ship Full List", 
            "[4] Edit Cruise", "[D] Print Cruise List", "[5] Add Passenger", "[E] Print Cruise Details",
            "[6] Edit Passenger", "[F] Print Passenger List", "[x] Exit System", "Enter a menu selection: "};
        
        //This for loop just implements specific print types for the 2 header lines, the exit option and the prompt, all others default to a print
        //that formats both options in 1 line then adds to i to avoid duplicating options.
        for(int i = 0; i < printList.length; i++){
            if(i == 0){
                System.out.printf("\n\n%40s\n", printList[i]);
            }else if(i == 1){
                System.out.printf("%32s\n\n", printList[i]);
            }else if(i == printList.length-2){
                System.out.println(printList[i]);
            }else if(i == printList.length-1){
                System.out.println("\n" + printList[i]);
            }else{
                System.out.printf("%-25s%-25s\n", printList[i], printList[i+1]);
                i++;
            }
        }
    }

    // Add a New Ship
    public static void addShip(Scanner scan) {
        //Here I make a list of all prompts for ease in display and a tempString for holding user input
        String[] variableName = {"ship's name", "amount of balcony rooms", "amount of ocean view rooms",
            "amount of suites", "amount of interior rooms", "if ship is active (yes/no). note: defaults to no if invalid input"};
        String tempString;

        //This creates a new Ship object
        Ship newShip = new Ship();

        //This for loop allows prompting, the try/catch catches invalid inputs, informs user of invalid entry
        //and uses i-- to repeat loop prompt until a valid entry is made
        for(int i = 0; i < variableName.length; i++){
            //This allows me to cycle through all prompts with one statement
            System.out.printf("\nPlease enter %s: ", variableName[i]);
            try{
                //A switch case seemed the clearest way to effectively manage data input
                //I considered adding a single line input but it seemed like it would be too prone
                //To user input mistakes and cause more user frustration than ease, fun fact, you can enter
                //all values in one line after entering name to quickly input data
                switch(i){
                    case 0:
                        tempString = scan.nextLine();
                        //this checks if name is taken, if taken throws Throwable with message, otherwise it sets ship name
                        for(Ship shipName: shipList){
                            if(tempString.equalsIgnoreCase(shipName.getShipName())){
                                throw new Throwable("Same ship name already in use. ");
                            }
                        }
                        newShip.setShipName(tempString);
                        //Clearing tempString removes a potential bug.
                        tempString = "";
                        break;
                    //Case 1-4 set individual room values
                    case 1:
                        newShip.setRoomBalcony(scan.nextInt());
                        break;
                    case 2:
                        newShip.setRoomOceanView(scan.nextInt());
                        break;
                    case 3:
                        newShip.setRoomSuite(scan.nextInt());
                        break;
                    case 4:
                        newShip.setRoomInterior(scan.nextInt());
                        break;
                    case 5:
                        //Due to booleans being easily messed up by user input this was the most reliable
                        //way to ensure boolean values were added correctly
                        tempString = scan.next();
                        tempString = (tempString.equalsIgnoreCase("yes")) ? "True" : "False";
                        newShip.setInService(Boolean.parseBoolean(tempString));
                        break;
                    default:
                        //This would only activate if variable string is modified improperly and it stops newShip from being added to ship list
                        System.out.println("Program Error, please try again");
                        return;
                }
            }catch(InputMismatchException error){
                //This catches invalid assignments from user input and reverts i to repeat 
                System.out.println("Please enter appropriate value.");
                scan.nextLine();
                i--;
            }catch(Throwable error){
                //This catches same name issues resets i for reprompting
                System.out.println(error.getMessage() + "Please pick another name.");
                i--;
            }
        }

        //This clears scanner then adds newShip to shipList. and reports to user ship was created
        System.out.printf("\n%s was created.", newShip.getShipName());
        scan.nextLine();
        shipList.add(newShip);
        return;
    }

    // Edit an existing ship
    public static void editShip() {

        // This method does not need to be completed
        System.out.println("The \"Edit Ship\" feature is not yet implemented.");

    }

    // Add a New Cruise
    public static void addCruise(Scanner scan) {
        //Here I create a string array for the required prompts(excluding rooms which are automatically added based off ship)
        String[] variableName = {"cruise name", "ship name", "departure port",
            "desitnation port", "return port", "balcony rooms", "ocean view rooms", "suites", "interior rooms"};
        //tempString is for user input temp storage, newCruise is the cruise and tempShip is used to store the chosen ship's variables for the cruise.
        String tempString;
        Cruise newCruise = new Cruise();
        Ship tempShip = null;

        //This for loop allows going through each user prompt, tempString is set to empty string to avoid broken infinite loops
        for(int i = 0; i < variableName.length; i++){
            tempString = "";
            //This prompts the user for cruise name, ship name, depature, destination and return ports.
            if(i<5){
                System.out.printf("\nPlease enter %s: ", variableName[i]);
            }
            
            //This try catch just catches invalid ship and cruise names
            try{
                switch(i){
                    case 0:
                        //Takes user input, cycles through cruise names and throws error if already take, if not assigns name
                        tempString = scan.nextLine();
                        for(Cruise cruiseName: cruiseList){
                            if(tempString.equalsIgnoreCase(cruiseName.getCruiseName())){
                                throw new Throwable("Cruise name already taken. ");
                            }
                        }
                        newCruise.setCruiseName(tempString);
                        break;
                    case 1:
                        //Takes user input, validats name and status, assigns ship name, then saves ship as tempShip for later usage, throws error if name invalid or status inactive
                        tempString = scan.nextLine();
                        for(Ship shipName: shipList){
                            //This validates shipName and validates ship status before assigning cruise
                            if(tempString.equalsIgnoreCase(shipName.getShipName()) && shipName.getInService()){
                                newCruise.setCruiseShipName(tempString);
                                tempShip = shipName;
                            }
                        }
                        if(newCruise.getCruiseShipName().equals(null)){
                            throw new Throwable("Ship name invalid or ship is not active. ");
                        }
                        break;
                    case 2:
                    case 3:
                    case 4:
                        //While each case has its own else if statement, this consolidates the if statement rather than writing it 3 times. Throws error if string empty
                        tempString = scan.nextLine();
                        if(tempString.equals("")){
                            throw new Throwable("Please enter port name. ");
                        }
                        else if (i == 2){
                            newCruise.setDeparturePort(tempString);
                        }
                        else if (i == 3){
                            newCruise.setDestination(tempString);
                        }
                        else if (i == 4){
                            newCruise.setReturnPort(tempString);
                        }
                        break;
                    case 5:
                        //Cases 5-8 auto assign room number to cruise
                        newCruise.setRoomBalcony(tempShip.getRoomBalcony());
                        break;
                    case 6:
                        newCruise.setRoomOceanView(tempShip.getRoomOceanView());
                        break;
                    case 7:
                        newCruise.setRoomSuite(tempShip.getRoomSuite());
                        break;
                    case 8:
                        newCruise.setRoomInterior(tempShip.getRoomInterior());
                        break;
                    default:
                        //This would only be reached with modification of variableName without adding/removing case.
                        System.out.println("Error, Please try again.");
                        return;
                        
                }

            }
            catch(Throwable error){
                //This catches same name issues and empty string.
                System.out.println(error.getMessage() + "Please pick another.");
                i--;
            }
        }

        //This assigns newCruise to cruiseList and resets scan and outputs success message before returning.
        cruiseList.add(newCruise);
        System.out.printf("\n%s cruise has been created.", newCruise.getCruiseName());
        scan.reset();
        return;

        
    }

    // Edit an existing cruise
    public static void editCruise() {

        // This method does not need to be completed
        System.out.println("The \"Edit Cruise\" feature is not yet implemented.");

    }

    // Add a New Passenger
    public static void addPassenger(Scanner newPassengerInput) {
        //I choose to pass the scanner from main rather than create multiple scanners
        
        System.out.println("Enter the new passenger's name: ");
        String newPassengerName = newPassengerInput.nextLine();

        // ensure new passenger name does not already exist
        for (Passenger eachPassenger: passengerList) {
            if (eachPassenger.getPassengerName().equalsIgnoreCase(newPassengerName)) {
                System.out.println("That passenger is already in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get cruise name for passenger, creates Cruise object
        System.out.println("Enter cruise name: ");
        String newCruiseName = newPassengerInput.nextLine();
        Cruise tempCruise = null;

        // ensure cruise exists, assigns cruise to tempCruise, else statement unneeded.
        for (Cruise eachCruise: cruiseList) {
            if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
                // cruise does exist, added this line to effectively manage passengers
                tempCruise = eachCruise;
            } /*else {  FIXME: this else statement causes a bug where each time one statement isnt true it exits, even if another is
                System.out.println("That cruise does not exist in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }*/
        }

        //This checks if tempCruis was assigned, if not notifies user and exits
        if(tempCruise == null){
            System.out.println("No cruise found.");
            return;
        }

        //This creates an empty string for room
        String room = "";

        // get room type, my modifications enable program to actually track passengers on cruise and preventsa overBooking
        do{
            System.out.println("Enter Room Type (BAL, OV, STE, or INT) (x) to exit: ");
            room = newPassengerInput.nextLine();
            // validate room type, while this look long it prevents overbooking by validating room availability and type
            if((tempCruise.getRoomBalcony() > 0 && room.equalsIgnoreCase("Bal")) || (tempCruise.getRoomOceanView() > 0 && room.equalsIgnoreCase("OV")) ||
            (tempCruise.getRoomSuite() > 0 && room.equalsIgnoreCase("STE")) || (tempCruise.getRoomInterior() > 0 && room.equalsIgnoreCase("INT"))){
                //This creates a new Passenger object, adds to passengerList and then notifies user before exiting loop
                Passenger newPassenger = new Passenger(newPassengerName, newCruiseName, room.toUpperCase(), tempCruise);
                passengerList.add(newPassenger);
                System.out.printf("\n%s has been added to %s with a %s room.", newPassenger, tempCruise.getCruiseName(), room);
                room = "x";
            }else{
                //This notifies user of unsuccessful room selection and clears room string.
                System.out.println("No selected rooms available. Please choose another");
                room = "";
                continue;
            }
            //For bug purposes, for some reason reset works.
            newPassengerInput.reset();

        }while(!(room.equalsIgnoreCase("X")));
    }

    // Edit an existing passenger
    public static void editPassenger() {

        // This method does not need to be completed
        System.out.println("The \"Edit Passenger\" feature is not yet implemented.");

    }

    // Method to check if input is a number
    //I had no reason to use this as I used try/catch exception handling instead.
    public static boolean isANumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false)
                return false;
        }
        return true;
    }


    public static char userCharCheck(Scanner scan, String userInput) {
        //This is a character validation method I made to validate only one character was input, Erro throw possible to catch, inputMismatch should no longer be possible.
        char userChar = 'z';
        try{
            if(userInput.length()>1){
                throw new Error("Invalid input, input should be 1 character long: ");
            }
            //assigns userChar as UpperCase char
            userChar = userInput.toUpperCase().charAt(0);
        }
        catch(InputMismatchException error){
            //This was useful for initial code, unlikely to be triggered now, but kept incase of weird issue.
            System.out.println("Invalid character, please select from options in \"[]\": ");
            userInput = scan.nextLine();
            userCharCheck(scan, userInput);
        }
        catch(Error error){
            //This happens when more than 1 character is entered
            System.out.println(error.getMessage());
            userInput = scan.nextLine();
            userCharCheck(scan, userInput);
        }
        
        //Returns character, and reprompts if invalid.
        return userChar;
    }

}
