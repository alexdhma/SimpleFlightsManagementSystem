import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

		// title of program
		System.out.println("""
				++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				|       Simple Flights Management System (SFMS)	       |
				++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				""");

		// code descriptions
		System.out.println("""
				Code -> Description
				++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				101 -> Define the Flight
				102 -> Add Pilot to the Flight
				103 -> Add Flight Attendants to the Flight
				104 -> Register Passenger to the Flight
				105 -> Deregister Flight Attendant(s) and/or Passenger(s)
				106 -> Enter/Update Passenger(s) Charges
				107 -> Display Passengers' Statistics
				108 -> Display Flight Statistics
				109 -> Display Chargesheet
				110 -> Exit
				""");


		// create a Scanner
		System.out.print("Please enter a Code, from the aforementioned, that corresponds to your task: ");
		Scanner keyboard = new Scanner(System.in);

        // create a flight and pilot
		Flight flight = new Flight();
		Person pilot = new Person();

        // loop for the menuCode until the user exits
		while (true) {

			int menuCode = 0;
			try {
				menuCode = keyboard.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Error! Input Should be a Number.");
				keyboard.nextLine();
				continue;
			}

			switch (menuCode) {
				case 101:
					System.out.print(""" 
							\n
							...Define Flight...
							.....................................
							""");
					System.out.println("Enter the Flight information (FlightName FlightCode Permit) as a single-line entry: \n");
					flight.setFlightName(keyboard.next());
					flight.setFlightCode(keyboard.next());
					flight.setFlightPermit(keyboard.next());
					System.out.print("Successful operation! ");
					break;

				case 102:
					System.out.print("""
							\n
							...Add the Pilot to the Flight...
							.....................................
							""");
					System.out.print("Enter the pilot's information (EmployeeID FirstName LastName) as a single-line entry: \n");
					pilot.setEntityID(keyboard.next());
					pilot.setFirstName(keyboard.next());
					pilot.setLastName(keyboard.next());
					flight.setPilot(pilot);
					System.out.print("Successful operation! ");
					break;

				case 103:
					System.out.print("""
							\n
							...Add Flight Attendants to Flight...
							.....................................
							""");
					System.out.print("Enter Flight Attendant(s) information (ID1,FirstName1,LastName1;ID2,FirstName2,LastName2): \n");
					String junk = keyboard.nextLine();
					Person[] newFlightAttendants = Person.inStrToPersonArr(keyboard.nextLine());
					System.out.println(flight.appendToPersonArr(newFlightAttendants, 103));
					break;

				case 104:
					System.out.print("""
							\n
							...Register Passenger(s) at Flight...
							.....................................
							""");
					System.out.print("Enter Passengers information (ID1,FirstName1,LastName1;ID2,FirstName2,LastName2): \n");
					String junk1 = keyboard.nextLine();
					Person[] newPassengers = Person.inStrToPersonArr(keyboard.nextLine());
					System.out.println(flight.appendToPersonArr(newPassengers, 104));
					break;

				case 105:
					System.out.print("""
							\n
							...Deregister Flight Attendant(s) and/or Passenger(s)...
							.....................................................
							""");
					System.out.print("Enter '103' to deregister Flight Attendants(s) and '104' to deregister Passenger(s):");
                    int mode = 0;
                    try {
                        mode = keyboard.nextInt();
                    } catch (InputMismatchException e) {
						System.out.println("Error. Please enter'103' to deregister Flight Attendants(s) and '104' to deregister Passenger(s)");
						keyboard.nextLine();
                    }
                    System.out.println("Enter information of entries (ID1;ID2):");
					String junk2 = keyboard.nextLine();
					String result = flight.deleteFromPersonArr(keyboard.nextLine(), mode);
					System.out.println(result);
					break;

				case 106:
					System.out.print("""
							\n
							...Enter/Update Passenger(s) Charges...
							.....................................
							""");
					System.out.println("Enter Passengers' Charges (ID1,Charge1;ID2,Charge2): ");
					String junk3 = keyboard.nextLine();
					mode = 104;
					String newCharge = flight.updatePersonCharge(keyboard.nextLine(), mode);
					System.out.println(newCharge);
					break;

				case 107:
					System.out.print("""
							\n
							...Display Passengers' Statistics...
							.....................................
							""");
					System.out.println(flight);
					System.out.println(".....................................");

                    try {
                        for (int i = 0; i < flight.getPassengerList().size(); i++)
                            System.out.print((i + 1) + ". " + flight.getPassengerList().get(i) + "\n");
                    } catch (Exception e) {
						System.out.println("No Passengers to Display.");
                    }
                    System.out.println(".....................................");
					break;


				case 108:
					System.out.print("""
							\n
							.........................Flight Statistics.........................
							.........................................................................
							""");
					flight.flightStats();
					System.out.println("..........................................................................");
					break;

				case 109:
					System.out.print("""
							\n
							.......................Flight Chargesheet.......................
							. P/N. passenger ID SURNAME			First Name			Charge .
							................................................................
							""");
					flight.chargeSheet();
					System.out.println("................................................................");
					break;


				case 110:
					System.out.println("Simple SFMS >>>> Exiting... \n");
					System.out.println("Thank you for fostering our Simple Flights Management System (SFMS).");
                    System.exit(0);
					break;

				default:
					System.out.println("Invalid code. Please enter a code from one of the above options.");


			}
			System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: ");
		}

    }
	}