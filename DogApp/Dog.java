public class Dog {

    // These are the 4 fields that any Dog object will have, including subclasses
    private String type;
    private String breed;
    private String name;
    private String topTrick;



    // This is a default constructor that assigns the values type, breed and name as "Unknown"
    public Dog(){
        this.setType("Unknown");
        this.setBreed("Unknown");
        this.setName("Unknown");
    }

    //This is a constructor method that accepts type, breed and name and assigns them to object
    public Dog(String inputType, String inputBreed, String inputName){
        //Here I use the setters to set the three input values to their appropriate variable
        this.setType(inputType);
        this.setBreed(inputBreed);
        this.setName(inputName);
    }

    // This is the default method for returning a string for output
    public String toString() {
        String temp = "\nDOG DATA\n" + name + " is a " + breed +
                ", a " + type + " dog. \nThe top trick is: " +
                topTrick + ".";
        return temp;
    }


    //Getters and setters for the 4 fields
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopTrick() {
        return topTrick;
    }

    public void setTopTrick(String topTrick) {
        this.topTrick = topTrick;
    }

}
