package netid.iastate.edu.lab5.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class represents the Event object whose data will be stored in the database.
 */
@Entity (tableName = "event")
public class Event {

    // - create event attributes for a uid(int) that autogenerates, title(string), location(string), startTime(string), endTime(string), and details(string)
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String location;
    private String startTime;
    private String endTime;
    private String details;



    /**
     * Constructs an event given data from database
     */
    public Event(String title, String location, String startTime,
                 String endTime, String details) {
        // - assign the passed in variables to the corresponding private variables in this class
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
    }

    /**
     * Constructs an empty Event, used by Room database query
     */
    public Event() { }

    // GETTERS
    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getEndTime() {
        return endTime;
    }
    public String getLocation() {
        return location;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getId() {
        return id;
    }
    //SETTERS

    public void setDetails(String details) {
        this.details = details;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }
    //- create getters and setters for all the above mentioned private variables






    /**
     * Returns the end time in the form of a Date object
     */
    // - uncomment the below method once you have created an endTime variable in this class
    public Date getEndTimeAsDate() {
        try {
            return new SimpleDateFormat("MMMM d, yyyy, 'at' h:mm a", Locale.US).parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    @Override
    public String toString(){
        return title;
    }



}