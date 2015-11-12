package edu.uco.schambers4.octane.Models.MealPlanner;

import java.util.List;

import edu.uco.schambers4.octane.Models.Schedule.Schedule;

/**
 * Created by Steven Chambers on 11/13/2015.
 */
public class MealPlanByWeek
{
    private int weekOfYear;
    private List<Schedule<Recipe>> plan;

    public MealPlanByWeek(int weekOfYear, List<Schedule<Recipe>> plan)
    {
        this.weekOfYear = weekOfYear;
        this.plan = plan;
    }

    @Override
    public String toString()
    {
        return String.format("Week %d", this.weekOfYear);
    }

    public int getWeekOfYear()
    {
        return weekOfYear;
    }

    public List<Schedule<Recipe>> getPlan()
    {
        return plan;
    }
}
