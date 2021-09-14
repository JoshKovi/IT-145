import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class tester {

    private static SimpleDateFormat dateTest; //= new SimpleDateFormat("MM/dd/yyyy");
    private static Scanner scnr = new Scanner(System.in);
    protected static ArrayList<Object> variableList = new ArrayList<Object>();
    public static void main(String[] args){

        variableList.add("Hello");
        variableList.add(12);
        variableList.add(new Date());

        System.out.println(variableList.get(1));

        
        
        //simpleDateTester();

        


    }

    public static void simpleDateTester(){
        dateTest = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("Enter a date (format MM/DD/YYYY): ");
        String dateInput = scnr.next();
        try{
            Date date = dateTest.parse(dateInput);
            System.out.println(dateTest.format(date));
        } catch(ParseException eParseException){
            eParseException.printStackTrace();
        }

        
        
    }

    public static SimpleDateFormat getDateTest() {
        return dateTest;
    }

    public static void setDateTest(SimpleDateFormat dateTest) {
        tester.dateTest = dateTest;
    }
    
}
