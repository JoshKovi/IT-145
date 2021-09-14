//Joshua Kovacevich, 20201015, This is the Cruise class for LOCO aka project 3, the full constructor was changed, Cruise now extends ship
//for proper room tracking and the print statement was made.
public class Cruise extends Ship{

    // Instance Variables
    private String cruiseName;
    private String cruiseShipName;
    private String departurePort;
    private String destination;
    private String returnPort;

    // Constructor - default
    Cruise() {
    }

    // Constructor - full
    Cruise(String tCruiseName, String tShipName, String tDeparture, String tDestination, String tReturn, Ship ship) {
        cruiseName = tCruiseName;
        cruiseShipName = tShipName;
        departurePort = tDeparture;
        destination = tDestination;
        returnPort = tReturn;

        //This was added to comply with customer overbooking functionality as each cruise needs passanger counts updated
        this.setRoomBalcony(ship.getRoomBalcony());
        this.setRoomOceanView(ship.getRoomOceanView());
        this.setRoomSuite(ship.getRoomSuite());
        this.setRoomInterior(ship.getRoomInterior());
    }

    // Accessors
    public String getCruiseName() {
        return cruiseName;
    }

    public String getCruiseShipName() {
        return cruiseShipName;
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public String getDestination() {
        return destination;
    }

    public String getReturnPort() {
        return returnPort;
    }

    // Mutators
    public void setCruiseName(String tVar) {
        cruiseName = tVar;
    }

    public void setCruiseShipName(String tVar) {
        cruiseShipName = tVar;
    }

    public void setDeparturePort(String tVar) {
        departurePort = tVar;
    }

    public void setDestination(String tVar) {
        destination = tVar;
    }

    public void setReturnPort(String tVar) {
        returnPort = tVar;
    }

    // print cruise details
    public void printCruiseDetails() {
        //This was the easiest way to properly align text in the vast majority of situations
        //The numbers come from the amount of dashes present in the formatting line minus
        //1 space in the first 4 variables to ensure spaces between variables
        //This prints the main original fields
        System.out.printf("\n%-19s %-19s %-19s %-19s %-11s", 
            cruiseName, cruiseShipName, departurePort, destination, returnPort);

        //This adds the room availability numbers for a specific cruise, room number is ship room number - passenger assigned room
        System.out.printf("%-3s %-3s %-3s %-3s", this.getRoomBalcony(),
            this.getRoomOceanView(), this.getRoomSuite(), this.getRoomInterior());

    }

    // method added to print ship's name vice memory address
    @Override
    public String toString() {
        return cruiseName;
    }
}
