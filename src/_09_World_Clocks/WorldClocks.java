package _09_World_Clocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
    ClockUtilities clockUtil;
    Timer timer;
    TimeZone chicagoTimeZone;

    JFrame frame;
    JPanel panel;
    JButton addTime;
    JTextArea chicagoTextArea;
    JTextArea seattleTextArea;
    JTextArea beijingTextArea;
    JTextArea londonTextArea;
    
    String chicagoTimeStr;
    String seattleTimeStr;
    String beijingTimeStr;
    String londonTimeStr;
    
    String chicagoDateStr;
    String seattleDateStr;
    String beijingDateStr;
    String londonDateStr;
    
    HashMap<String, TimeZone> times = new HashMap<String, TimeZone>();
    
    public WorldClocks() {
        clockUtil = new ClockUtilities();
        
        // times.put("Seattle, US", clockUtil.getTimeZoneFromCityName("Seattle, US"));
        // The format for the city must be: city, country (all caps)
        chicagoDateStr = getDateStr("Chicago, US");
        seattleDateStr = getDateStr("Seattle, US");
        beijingDateStr = getDateStr("Beijing, CN");
        londonDateStr = getDateStr("London, GB");
        times.put("Seattle, US", clockUtil.getTimeZoneFromCityName("Seattle, US"));
        times.put("Chicago, US", clockUtil.getTimeZoneFromCityName("Chicago, US"));
        times.put("Beijing, CN", clockUtil.getTimeZoneFromCityName("Beijing, CN"));
        times.put("London, GB", clockUtil.getTimeZoneFromCityName("London, GB"));
        
        System.out.println(chicagoDateStr);

        // Sample starter program
        frame = new JFrame();
        panel = new JPanel();
        addTime = new JButton("Add Clock");
        addTime.addActionListener(this);
        chicagoTextArea = new JTextArea();
        seattleTextArea = new JTextArea();
        beijingTextArea = new JTextArea();
        londonTextArea = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100, 100);
        frame.add(panel);
        panel.add(chicagoTextArea);
        chicagoTextArea.setText("Chicago, US" + "\n" + chicagoDateStr);
        panel.add(seattleTextArea);
        seattleTextArea.setText("Seattle, US" + "\n" + seattleDateStr);
        panel.add(beijingTextArea);
        beijingTextArea.setText("Beijing, China" + "\n" + beijingDateStr);
        panel.add(addTime);
        panel.add(londonTextArea);
        londonTextArea.setText("London, UK" + "\n" + londonDateStr);
        
        // This Timer object is set to call the actionPerformed() method every
        // 1000 milliseconds
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        chicagoTimeStr = getTimeStr("Chicago, US");
        seattleTimeStr = getTimeStr("Seattle, US");
        beijingTimeStr = getTimeStr("Beijing, CN");
        londonTimeStr = getTimeStr("London, GB");
        
        chicagoTextArea.setText("Chicago, US" + "\n" + chicagoDateStr + "\n" + chicagoTimeStr);
        seattleTextArea.setText("Seattle, US" + "\n" + seattleDateStr + "\n" + seattleTimeStr);
        beijingTextArea.setText("Beijing, China" + "\n" + beijingDateStr + "\n" + beijingTimeStr);
        londonTextArea.setText("London, UK" + "\n" + londonDateStr + "\n" + londonTimeStr);
        frame.pack();
    }
    
    public String getDateStr(String city) {
    	TimeZone timeZone = clockUtil.getTimeZoneFromCityName(city);
        
        Calendar calendar = Calendar.getInstance(timeZone);
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        String dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR);
        
        return dateStr;
    }
    
    public String getTimeStr(String city) {
    	Calendar c = Calendar.getInstance(times.get(city));
        String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
        String timeStr = militaryTime + twelveHourTime;
        
        return timeStr;
    }
    
}
