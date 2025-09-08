import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flight {

    // fields
    private String flightName;
    private String flightCode;
    private String flightPermit;
    private Person pilot;
    private List<Person> flightAttendList;
    private List<Person> passengerList;

    // default constructor
    public Flight() {
        this.flightName = "Flight-Name:TBD";
        this.flightCode = "Flight-Code:TBD";
        this.flightPermit = "Flight-Permit:TBD";
        this.pilot = new Person("No ID Yet", "", "TBD");
    }

    // standard constructor
    public Flight(String flightName, String flightCode, String flightPermit, Person pilot, List<Person> flightAttendList, List<Person> passengerList) {
        this.flightName = flightName;
        this.flightCode = flightCode;
        this.flightPermit = flightPermit;
        this.pilot = pilot;
        this.flightAttendList = flightAttendList;
        this.passengerList = passengerList;
    }

    // getters and setters
    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightPermit() {
        return flightPermit;
    }

    public void setFlightPermit(String flightPermit) {
        this.flightPermit = flightPermit;
    }

    public Person getPilot() {
        return pilot;
    }

    public void setPilot(Person pilot) {
        this.pilot = pilot;
    }

    public List<Person> getFlightAttendList() {
        return flightAttendList;
    }

    public void setFlightAttendList(List<Person> flightAttendList) {
        this.flightAttendList = flightAttendList;
    }

    public List<Person> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Person> passengerList) {
        this.passengerList = passengerList;
    }

    // append new person array to existing array
    public String appendToPersonArr(Person[] chrArr, int mode) {

        StringBuilder message = new StringBuilder();

        // if new person array is empty
        if (chrArr == null || chrArr.length == 0)
            return "No person to add";

        // add flight attendants
        if (mode == 103) {
            if (flightAttendList==null)
                flightAttendList = new ArrayList<>();

            for (Person p : chrArr) {
                boolean attendantAlreadyExist = false;

                for (Person person : flightAttendList) {
                    if (p.getEntityID().equals(person.getEntityID())) {
                        attendantAlreadyExist = true;
                        break;
                    }
                }

                if (!attendantAlreadyExist) {
                    flightAttendList.add(p);
                    message.append("Successfully Added: ").append(p).append("\n");
                } else
                    message.append("Already Exists: ").append(p).append("\n");
            }
        }
        // add passengers
        if (mode == 104) {
            if (passengerList==null)
                passengerList = new ArrayList<>();

            for (Person p : chrArr) {
                boolean passengerAlreadyExist = false;

                for (Person person : passengerList) {
                    if (p.getEntityID().equals(person.getEntityID())) {
                        passengerAlreadyExist = true;
                        break;
                    }
                }

                if (!passengerAlreadyExist) {
                    passengerList.add(p);
                    message.append("Successfully Added: ").append(p).append("\n");
                } else
                    message.append("Already Exists: ").append(p).append("\n");
            }
        }
        return message.toString();
    }

    // delete from person array
    public String deleteFromPersonArr(String inStr, int mode) {

        String[] IDToDelete = inStr.split(";");
        StringBuilder message = new StringBuilder();

        // deregister flight attendant
        if (mode == 103) {
            if (flightAttendList == null)
                return "No Flight Attendants To Remove.";

            for (String id : IDToDelete) {
                boolean idFound = false;
                Iterator<Person> iterator = flightAttendList.iterator();
                while (iterator.hasNext()) {
                    Person p = iterator.next();
                    if (id.equals(p.getEntityID())) {
                        iterator.remove();
                        message.append("Successfully Deleted: ").append(p).append(".\n");
                        idFound = true;
                        break;
                    }
                }
                if (!idFound)
                    message.append("Entity NOT Found: ").append(id).append(".\n");
            }
        }

        // deregister passenger
        if (mode == 104) {
            if (passengerList == null)
                return "No Passengers To Remove.";

            for (String id : IDToDelete) {
                boolean idFound = false;
                Iterator<Person> iterator = passengerList.iterator();
                while (iterator.hasNext()) {
                    Person p = iterator.next();
                    if (id.equals(p.getEntityID())) {
                        iterator.remove();
                        message.append("Successfully Deleted: ").append(p).append(".\n");
                        idFound = true;
                        break;
                    }
                }
                if (!idFound)
                    message.append("Entity NOT Found: ").append(id).append(".\n");
            }
        }
        if (message.isEmpty())
            return "No matching ID found.";
        else
            return message.toString();
    }

    // update charge of person using their id
    public String updatePersonCharge(String inStr, int mode) {

        if (inStr == null || inStr.isEmpty())
            return "Nothing to update.";

        String[] idAndCharge = inStr.split(";");
        StringBuilder message = new StringBuilder();

        if (passengerList == null || passengerList.isEmpty())
            return "You Cannot add/update charge using an EMPTY array.";

        for (String s : idAndCharge) {
            String[] parts = s.split(",");
            if (parts.length != 2)
                message.append("Invalid format: ").append(s).append("\n");

            String id = parts[0];
            double charge;
            try {
                charge = Double.parseDouble(parts[1]);
            } catch (NumberFormatException e) {
                return "Invalid Charge. Please Provide Charge as a Number.";
            }

            boolean updated = false;
            for (Person p : passengerList) {
                if (id.equals(p.getEntityID())) {
                    p.setChargePercent(charge);
                    message.append("Successfully Added/Updated: Charge for ").append(p).append(".\n");
                    updated = true;
                    break;
                }
            }
            if (!updated)
                message.append("Entity NOT Found: ").append(id).append(".\n");
        }
        return message.toString();
    }

    // helper method for layout
    void printEnd(String inStr, String value, int width) {
        String line = ". " + inStr + value;
        while (line.length() < width-1)
            line += " ";
        line +=".";
        System.out.println(line);
    }

    // display flight stats method
    public void flightStats() {

        int width = 73;

        printEnd("Flight Name          : ", flightName, width);
        printEnd("Flight Code          : ", flightCode, width);
        printEnd("Permit               : ", flightPermit, width);
        printEnd("Pilot Name           : ", (pilot != null ? pilot.toString() : "TBD, (No ID Yet)"), width);

        if (flightAttendList==null || flightAttendList.isEmpty())
    printEnd("No Flight Attendant Registered Yet", "", width);
        else {
            for (int i = 0; i<flightAttendList.size(); i++)
                printEnd("Flight Attendant    " + (i+1) + ": ", flightAttendList.get(i).toString(), width);
        }
        if (passengerList == null || passengerList.isEmpty())
            printEnd("Registered Passengers: 0", "", width);
        else
            printEnd("Registered Passengers: " + passengerList.size(), "", width);
    }


    // display the charge sheet
    public void chargeSheet() {
        int width = 63;

        if (passengerList==null || passengerList.isEmpty())
            System.out.println("No Passengers Registered Yet.");
        else {
            for (int i=0; i<passengerList.size(); i++)
                printEnd("\t" + (i+1) + ". ",  passengerList.get(i).getEntityID() + "    " + passengerList.get(i).getLastName() + "           " + passengerList.get(i).getFirstName() + "              " + passengerList.get(i).getChargePercent(), width);
        }
    }

    // toString() method
    @Override
    public String toString() {

        int flightAttendantNum = (flightAttendList==null)? 0 : flightAttendList.size();
        int passengerNum = (passengerList==null)? 0 : passengerList.size();

        return
                flightName + " (" + flightCode + "): " + flightPermit + "\n" +
                "pilot: Capt. " + pilot + "\n" +
                "Flight Attendants: " + flightAttendantNum + " Flight Attendants(s)\n" +
                "The number of passengers registered with the flight: " + passengerNum + " passengers";
    }
}
