package edu.uco.schambers4.octane.DataAccessObjects.Schedules;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.uco.schambers4.octane.DataAccessObjects.Recipes.InternalStorageRecipeRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.RecipeRespository;
import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.Schedule.Schedule;

/**
 * Created by Steven Chambers on 11/12/2015.
 */
public class MockScheduleRepository<T extends INameable> implements ScheduleRepository<T>
{
    Context context;

    public MockScheduleRepository(Context context)
    {
        this.context = context;
    }

    @Override
    public ArrayList<Schedule<T>> getAllSchedules()
    {
        RecipeRespository recipeRespository = new InternalStorageRecipeRepository(context);
        List<IIngredient> recipeList = recipeRespository.getCollectionAsList();
        Recipe meal;
        if(recipeList.size() != 0)
        {
            meal = (Recipe)recipeList.get(0);
        }
        else
        {
            meal = new Recipe("STUFF");
        }
        Calendar cal = Calendar.getInstance();
        ArrayList<Schedule<T>> scheduleList = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) - i);
            Schedule<T> mealPlan = (Schedule<T>) new Schedule<Recipe>(meal, cal.getTime());
            scheduleList.add(mealPlan);
        }
        return scheduleList;
    }

    @Override
    public ArrayList<Schedule<T>> getSchedulesForDate(Date date)
    {
        return null;
    }

    @Override
    public void saveSchedule(Schedule<T> schedule)
    {

    }

    @Override
    public void deleteSchedule(Schedule<T> schedule)
    {

    }
}
