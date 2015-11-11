package edu.uco.schambers4.octane.Models.Schedule;

import java.util.Date;
import java.util.UUID;

public class Schedule<T> {

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
}
