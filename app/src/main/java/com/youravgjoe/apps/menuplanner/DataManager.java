package com.youravgjoe.apps.menuplanner;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager
{
    static String mealFilePath;

    // Store file path to these files for convience
    static String breakFastNamesPath;
    static String lunchNamesPath;
    static String dinnerNamesPath;

    static String ingredientFilePath;

    static Context context;

    static List<String> ingredientList;

    public static void Init(Context _context)
    {
        // Set up variables
        context = _context;
        mealFilePath = context.getFilesDir().getPath().toString() + "/meals/";
        ingredientFilePath = context.getFilesDir().getPath().toString() + "/ingredients/";

        breakFastNamesPath = mealFilePath + "Breakfast";
        lunchNamesPath = mealFilePath + "Lunch";
        dinnerNamesPath = mealFilePath + "Dinner";

        // Create new directories if they don't exist
        File mealPath = new File(mealFilePath);
        File ingredPath = new File(ingredientFilePath);
        if(!mealPath.exists() && !ingredPath.exists())
        {
            mealPath.mkdir();
            ingredPath.mkdir();
        }

        /* //This is to set up for testing
        ArrayList<String> breakfastMealNames = new ArrayList<>();
        ArrayList<String> lunchMealNames = new ArrayList<>();
        ArrayList<String> dinnerMealNames = new ArrayList<>();
        // Find all the recipes and ingredients.
        for (int i = 0; i < 10; i++)
        {
            breakfastMealNames.add("Breakfast " + i);
            lunchMealNames.add("Lunch " + i);
            dinnerMealNames.add("Dinner " + i);
        }

        saveMealNameList(breakFastNamesPath, breakfastMealNames);
        saveMealNameList(lunchNamesPath, lunchMealNames);
        saveMealNameList(dinnerNamesPath, dinnerMealNames);
        */
    }

    public static List<String> getBreakfastMealNames()
    {
        return loadMealNameList(breakFastNamesPath);
    }

    public static List<String> getLunchMealNames()
    {
        return loadMealNameList(lunchNamesPath);
    }

    public static List<String> getDinnerMealNames()
    {
        return loadMealNameList(dinnerNamesPath);
    }


    public static void addToList(Meal meal)
    {
        switch (meal.type)
        {
            case breakfast:
                ArrayList<String> breakfastMealNames = loadMealNameList(breakFastNamesPath);
                breakfastMealNames.add(meal.name);
                saveMealNameList(breakFastNamesPath, breakfastMealNames);
                break;
            case lunch:
                ArrayList<String> lunchMealNames = loadMealNameList(lunchNamesPath);
                lunchMealNames.add(meal.name);
                saveMealNameList(lunchNamesPath, lunchMealNames);
                break;
            case dinner:
                ArrayList<String> dinnerMealNames = loadMealNameList(dinnerNamesPath);
                dinnerMealNames.add(meal.name);
                saveMealNameList(dinnerNamesPath, dinnerMealNames);
                break;

            default:

                break;
        }
    }

    public static void saveMealNameList(String fileName, List<String> toSave)
    {
        try
        {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < toSave.size(); i++)
            {
                writer.write(toSave.get(i) + "\n");
            }
            writer.close();
            Toast.makeText(context,"Saved to " + file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    static ArrayList<String> loadMealNameList(String fileName)
    {
        try
        {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);

            ArrayList<String> load = new ArrayList();
            int readIn;
            String input = "";
            do
            {
                readIn = reader.read();
                char data = (char)readIn;
                if (data != '\n')
                {
                    input += data; // build a string
                }
                else
                {
                    load.add(input); // add string to list, then clear
                    input = "";
                    // Don't do anything else with the newline char, continue with the loop and read the next series of characters.
                }
            }
            while(readIn != -1); // End of file, stop reading.

            reader.close();
            return load;
        }
        catch (IOException e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return new ArrayList<String>();
    }

    public static void printExceptionMessage(IOException e)
    {
        Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
    }

}
