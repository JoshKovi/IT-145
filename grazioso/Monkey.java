//Joshua Kovacevich, 20201004, This is the Monkey class for Grazioso Salvare software/Project Two
//It inherits/extends RescueAnimal class

public class Monkey extends RescueAnimal{
    //These are the required fields for the project I use species rather than breed as they meet the same requirement and the
    //specification document seemed to be ambiguous in that regard
    private float tailLength;
    private float height;
    private float bodyLength;
    private String species;
    private float torsoMeasurement;
    private float skullMeasurement;
    private float neckMeasurement;

    //This default Monkey constructor uses the super class constructor
    public Monkey(){
        super();
    }

    //This is a prebuilt constructor for simulating a database to make the program functional without user input objects
    public Monkey(String[] prebuiltList, String[] additionalList){
        //This calls super with prebuiltList then sets monkey specific traits and calls setVariableLists
        super(prebuiltList);
        tailLength = Float.parseFloat(additionalList[0]);
        height = Float.parseFloat(additionalList[1]);
        bodyLength = Float.parseFloat(additionalList[2]);
        species = additionalList[3];
        torsoMeasurement = Float.parseFloat(additionalList[4]);
        skullMeasurement = Float.parseFloat(additionalList[5]);
        neckMeasurement = Float.parseFloat(additionalList[6]);
        setVariableLists();
    }

    //These are all standard setters and getters, if any are updated there will be comments associated with those updates
    public float getTailLength() {
        return tailLength;
    }

    public void setTailLength(float tailLength) {
        this.tailLength = tailLength;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(float bodyLength) {
        this.bodyLength = bodyLength;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        //This validates an input breed and loops recursively if invalid species type
        switch(species.toLowerCase()){
            case "capuchin":
            case "guenon":
            case "macaque":
            case "marmoset":
            case "squirrel monkey":
            case "tamarin":
                this.species = species;
                break;

            default:
                //This allows the user to output list of eligible species, exit input or directly enter breed.
                System.out.println("Please enter a valid monkey species or type \"t\" to see list or \"x\" to exit: ");
                String tempSpecies = this.scnr.nextLine();
                if(tempSpecies.equalsIgnoreCase("x")){
                    return;
                } else if(tempSpecies.equalsIgnoreCase("t")){
                    System.out.println("capuchin");
                    System.out.println("guenon");
                    System.out.println("macaque");
                    System.out.println("marmoset");
                    System.out.println("squirrel monkey");
                    System.out.println("tamarin");
                    tempSpecies = this.scnr.nextLine();
                    this.setSpecies(tempSpecies);
                    
                } else{
                    this.setSpecies(tempSpecies);
                }
        }
    }

    public float getTorsoMeasurement() {
        return torsoMeasurement;
    }

    public void setTorsoMeasurement(float torsoMeasurement) {
        this.torsoMeasurement = torsoMeasurement;
    }

    public float getSkullMeasurement() {
        return skullMeasurement;
    }

    public void setSkullMeasurement(float skullMeasurement) {
        this.skullMeasurement = skullMeasurement;
    }

    public float getNeckMeasurement() {
        return neckMeasurement;
    }

    public void setNeckMeasurement(float neckMeasurement) {
        this.neckMeasurement = neckMeasurement;
    }
    
    @Override
    public void setVariableLists(){
        //This calls the super setVariableLists then adds the 7 individual fields to monkey in a specific order and location
        super.setVariableLists();
        this.variableList.add(2, species);
        this.variableNameList.add(2, "Species: ");
        this.variableList.add(6, tailLength);
        this.variableNameList.add(6, "Tail Length: ");
        this.variableList.add(7, height);
        this.variableNameList.add(7, "Height: ");
        this.variableList.add(8, bodyLength);
        this.variableNameList.add(8, "Body Length: ");
        this.variableList.add(9, torsoMeasurement);
        this.variableNameList.add(9, "Torso Measurement: ");
        this.variableList.add(10, skullMeasurement);
        this.variableNameList.add(10, "Skull Measurement: ");
        this.variableList.add(11, neckMeasurement);
        this.variableNameList.add(11, "Neck Measurement: ");  
    }

    @Override
    public void createAnimal(int optionSelect){
        //This method uses the super.createAnimal() method then after its completion sets species,
        //from there it uses a do/while loop to loop through and set the remaining monkey specific traits

        //Calls super.createAnimal method
        if(optionSelect == -1){
            super.createAnimal(optionSelect);
        }

        //This variable enables breaking of do/while loop on a non initial run
        boolean initialSetup = true;
        if(optionSelect != -1){
            initialSetup = false;
        }


        //sets specices value, then if it is building monkey for first time sets option select in preperation of while loop
        if((optionSelect == 2 || optionSelect == -1) && "Species: ".equalsIgnoreCase(variableNameList.get(2))){
            System.out.println("Please enter Species update: ");
            String newSpecies = scnr.nextLine();
            this.setSpecies(newSpecies);
            if(optionSelect == -1){
                optionSelect = 6;
            }
        } 

        //This only enters only when the optionSelect is in specified range, it executes once
        //but will exit if not an initial setup run, interior code is self explanitory as it gets a value and
        //assigns input value to required variable
        if(optionSelect > 5 && optionSelect <= 11){
            do{
                System.out.println(String.format("Please enter %s as decimal/integer: ", this.variableNameList.get(optionSelect)));
                float userFloat = scnr.nextFloat();
                switch(optionSelect){
                    case 6:
                        // System.out.println("Please enter Tail Length(As decimal) update: ");
                        // float tailLength = scnr.nextFloat();
                        this.setTailLength(userFloat);
                        optionSelect +=1;
                        break;
                    case 7:
                        // System.out.println("Please enter Height(As decimal)) update: ");
                        // float height = scnr.nextFloat();
                        this.setHeight(userFloat);
                        optionSelect +=1;
                        break;
                    case 8:
                        // System.out.println("Please enter Body Length(As decimal) update: ");
                        // float bodyLength = scnr.nextFloat();
                        this.setBodyLength(userFloat);
                        optionSelect +=1;
                        break;
                    case 9:
                        // System.out.println("Please enter Torso Measurement(As decimal) update: ");
                        // float torso = scnr.nextFloat();
                        this.setTorsoMeasurement(userFloat);
                        optionSelect +=1;
                        break;
                    case 10:
                        // System.out.println("Please enter Skull Measurement(As decimal) update: ");
                        // float skull = scnr.nextFloat();
                        this.setSkullMeasurement(userFloat);
                        optionSelect +=1;
                        break;
                    case 11:
                        // System.out.println("Please enter Neck Measurement(As decimal) update: ");
                        // float neck = scnr.nextFloat();
                        this.setNeckMeasurement(userFloat);
                        optionSelect +=1;
                        break;
                    default:
                        System.out.println("Error, please try again: ");
                        this.createAnimal(optionSelect);

                }
            }while((optionSelect <= 11) && initialSetup);
        }
        else if(this.variableNameList.indexOf("Service Country: ") < optionSelect){
            optionSelect +=1;
            this.updateServiceInfo(optionSelect);
        }
        //This updates variable lists
        this.setVariableLists();
    }


}
