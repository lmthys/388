package netid.iastate.edu.lab5.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    // - create a @Query to get all events that returns a List<Event> type
    @Query("SELECT * FROM event;")
    public List<Event> loadAllEvents();

    // - create a @Query to get an event by ID that returns a Event type
    @Query("SELECT * FROM event WHERE id = :id;")
    public Event findEventByID(int id);
    // - create an @Insert for an event object
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertEvent(Event e);
    // - create an @Delete for an event object
    @Delete
    public void deleteEvent(Event e);

}

