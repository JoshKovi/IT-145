//Joshua Kovacevich, 20201015, This is the Ship class for LOCO aka project 3, minor modifications were made to print function.
public class Ship {

    // Instance Variables
    private String shipName;
    private int roomBalcony;
    private int roomOceanView;
    private int roomSuite;
    private int roomInterior;
    private boolean inService;

    // Constructor - default
    Ship() {
    }

    // Constructor - full
    Ship(String tName, int tBalcony, int tOceanView,
         int tSuite, int tInterior, boolean tInService) {
        shipName = tName;
        roomBalcony = tBalcony;
        roomOceanView = tOceanView;
        roomSuite = tSuite;
        roomInterior = tInterior;
        inService = tInService;
    }

    // Accessors
    public String getShipName() {
        return shipName;
    }

    public int getRoomBalcony() {
        return roomBalcony;
    }

    public int getRoomOceanView() {
        return roomOceanView;
    }

    public int getRoomSuite() {
        return roomSuite;
    }

    public int getRoomInterior() {
        return roomInterior;
    }

    public boolean getInService() {
        return inService;
    }

    // Mutators
    public void setShipName(String tVar) {
        shipName = tVar;
    }

    public void setRoomBalcony(int tVar) {
        roomBalcony = tVar;
    }

    public void setRoomOceanView(int tVar) {
        roomOceanView = tVar;
    }

    public void setRoomSuite(int tVar) {
        roomSuite = tVar;
    }

    public void setRoomInterior(int tVar) {
        roomInterior = tVar;
    }

    public void setInService(boolean tVar) {
        inService = tVar;
    }

    // print method
    public void printShipData() {
        //Like some of the other print statements my ocd kicked in and I needed to 
        //correct the way they were formatted and align them properly for ships with names
        //less than 19 characters... otherwise it aligns similarly to before.
        System.out.printf("%-19s %-3s %-3s %-3s %-3s\t%s\n", shipName, roomBalcony,
            roomOceanView, roomSuite, roomInterior, inService);
    }

    // method added to print ship's name vice memory address
    @Override
    public String toString() {
        return shipName;
    }
}
