public class Corgi extends Dog {

    // These are two additional fields for the Corgi class which inherits from the Dog class
    private int weight;
    private int age;


    // These are the default and requested constructors
    public Corgi(){
        //This is the default constructor that assigns the fields all with default values
        super();
        setWeight(0);
        setAge(0); 
    }
    public Corgi(String type, String breed, String name, int pounds, int years) {

        // This calls Dogs constructor with type, breed, and name, and then additionally assigns weight and age
        super(type, breed, name);
        weight = pounds;
        age = years;
    }



    // override toString() method to include additional dog information
    //This is a cool way to return the inherited toString and add on to it, I like it
    @Override
    public String toString() {
        return (super.toString() + "\nThe Corgi is " + age +
                " years old and weighs " + weight + " pounds.");
    }

    //This is the setters and getters for the 2 fields weight and age
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
