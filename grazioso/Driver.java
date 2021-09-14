//Joshua Kovacevich, 20201004, This is the driver class for Grazioso Salvare software/Project Two
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {

    //These two fields allow for me to loop through all new RescueAnimal objects (excluding validity testing ones)
    //And creates a central Constant list that allows for easy adding or removing of options paired with the main
    //Switch statement below in main method
    private static ArrayList<RescueAnimal> animalList = new ArrayList<RescueAnimal>();
    private static ArrayList<String> OPTION_LIST = new ArrayList<String>(
        Arrays.asList("Print Animal", "Add Animal", "Request/Reserve Animal", "Update Animal", "View Animals Phase", 
                        "Change to In Service/farm", "View Service Animals", "Report retirement/death", "Location/Status", "Exit"));
                        
    public static void printAnimal(RescueAnimal animal){
        //This Class allows printing of all animal information with a title for each peice of data
        //It is located in the driver class for easy/access but could be added to rescue class with minor modifications
        System.out.println("");
        for(int i = 0; i < animal.variableList.size(); i++){
            System.out.println(String.format("%s%s",animal.variableNameList.get(i), animal.variableList.get(i)));
        }
        System.out.println("");
        System.out.println("");
    }

    public static void createDog(String[] animalInfo, String[] dogInfo){
        //This method is specifically called for Dog, if empty arrays are passed
        //the program goes through default creation, if not it uses the arrays to build "database" simulation
        if(animalInfo.length == 0){
            Dog newDog = new Dog();
            animalList.add(newDog);
        }
        else{
            Dog newDog = new Dog(animalInfo, dogInfo);
            animalList.add(newDog);
        }
    }

    public static void createMonkey(String[] animalInfo, String[] monkeyInfo){
        //This method is specifically called for Monkey, if empty arrays are passed
        //the program goes through default creation, if not it uses the arrays to build "database" simulation
        if(animalInfo.length == 0){
            Monkey newMonkey = new Monkey();
            animalList.add(newMonkey);
        }
        else{
            Monkey newMonkey = new Monkey(animalInfo, monkeyInfo);
            animalList.add(newMonkey);
        }
    }

    public static void createAnimal(Scanner scan){
        //This is for adding a new animal into the "database", user selects animal type, invalid entry
        //Causes while loop to reoccur, this makes managing RescueAnimal objects very easy
        String optionString;
        int reselect = 0;
        System.out.println("Which type of animal would you like to make? (Dog/Monkey/Generic) ");
        while(reselect == 0){
            optionString = scan.nextLine();
            if(optionString.equalsIgnoreCase("dog")){
                Dog newDog = new Dog();
                animalList.add(newDog);
                reselect = 1;
            }else if(optionString.equalsIgnoreCase("monkey")){
                Monkey newMonkey = new Monkey();
                animalList.add(newMonkey);
                reselect = 1;
            }else if(optionString.equalsIgnoreCase("generic")){
                RescueAnimal newAnimal = new RescueAnimal();
                animalList.add(newAnimal);
                reselect = 1;
            }
        }
        return;

    }
    public static void animalDisplayLimited(Scanner scan, String secondVariableName, String secondVariableValue) {
        //This method takes the secondVariableName and secondVariableValue, asks for a country and then determines if 
        //If there is an animal in chosen country that meets both critea, this was by far the most difficult part of
        //this project for me as nesting if/else statements can be quite confusing initially it contained extremely
        //confusing branching

        //This initializes the variables indexLocation and countryIndex to prevent compile errors then declares countryName and limited selection
        int indexLocation = 0;
        int countryIndex = 0;
        String countryName;
        ArrayList<RescueAnimal> limitedSelection = new ArrayList<RescueAnimal>();

        //This Rescue animal is created purely to check if input is valid
        RescueAnimal validityAnimal = new RescueAnimal(-1);

        //This takes a user input string and verifys it is a valid country with genric rescueAnimal object
        System.out.println("Which country are you looking for? ");
        countryName = scan.nextLine();
        validityAnimal.setTrainingLocation(countryName);
        countryName = validityAnimal.getTrainingLocation();
        
        //This for loops allows me to go through each animalList object and check if they meet criteria required
        for(int i = 0; i < animalList.size(); i++){
            //This assignment ensures each index location is correct for the given RescueAnimal
            indexLocation = animalList.get(i).variableNameList.indexOf(secondVariableName);
            countryIndex = animalList.get(i).variableNameList.indexOf("Training Location: ");

            //Since both current uses of this method require the correct country checking that seemed the most logical option
            if(countryName.equalsIgnoreCase(animalList.get(i).variableList.get(countryIndex).toString())){

                //Here we check if we are checking for an unreserved animal (the first condition is only passed by reserve option)
                //and if the animal is reserved or not by casting a boolean value to string
                if(secondVariableValue.equals("false") && secondVariableValue.equalsIgnoreCase(animalList.get(i).variableList.get(indexLocation).toString())){
                    //This is the branch used when requesting/reserving animal
                        limitedSelection.add(animalList.get(i));
                }

                //This checks the variable value (as a string) vs the desired value
                else if(secondVariableValue.equalsIgnoreCase(animalList.get(i).variableList.get(indexLocation).toString())){
                    //This is the branch that matches secondVariable value assuming it is not for "Reserved: " 
                    limitedSelection.add(animalList.get(i));
                }

            }
        }

        //This initializes selected then determines if any animals meet the desired criteria
        int selected;
        if(limitedSelection.size() == 0){
            System.out.println("Sorry, no animals meet your criteria.");
            return;
        }
        //This is the brach that goes through reserving an animal
        else if(secondVariableValue.equals("false")){
            //Prompts user then calls choosenByName allowing the user to choose animal by name
            System.out.println("If you would not like to select type -1: ");
            selected = chooseByName(scan, limitedSelection);
            //This is for when no reservation occurs
            if(selected == -1){
                System.out.println("No Animal selected.");
                return;
            }
            //this reserve animal and prints it
            else{
                limitedSelection.get(selected).setReserved(true);
                printAnimal(limitedSelection.get(selected));
            }
        }

        //This displays names of all animals meeting criteria and then allows selection of and printing of selected animal
        else{
            System.out.println("Select an animal to view, type -1 to exit: ");
            selected = chooseByName(scan, limitedSelection);
            if(selected == -1){
                System.out.println("No Animal selected.");
                return;
            }
            else{
                printAnimal(limitedSelection.get(selected));
            }
        }
    }

    public static int chooseByName(Scanner scan, ArrayList<RescueAnimal>animalList) {
        //This is a reusable method that prints all names of animals in list, and allows selection of animal extremely handy
        System.out.println("Which Animal would you like? ");
        for(int i = 0; i < animalList.size(); i++){
            System.out.println(String.format("Press %s for %s", i, animalList.get(i).variableList.get(0)));
        }
        int optionSelect = scan.nextInt();
        return optionSelect;
    }

    public static void animalUpdate(Scanner scan, int optionSelect){
        //This case prints animal names, allows user to select an animal, then prints the trait names
        //and allows the user to directly modify a selected trait.
        optionSelect = chooseByName(scan, animalList);
        System.out.println("Which trait would you like to update?");

        //This prints all traits with associated index
        for(int i = 0; i < animalList.get(optionSelect).variableNameList.size(); i++){
            System.out.println(String.format("Press %s for %s", i, animalList.get(optionSelect).variableNameList.get(i)));
        }
        //This initializes newOptionSelect as int and assigns it to next input int
        int newOptionSelect = scan.nextInt();

        //This determines if the selected trait is a service information peice or other and uses appropriate method for update
        if(newOptionSelect < (animalList.get(optionSelect).variableList.size() - animalList.get(optionSelect).SERVICE_TRAITS -1)){
            animalList.get(optionSelect).createAnimal(newOptionSelect);
        }
        else{
            animalList.get(optionSelect).updateServiceInfo(newOptionSelect);
        }
    }

    public static void animalStatusDisplay(Scanner scan, String optionString, ArrayList<RescueAnimal>animalList) {
        //this is similar to animalDisplayLimited but only uses training status to find desired output, if optionString
        //is "" it will prompt user for desired status, otherwise it automatically executes
        if(optionString.equals("")){
            System.out.println("Please enter Training Status: ");
            scan.nextLine();
            optionString = scan.nextLine();
        }
        //This is the declaration of indexLocation prior to for loops assignment
        int indexLocation;
        for(int i = 0; i < animalList.size(); i++){
            //This for loop updates individual indexLocation then checks if the status matches desired status
            indexLocation = animalList.get(i).variableNameList.indexOf("Training Status: ");
            if(optionString.equalsIgnoreCase(animalList.get(i).variableList.get(indexLocation).toString())){
                //This directly prints any animal in chosen Training Status
                printAnimal(animalList.get(i));
            }
        }
    }

    public static void updateStatusLimited(Scanner scan) {
        //This method loops recursively and can set 1 of four status, does not deal with phase status
        
        //optionSelect displays all animalList animals then scan ensures empty input
        int optionSelect = chooseByName(scan, animalList);
        scan.nextLine();

        //Prompts user for Name and status update and stores input to variables, I was having issues with blank input which is why
        //I strip white space and clear the line above prior to taking user input
        System.out.println("Enter status update\"Retired\", \"Deceased\", \"farm\" or \"In Service\" type \"Exit\" to exit: ");
        String serviceUpdate = scan.nextLine().stripLeading();

        //This exits recursive loop
        if(serviceUpdate.equalsIgnoreCase("exit")){
            return;
        }

        //This is the meat of the loop, potentially easier for a switch case here but it seems a little easier with if/else branch
        if(serviceUpdate.equalsIgnoreCase("farm")){
            animalList.get(optionSelect).incrementTrainingStatus(false, false);
        } 
        else if(serviceUpdate.equalsIgnoreCase("in service")){
            animalList.get(optionSelect).incrementTrainingStatus(true, true);
        } 
        else if(serviceUpdate.equalsIgnoreCase("retired")){
            animalList.get(optionSelect).setTrainingStatus("Retired");
        } 
        else if(serviceUpdate.equalsIgnoreCase("deceased")){
            animalList.get(optionSelect).setTrainingStatus("Deceased");
        } 
        else{
            System.out.println("Invalid entry, try again");
            updateStatusLimited(scan);
        }
        
        
    }
    
    public static void generateDataBase(){
        /*This generates a quasi database of animals, this enables the end user to see animals with
        having to generate them first and while not required for program operation it enhances functionality
        Format is: 
        name, type, gender, acquisitionSource, trainingLocation, trainingStatus, inServiceCountry, inServiceCity, 
        inServiceAgency, inServicePOC, inServiceEmail, inServicePhone, inServicePostalAddress, 
        age, weight, reserved

        for dog add:
        breed

        for monkey add:

        tailLength, height, bodyLength, species, torsoMeasurement, skullMeasurement, neckMeasurement
        */


        //This is all just code to artificially represent a database, as such its pretty messy and is in no way
        //a good representation of code
        String[] generatorList = {"Pakkun", "Dog", "Male", "Alleyway", "Japan", "In Service", "Japan", "Tokyo", 
        "TPD", "Kakashi Hatake", "Kakashi@gmail.com", "555-555-5555", "Leaf Village, General Delivery", 
        "74", "12", "true"};
        String[] additionList = {"bloodhound"};
        createDog(generatorList, additionList);
        
        generatorList[0] = "Bull";
        generatorList[13] = "28";
        generatorList[14] = "85";
        additionList[0] = "American pit bull terrier";
        createDog(generatorList, additionList);

        generatorList[0] = "Akimaru";
        generatorList[13] = "10";
        generatorList[14] = "65";
        additionList[0] = "Labrador retriever";
        createDog(generatorList, additionList);

        generatorList[0] = "Doggy McPuppyFace the fifth";
        generatorList[13] = "1";
        generatorList[14] = "10";
        generatorList[15] = "false";
        additionList[0] = "German shorthaired pointer";
        createDog(generatorList, additionList);



        String[] generatorList1 = {"Monk", "Monkey", "Male", "Peru", "United States", "Intake", "N/A", 
        "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "12", "35", "true"};
        String[] additionList1 = {"3", "8", "4", "Capuchin", "55", "22", "14"};
        createMonkey(generatorList1, additionList1);
        
        generatorList1[0] = "Curious George";
        generatorList1[13] = "14";
        generatorList1[14] = "12";
        additionList1[3] = "Tamarin";
        createMonkey(generatorList1, additionList1);

        generatorList1[0] = "Bilbo Baggins";
        generatorList1[13] = "111";
        generatorList1[14] = "45";
        additionList1[3] = "Guenon";
        createMonkey(generatorList1, additionList1);

        generatorList1[0] = "Bubbles";
        generatorList1[13] = "2";
        generatorList1[14] = "22";
        generatorList1[15] = "false";
        additionList1[3] = "Marmoset";
        createMonkey(generatorList1, additionList1);
    }

    public static void main(String[] args) {
        //This is a scanner to make input easier, field assignment raises errors with usage so I initialize here and pass to other methods
        Scanner scan = new Scanner(System.in); 

        //This is a call to a generator method that makes a qausi database, 
        //purely to have initial functionality of program
        generateDataBase();

        // This while loop executes the main program until user ends
        int loopExit = 0;
        while(loopExit == 0){

            //This displays user options and then retrieves input for selection
            System.out.println("Please enter the option you would like to execute from options below.");
            for(int i = 0; i < OPTION_LIST.size(); i++){
                System.out.println(String.format("Press %s for %s", (i), OPTION_LIST.get(i)));
            }
            int optionSelect = scan.nextInt();

            //If the animalList is empty this stops user from doing anything besides adding or exiting loop
            //No longer required but would serve a purpose without generateDataBase method
            if(animalList.size() == 0){
                switch(optionSelect){
                    case 1:
                    case 9:
                        break;
                    default:
                        System.out.println("Sorry, no animals in database, please add animals before continuing.");
                        continue;
                }
            }

            //This is the meat and potatoes of the driver class, this switch statement controls the entire functionality
            //of the program, I made methods for almost all code in the switch statement as it makes it significantly easier to understand
            //and forced me to consolidate redundent code into methods that meet all functionality requirements
            switch(optionSelect){
                case 0:
                    //This is for viewing an individual animal
                    optionSelect = chooseByName(scan, animalList);
                    scan.nextLine();
                    printAnimal(animalList.get(optionSelect));
                    break;
                case 1:
                    createAnimal(scan);
                    break;
                case 2:
                    animalDisplayLimited(scan, "Reserved: ", "false");
                    break;
                case 3:
                    animalUpdate(scan, optionSelect);
                    break;
                case 4:
                    animalStatusDisplay(scan, "", animalList);
                    break;
                case 5:
                    updateStatusLimited(scan);                                
                    break;
                case 6:
                    //Much like case 4 this prints all animals with "in service" status
                    animalStatusDisplay(scan, "In Service", animalList);
                    break;
                case 7:
                    updateStatusLimited(scan);
                    break;         
                case 8:
                    scan.nextLine();
                    System.out.println("Please enter what status you are looking for: ");
                    String secondVariableValue = scan.nextLine();
                    animalDisplayLimited(scan, "Training Status: ", secondVariableValue);
                    break;
                case 9:
                //Exits program
                    loopExit = 1;
                    break;
                default:
                    //Notifys user of invalid entry, allows loop to reset.
                    System.out.println("Please enter a valid option to continue.");

            }
        }//While loop ends
    }

}
