//Joshua Kovacevich, 20201004, This is the Dog class for Grazioso Salvare software/Project Two
//It inherits/extends RescueAnimal class
public class Dog extends RescueAnimal {

    // Only required field for dog 
    private String breed;

    // THis is the default constructor for dog
    public Dog() {
        //This calls the RescueAnimal constructor, requests breed and then rebuilds variableList
        super();
        System.out.println("Please Enter Dog's Breed: ");
        String tempBreed = this.scnr.nextLine();
        setBreed(tempBreed);
        setVariableLists();
        
    }
    //This is a prebuilt constructor that allows me to simulate a database
    public Dog(String[] prebuiltList, String[] additionalList){
        //This calls super with prebuiltList then sets Breed and calls setVariableLists
        super(prebuiltList);
        setBreed(additionalList[0]);
        setVariableLists();

    }

    // This returns breed
    public String getBreed() {
        return breed;
    }

    // This is a modified setBreed that allows for validation of breed
    public void setBreed(String dogBreed) {
        //This validates an input breed and loops recursively if invalid breed
        switch(dogBreed.toLowerCase()){
            case "american pit bull terrier":
            case "beagle":
            case "belgian malinois":
            case "border collie":
            case "bloodhound":
            case "coonhound":
            case "english springer spaniel":
            case "german shepherd":
            case "german shorthaired pointer":
            case "golden retriever":
            case "labrador retriever":
            case "nova scotia duck tolling retriever":
            case "rough collie":
            case "smooth collie":
                this.breed = dogBreed;
                break;

            default:
                //This allows the user to output list of eligible breeds, exit input or directly enter breed.
                System.out.println("Please enter a valid dog breed or type \"t\" to see list or \"x\" to exit: ");
                String tempBreed = this.scnr.nextLine();
                if(tempBreed.equalsIgnoreCase("x")){
                    return;
                } else if(tempBreed.equalsIgnoreCase("t")){
                    System.out.println("american pit bull terrier");
                    System.out.println("beagle");
                    System.out.println("belgian malinois");
                    System.out.println("border collie");
                    System.out.println("bloodhound");
                    System.out.println("coonhound");
                    System.out.println("english springer spaniel");
                    System.out.println("german shepherd");
                    System.out.println("german shorthaired pointer");
                    System.out.println("golden retriever");
                    System.out.println("labrador retriever");
                    System.out.println("nova scotia duck tolling retriever");
                    System.out.println("rough collie");
                    System.out.println("smooth collie");
                    System.out.println("Please enter a valid dog breed: ");
                    tempBreed = this.scnr.nextLine();
                    this.setBreed(tempBreed);
                    
                } else{
                    this.setBreed(tempBreed);
                }
        }
    }

    @Override
    public void setVariableLists(){
        //This calls the Rescuelist variable list then adds dog breed at specified index for both lists
        super.setVariableLists();
        this.variableList.add(2, breed);
        this.variableNameList.add(2, "Breed: ");
    }

    @Override
    public void createAnimal(int optionSelect){
        //This if statement updates breed if its index is given, checks if update is service update
        // otherwise it defaults to super.createAnimal(optionSelect)
        if(optionSelect == 2){
            System.out.println("Please enter breed update: ");
            String newBreed = scnr.nextLine();
            this.setBreed(newBreed);
        } else if(this.variableNameList.indexOf("Service Country: ") < optionSelect){
            this.updateServiceInfo(optionSelect);
        }
        else{
            super.createAnimal(optionSelect);
        }

    }

}
