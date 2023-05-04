package _08_California_Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */

public class CaliforniaWeather {

	void start() {
		HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
		Scanner s = new Scanner(System.in);
		ArrayList<String> cities = new ArrayList<String>();

		// All city keys have the first letter capitalized of each word
		System.out.println("Pick a mode, enter 'c' for determining the weather of a city,\nenter 'w' to determine cities that have the specified weather,\nor enter 't' to determine cities in a specified range of tempature.");
		char mode = s.nextLine().charAt(0);
		String input = null;
		WeatherData datum = null;
		double minTemp = 0;
		double maxTemp = 0;
		
		if (mode != 't') {
			System.out.println("Enter your weather or city.");
			input = Utilities.capitalizeWords(s.nextLine());
			datum = weatherData.get(input);
		} else {
			System.out.println("Enter your minimum tempature in fahrenheit."); 
			minTemp = s.nextDouble();
			System.out.println("Enter your maximum tempature in fahrenheit.");
			maxTemp = s.nextDouble();
		}
		
		if (mode == 'w') {
			for (String key : weatherData.keySet()) {
				if (weatherData.get(key).weatherSummary.equals(input)) {
					cities.add(key);
				}
			}
			if (!cities.isEmpty()) {
				System.out.println(cities.toString());
			} 
		} else if (mode == 'c') {
			System.out.println(input + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
		} else if (mode == 't') {
			
			for (String key : weatherData.keySet()) {
				if (weatherData.get(key).temperatureF >= minTemp && weatherData.get(key).temperatureF <= maxTemp ) {
					cities.add(key);
				}
			}
			if (!cities.isEmpty()) {
				System.out.println(cities.toString());
			} 
			
		} else {
			System.out.println("Unable to find weather data for: " + input);
			return;
		}
		
		
	}
}
