package edu.uco.schambers4.octane.Models.Schedule;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import edu.uco.schambers4.octane.DataAccessObjects.Schedules.ScheduleRepository;
import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;

public class Schedule<T extends INameable> implements Serializable
{

    private UUID id;
    private Date date;
    private T item;

    public Schedule() {
        id = UUID.randomUUID();
    }


    public Schedule(T item, Date date) {
        this();
        this.date = date;
        this.item = item;
    }

    public UUID getId(){
        return id;
    }

    public T getItem(){
        return item;
    }

    public void setItem(T item){
        this.item = item;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", this.date.toString(), this.item.getName());
    }
}
