//Joshua Kovacevich, 20200924, THis is the driver class for module 4's assignment
public class Driver {
    
    public static void main(String[] args) {
        /*This driver class runs the program once. The first line instantiates a "newCorgi" object
        The second line assigns a value to the Corgi's inherited value of topTrick and the last 
        prints the return value of toString()*/
        Corgi newCorgi = new Corgi("cattle herding", "Pembroke Welsh Corgi", "Java", 38, 5);
        newCorgi.setTopTrick("ringing the bell to go outside");
        System.out.println(newCorgi.toString()); 

    }
}
