//Joshua Kovacevich, 20201004, This is the RescueAnimal class for Grazioso Salvare software/Project Two
//It is the parent/superClass for Dog.java and Monkey.Java
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class RescueAnimal {

    // These are the required fields for the project, please note that I changed the simpleDateFormat types to
    // Date types as I could find no way to utilize 4 redundent formatters and opted for one reusable formatter
    // I was unable to find a way to save a date in a simpleDateFormat variable
    private String name;
    private String type;
    private String gender;
    private int age;
    private float weight;
    private Date acquisitionDate;
    private Date statusDate;
    private String acquisitionSource;
    private Boolean reserved;

    private String trainingLocation;
    private Date trainingStart;
    private Date trainingEnd;
    private String trainingStatus;

    private String inServiceCountry;
    private String inServiceCity;
    private String inServiceAgency;
    private String inServicePOC;
    private String inServiceEmail;
    private String inServicePhone;
    private String inServicePostalAddress;

    
    //These are additional fields I implemented to make the program more functional and usable
    private SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd/yyyy");
    protected ArrayList<Object> variableList = new ArrayList<Object>();
    protected ArrayList<String> variableNameList = new ArrayList<String>();
    public final int SERVICE_TRAITS = 8;
    public Scanner scnr = new Scanner(System.in);
    private Date serviceDate;

    // This is a default constructor that initializes all required fields and then builds twovariable lists with those values
    public RescueAnimal() {
        //This is the only way I have found to successfully assign a date value in correct format
        try{
            Date constructorDate = dateFormater.parse("01/01/1965");
            acquisitionDate = statusDate = trainingStart = trainingEnd = serviceDate = constructorDate;
        } catch(ParseException eParseException){
            eParseException.printStackTrace();
            acquisitionDate = statusDate = trainingStart = trainingEnd = serviceDate = null;
        }
        

        //This was the easiest way to assign a bunch of variables without multiple lines for the same values
        name = type = gender = acquisitionSource = trainingLocation = trainingStatus = "None";
        inServiceCountry = inServiceCity = inServiceAgency = inServicePOC = inServiceEmail = inServicePhone = inServicePostalAddress = "N/A";

        //These are the variables that needed individual assignment
        age = 0;
        weight = 0;
        reserved = false;

        //This calls setVaraibleLists, a method I created for easy testing/display, check below for more information, it combines two setters 
        //for ease as they are dependent on each other for their purposes.
        setVariableLists();
        createAnimal(-1);
        setVariableLists();

    }

    //this constructor is for a default build that is only used to validate user input and is not generally used
    public RescueAnimal(int one){
        name = type = gender = acquisitionSource = trainingLocation = trainingStatus = "None";
        inServiceCountry = inServiceCity = inServiceAgency = inServicePOC = inServiceEmail = inServicePhone = inServicePostalAddress = "N/A";
        try{
            Date constructorDate = dateFormater.parse("01/01/1965");
            acquisitionDate = statusDate = trainingStart = trainingEnd = serviceDate = constructorDate;
        } catch(ParseException eParseException){
            eParseException.printStackTrace();
            acquisitionDate = statusDate = trainingStart = trainingEnd = serviceDate = null;
        }
        age = 0;
        weight = 0;
        reserved = false;
        setVariableLists();
    }

    //This is a prebuilt constructor that allows me to simulate a database
    public RescueAnimal(String[] prebuiltList){
        //This assigns each value baised of a predefined ordered list the spacing is for ease and clarity only
        // All Dates are set to default date of 06/20/2010 
        name = (String)prebuiltList[0];
        type = (String)prebuiltList[1];
        gender = (String)prebuiltList[2];
        acquisitionSource = (String)prebuiltList[3];
        trainingLocation = (String)prebuiltList[4];
        trainingStatus = (String)prebuiltList[5];

        inServiceCountry = (String)prebuiltList[6];
        inServiceCity = (String)prebuiltList[7];
        inServiceAgency = (String)prebuiltList[8];
        inServicePOC = (String)prebuiltList[9];
        inServiceEmail = (String)prebuiltList[10];
        inServicePhone = (String)prebuiltList[11];
        inServicePostalAddress = (String)prebuiltList[12];

        age = Integer.parseInt(prebuiltList[13]);
        weight = Float.parseFloat(prebuiltList[14]);
        reserved = Boolean.parseBoolean(prebuiltList[15]);


        //This is the way I am forced to parse the date as it believes I could be wrong even when it is a hardcode
        try{
            Date constructorDate = dateFormater.parse("06/20/2010");
            acquisitionDate = statusDate = trainingStart = trainingEnd = serviceDate = constructorDate;
        } catch(ParseException eParseException){
            eParseException.printStackTrace();
            acquisitionDate = statusDate = trainingStart = trainingEnd = serviceDate = null;
        }
        this.setVariableLists();
    }

    public void createAnimal(int optionSelect){
        //This method allows me to create an animal and input all traits, or select 1 specific trait to update

        //This is a String Type variable for inputting information
        String tempUserInput;

        //This forloop loops through all generic variable list items and assigns them excluding service updates
        for(int i = 0; i < (this.variableList.size() - this.SERVICE_TRAITS); i++){

            //When updating a specific trait the index is passed as optionSelect, during initial creation -1 is passed
            if(!(optionSelect == -1)){
                i = optionSelect;
            }

            //These if statements effectively ignore non generic assignment and allows child classes to handle them directly
            //There is likely a better way to handle this but I believe it would take a large rework of the code so for ease
            //considering the limited scope this is the way, if this was production code I would reformat the children to make
            //this section unnecessary
            if(this.variableNameList.get(i).equals("Breed: ")){
                i++;
            }
            else if((this.getType().equalsIgnoreCase("Monkey") && ((i > 5) && (i <= 11)))){
                i++;
                continue;
            }
            else if(this.variableNameList.get(i).equals("Species: ")){
                i++;
            }
            
            //This promprts the user for specific trait and accepts their input (as a string currently)
            System.out.println(String.format("Please enter %s", this.variableNameList.get(i)));
            tempUserInput = scnr.nextLine();

            //This switch case sets all generic values for a new RescueAnimal object
            switch(this.variableNameList.get(i)){
                case "Name: ":
                    this.name = tempUserInput;
                    break;
                case "Type: ":
                    this.setType(tempUserInput);
                    break;
                case "Gender: ":
                    this.gender = tempUserInput;
                    break;
                case "Age: ":
                    this.age = Integer.parseInt(tempUserInput);
                    break;
                case "Weight: ":
                    this.weight = Float.parseFloat(tempUserInput);
                    break;
                case "Acquisition Date: ":
                    this.setAcquisitionDate(this.returnFormatedDate(tempUserInput));
                    break;
                case "Status Date: ":
                    this.setStatusDate(this.returnFormatedDate(tempUserInput));
                    break;
                case "Acquisition Source: ":
                    this.setAcquisitionSource((String)tempUserInput);
                    break;
                case "Reserved: ":
                    this.setReserved(Boolean.parseBoolean((String)tempUserInput));
                    break;
                case "Training Location: ":
                    this.setTrainingLocation((String)tempUserInput);
                    break;
                case "Training Start: ":
                    this.setTrainingStart(this.returnFormatedDate(tempUserInput));
                    break;
                case "Training End: ":
                    this.setTrainingEnd(this.returnFormatedDate(tempUserInput));
                    break;
                case "Training Status: ":
                    this.setTrainingStatus((String)(tempUserInput));
                    break;
                default:
                    System.out.println("Something went wrong. Method createAnimal, RescueAnimal default case");
                    break;
            }

            //This ensures variable lists are updated appropriately
            this.setVariableLists();

            //This ends the loop after selected object is updated
            if(!(optionSelect == -1)){
                return;
            }
            
        }

    }
    public Date returnFormatedDate(String tempUserInput){
        //This accepts a user input string, if it is N/A nothing is assigned and date is set to default date
        //else is loops until an acceptable format is input and then the method returns that date
        int i = 0;
        Date date = new Date();
        while(i == 0){
            try{
                if(tempUserInput.equalsIgnoreCase("N/A")){
                    date = this.dateFormater.parse("01/01/1965");
                    i = 1;
                    continue;
                }
                date = this.dateFormater.parse((String)tempUserInput);
                i = 1;
                
            } catch(ParseException eParseException){
                System.out.println("Please Enter date in format \"MM/DD/YYYY\" if N/A type \"N/A\": ");
                tempUserInput = scnr.nextLine();
            }
        }
        return date;

    }

    public void incrementTrainingStatus(boolean Increment, boolean inService){
        //This is a training incrementor that allows for assignment to farm and also direct
        //Assignment to In service without incrementing training first, initially created for testing
        //it serves a purpose now mainly for catching assignment issue and direct assignment for farm or Inservice
        String currentStatus = this.getTrainingStatus();
        if(Increment == false){
            this.setTrainingStatus("farm");
            return;
        } else if(inService){
            this.setTrainingStatus("In Service");
            this.updateServiceInfo(-1);
            return;
        }
        switch(currentStatus.toLowerCase()){
            case "intake":
                this.setTrainingStatus("Phase I");
                break;
            case "phase i":
                this.setTrainingStatus("Phase II");
                break;
            case "phase ii":
                this.setTrainingStatus("Phase III");
                break;
            case "phase iii":
                this.setTrainingStatus("Phase IV");
                break;
            case "phase iv":
                this.setTrainingStatus("Phase V");
                break;
            case "phase v":
                this.setTrainingStatus("in service");
                this.updateServiceInfo(-1);
                break;
            case "in service":
                //This effective catchs any over incrementing done unintentionally
                this.setTrainingStatus("in service");
                break;
            default:
                System.out.println("Something went wrong, Please manually input training status with proper formatting (ie \"Phase I\"): " + currentStatus);
                String trainingStatusManual = this.scnr.nextLine();
                this.setTrainingStatus(trainingStatusManual);
                return;
        }

        //This ensures variable lists are updated appropriately
        this.setVariableLists();
        return;
    }

    
    public void updateServiceInfo(int optionSelect){
        //This method specifically handles service related updates outside of Status, it allows direct individual 
        //assignment with the optionSelect variable

        //This finds the index of "Service Country: " and sets the locator to that index and declares tempUserInput 
        int serviceLocator = this.variableNameList.indexOf("Service Date: ");
        String tempUserInput;

        //This loop is used to prompt for each in service trait and  assigns a value to it.
        // There is an allowed bypass for specific traits
        for(int i = serviceLocator; i < (serviceLocator + this.SERVICE_TRAITS); i++){

            //This allows direct assignment to one selected trait
            if(optionSelect > -1){
                i = optionSelect;
            }
            //If initially putting in service this else if statement prompts user for date once
            else if(i == serviceLocator){
                System.out.println("What day did animal enter Service(MM/DD/YYYY): ");
            }

            System.out.println(String.format("Please enter %s", this.variableNameList.get(i)));
            tempUserInput = scnr.nextLine();
            if(tempUserInput.equals("")){
                tempUserInput = scnr.nextLine();
            }
            //THis switch case updates selected trait with its setter
            switch(this.variableNameList.get(i)){
                case "Service Date: ":
                    this.setServiceDate(this.returnFormatedDate(tempUserInput));
                    break;
                case "Service Country: ":
                    this.setInServiceCountry(tempUserInput);
                    break;
                case "Service City: ":
                    this.setInServiceCity(tempUserInput);
                    break;
                case "Service Agency: ":
                    this.setInServiceAgency(tempUserInput);
                    break;
                case "Service POC: ":
                    this.setInServicePOC(tempUserInput);
                    break;
                case "Service Email: ":
                    this.setInServiceEmail(tempUserInput);
                    break;
                case "Service Phone: ":
                    this.setInServicePhone(tempUserInput);
                    break;
                case "Service Postal Address: ":
                    this.setInServicePostalAddress(tempUserInput);
                    break;
                default:
                    System.out.println("Something went wrong. Method updateServiceInfo, RescueAnimal default case");
            }
            
            
            //This updates variable lists then exits the forloop/method
            if(optionSelect > -1){
                this.setVariableLists();
                return;
            }
            
        }

        //This ensures variable lists are updated appropriately
        this.setVariableLists();

    }

    



    //This is all the default setters and getters methods. If a method is required to be
    //Modified there will be a comment added to that method. Note, all date getters output a string
    //rather than a date type, this enables a smother delivery of dates without significant redundant code
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        //This is a while loop that enforces type requirements, loops recursively with incorrect input
        if(type.equalsIgnoreCase("Dog")){
            this.type = "Dog";
        }
        else if(type.equalsIgnoreCase("Monkey")){
            this.type = "Monkey";
        }
        else if(type.equalsIgnoreCase("Animal")){
            this.type = "Animal";
        }
        else{
            System.out.println("Incorrect input, please enter Dog, Monkey or Animal: ");
            type = this.scnr.nextLine();
            setType(type);
        }
        //Updates variable lists
        this.setVariableLists();  
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getAcquisitionDate() {
        //This is used to return string format of date, I had no need of a date datatype output
        return dateFormater.format(this.acquisitionDate);
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getStatusDate() {
        //This is used to return string format of date, I had no need of a date datatype output
        return dateFormater.format(this.statusDate);
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getAcquisitionSource() {
        return acquisitionSource;
    }

    public void setAcquisitionSource(String acquisitionSource) {
        this.acquisitionSource = acquisitionSource;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
        //This is required due to option to specifically ability to reserve animal at any given time
        this.setVariableLists();
    }

    public String getTrainingLocation() {
        return trainingLocation;
    }

    public void setTrainingLocation(String trainingLocation) {
        //This ensures the training location is a valid country and if incorrect input recursively loops after
        //obtaining a new input
        switch(trainingLocation.toLowerCase()){
            case "chile":
            case "greece":
            case "japan":
            case "madagascar":
            case "singapore":
            case "south korea":
            case "turkey": 
            case "united kingdom": 
            case "united states":
                this.trainingLocation = trainingLocation.toLowerCase();
                break;
            default:
            //This prompts user after displaying all valid countries then loops recursively
                System.out.println("We have locations in Chile, Greece, Japan, Madagascar, Singapore," +
                                        " South Korea, Turkey, the United Kingdom, and the United States.");
                System.out.println("Please enter a valid training location: ");
                String userInput = scnr.nextLine();
                setTrainingLocation(userInput);
                break;
        }
        return;
    }

    public String getTrainingStart() {
        //This is used to return string format of date, I had no need of a date datatype output
        return dateFormater.format(this.trainingStart);
    }

    public void setTrainingStart(Date trainingStart) {
        this.trainingStart = trainingStart;
    }

    public String getTrainingEnd() {
        //This is used to return string format of date, I had no need of a date datatype output
        return dateFormater.format(this.trainingEnd);
    }

    public void setTrainingEnd(Date trainingEnd) {
        this.trainingEnd = trainingEnd;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        //This switch case enables me to properly check training status and validate it before entry
        //String formatting is strictly for pretty output
        switch(trainingStatus.toLowerCase()){
            case "intake":
            case "phase i":
            case "phase ii":
            case "phase iii":
            case "phase iv":
            case "phase v":
                this.trainingStatus = trainingStatus.toUpperCase().charAt(0) + trainingStatus.replace('i', 'I').substring(1);
                this.trainingStatus = this.trainingStatus.replace('v', 'V') ;
                System.out.println("Status successfully set to " + this.trainingStatus);
                break;
            case "in service":
                //This allows bypass of updating all service info when upgrading to inservice
                this.trainingStatus = trainingStatus.toUpperCase().charAt(0) + trainingStatus.substring(1);
                System.out.println("Status successfully set to " + this.trainingStatus);
                break;
            case "retired":
                this.trainingStatus = trainingStatus.toUpperCase().charAt(0) + trainingStatus.substring(1);
                break;
            case "deceased":
                this.trainingStatus = trainingStatus.toUpperCase().charAt(0) + trainingStatus.substring(1);
                break;
            case "farm":
                this.trainingStatus = trainingStatus.toUpperCase().charAt(0) + trainingStatus.substring(1);
                break;
            default:
                //This determines invalid input and loops recursively until valid input is detected
                System.out.println("Something went wrong setTrainingStatus, Please manually input training status with proper formatting: ");
                String trainingStatusManual = this.scnr.nextLine();
                this.setTrainingStatus(trainingStatusManual);
        }
        
        //Ensures variable lists are uptdated
        setVariableLists();
    }

    public String getServiceDate() {
         //This is used to return string format of date, I had no need of a date datatype output
        return dateFormater.format(this.serviceDate);   
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getInServiceCountry() {
        return inServiceCountry;
    }

    public void setInServiceCountry(String inServiceCountry) {
        this.inServiceCountry = inServiceCountry;
    }

    public String getInServiceCity() {
        return inServiceCity;
    }

    public void setInServiceCity(String inServiceCity) {
        this.inServiceCity = inServiceCity;
    }

    public String getInServiceAgency() {
        return inServiceAgency;
    }

    public void setInServiceAgency(String inServiceAgency) {
        this.inServiceAgency = inServiceAgency;
    }

    public String getInServicePOC() {
        return inServicePOC;
    }

    public void setInServicePOC(String inServicePOC) {
        this.inServicePOC = inServicePOC;
    }

    public String getInServiceEmail() {
        return inServiceEmail;
    }

    public void setInServiceEmail(String inServiceEmail) {
        this.inServiceEmail = inServiceEmail;
    }

    public String getInServicePhone() {
        return inServicePhone;
    }

    public void setInServicePhone(String inServicePhone) {
        this.inServicePhone = inServicePhone;
    }

    public String getInServicePostalAddress() {
        return inServicePostalAddress;
    }

    public void setInServicePostalAddress(String inServicePostalAddress) {
        this.inServicePostalAddress = inServicePostalAddress;
    }

    public ArrayList<Object> getVariableList() {
        return variableList;
    }

    public ArrayList<String> getVariableNameList(){
        return variableNameList;
    }

    public void setVariableLists() {
        //While not required for the project, this creates two  arraylists with all variable values and names and makes handling the data in testing
        //significantly more efficient, it would also facilitate easy printing with for loops for data display and is built to facilitate being used
        // by all child classes.

        //This is to ensure both arraylists are empty prior to adding data
        this.variableList.clear();
        this.variableNameList.clear();

        //This builds the variable list with values of each field in class
        this.variableList.add(this.name);
        this.variableList.add(this.type);
        this.variableList.add(this.gender);
        this.variableList.add(this.age);
        this.variableList.add(this.weight);
        this.variableList.add(this.getAcquisitionDate());
        this.variableList.add(this.getStatusDate());
        this.variableList.add(this.acquisitionSource);
        this.variableList.add(this.reserved);

        this.variableList.add(this.trainingLocation);
        this.variableList.add(this.getTrainingStart());
        this.variableList.add(this.getTrainingEnd());
        this.variableList.add(this.trainingStatus);

        this.variableList.add(this.getServiceDate());
        this.variableList.add(this.inServiceCountry);
        this.variableList.add(this.inServiceCity);
        this.variableList.add(this.inServiceAgency);
        this.variableList.add(this.inServicePOC);
        this.variableList.add(this.inServiceEmail);
        this.variableList.add(this.inServicePhone);
        this.variableList.add(this.inServicePostalAddress);

        //This builds a name Arraylist with the same indexs of the variable for easy print/data display
        this.variableNameList.add("Name: ");
        this.variableNameList.add("Type: ");
        this.variableNameList.add("Gender: ");
        this.variableNameList.add("Age: ");
        this.variableNameList.add("Weight: ");
        this.variableNameList.add("Acquisition Date: ");
        this.variableNameList.add("Status Date: ");
        this.variableNameList.add("Acquisition Source: ");
        this.variableNameList.add("Reserved: ");

        this.variableNameList.add("Training Location: ");
        this.variableNameList.add("Training Start: ");
        this.variableNameList.add("Training End: ");
        this.variableNameList.add("Training Status: ");

        this.variableNameList.add("Service Date: ");
        this.variableNameList.add("Service Country: ");
        this.variableNameList.add("Service City: ");
        this.variableNameList.add("Service Agency: ");
        this.variableNameList.add("Service POC: ");
        this.variableNameList.add("Service Email: ");
        this.variableNameList.add("Service Phone: ");
        this.variableNameList.add("Service Postal Address: ");
    }

}
