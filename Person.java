import java.util.ArrayList;
import java.util.List;

public class Person {

    // fields
    private String entityID;
    private String firstName;
    private String lastName;
    private double chargePercent;

    // default constructor
    public Person() {
    }

    // standard constructor
    public Person(String entityID, String firstName, String lastName) {
        this.entityID = entityID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // copy constructor
    public Person(Person objPerson) {
        this.entityID = objPerson.entityID;
        this.firstName = objPerson.firstName;
        this.lastName = objPerson.lastName;
        this.chargePercent = objPerson.chargePercent;
    }

    // getters and setters
    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getFirstName() {
        return upperCamelCase(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return upperCase(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getChargePercent() {
        return chargePercent;
    }

    public void setChargePercent(double chargePercent) {
        this.chargePercent = chargePercent;
    }

    // equals() method
    public boolean equals(Person anotherPerson) {

        if(anotherPerson==null)
            return false;

        return entityID.equals(anotherPerson.entityID);
    }

    // return input string word with upper camel case notation
    public static String upperCamelCase(String inStr) {
        if (inStr==null||inStr.isEmpty())
            return inStr;
        return inStr.substring(0,1).toUpperCase() + inStr.substring(1).toLowerCase();
    }

    // return input string sentence with upper camel case notation
    public static String multiUpperCamelCase(String inStr) {
        if (inStr==null||inStr.isEmpty())
            return inStr;
        String[] words = inStr.split("\\s");
        for (int i=0; i< words.length; i++) {
            words[i] = upperCamelCase(words[i]);
        }
        return String.join(" ", words);
    }

    // return input string in all uppercase notation
    public static String upperCase(String inStr) {
        if (inStr==null||inStr.isEmpty())
            return inStr;
        return inStr.toUpperCase();
    }

    // create person array from input string
    public static Person[] inStrToPersonArr(String inStr) {
        String[] personStrArr = inStr.split(";");
        List<Person> peopleArr = new ArrayList<>();

        for (String s : personStrArr) {
            String[] personInfo = s.split(",");
            if (personInfo.length != 3) {
                System.out.println("Invalid entry: " + s);
                continue;
            }
            String entityID = personInfo[0];
            String firstName = personInfo[1];
            String lastName = personInfo[2];
            peopleArr.add(new Person(entityID, firstName, lastName));
        }
       return peopleArr.toArray(new Person[0]);
    }

    // toString() method
    @Override
    public String toString() {
        return upperCase(lastName) + ", " + upperCamelCase(firstName) + " (" + entityID + ")";
    }
}
