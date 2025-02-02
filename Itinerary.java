import java.util.ArrayList;
import java.util.Arrays;

public class Itinerary {
    private String travelerName;
    private ArrayList<String> citiesByDay;

    // Constructor 1
    public Itinerary(String n, ArrayList<String> cities) {
        travelerName = n;
        citiesByDay = cities;
    }

    // Constructor 2
    public Itinerary(String n) {
        travelerName = n;
    }

    // Getters
    public String getTravelerName() {
        return travelerName;
    }

    public ArrayList<String> getCitiesByDay() {
        return new ArrayList<>(citiesByDay); // return a copy
    }

    // Method to get a list of unique cities visited
    public ArrayList<String> citiesVisited() {
        ArrayList<String> citiesVisitedFiltered = new ArrayList<>();

        for (String city : citiesByDay) {
            if (city != null && !city.trim().isEmpty() && !citiesVisitedFiltered.contains(city)) {
                citiesVisitedFiltered.add(city);
            }
        }

        return citiesVisitedFiltered;
    }

    // Method to get the number of unique cities visited
    public int numberOfCitiesVisited() {
        return citiesVisited().size();
    }

    // Method to get the number of day of the itinerary
    public int numberOfDays() {
        return getCitiesByDay().size();
    }

    // Method to add city to citiesByDay List
    public void addNextCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City can't be null/empty.");
        }

        ArrayList<String> copyOfCitiesByDay = getCitiesByDay(); // Get a copy
        copyOfCitiesByDay.add(city); // Add the new city

        this.citiesByDay = copyOfCitiesByDay; // Update
    }

    // Method to reset citiesByDay List
    public void resetItinerary() {
        this.citiesByDay = new ArrayList<>(); // Create a new empty list
    }

    // Over-riding toString method
    @Override
    public String toString() {
        String result = "\nTraveler: "+ travelerName+
                "\n***Cities Visited by Day***"+ "\n";
        if(citiesByDay.isEmpty()) {
            result  += "No cities visited yet.";
        }
        else {
            for (int i = 0; i < citiesByDay.size(); i++) {
                result += "Day "+ (i + 1)+ ": "+ citiesByDay.get(i)+ "\n";
            }
        }
        return result;
    }

    // Method to calculates how many days two travelers will be in the same city on the same day.
    public static int daysTogther(Itinerary i1, Itinerary i2) {
        if (i1 == null || i2 == null) {
            throw new IllegalArgumentException("Itinerary objects cannot be null.");
        }
        int smallest_List = Math.min(i1.numberOfDays(), i2.numberOfDays()); // get the person with the smallest list

        int counter = 0;
        for (int i = 0; i < smallest_List; i++) {
            String city1 = i1.getCitiesByDay().get(i);
            String city2 = i2.getCitiesByDay().get(i);

            if (city1 != null && city2 != null && city1.equalsIgnoreCase(city2)) {
                counter++;
            }
        }
        return counter;
    }
}
