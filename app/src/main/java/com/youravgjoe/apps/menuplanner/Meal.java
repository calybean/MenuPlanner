package com.youravgjoe.apps.menuplanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Meal
{
    public enum mealType {breakfast, lunch, dinner;}

    String name;
    List<Ingredient> ingredients;
    String directions;
    mealType type;

    public Meal(String filePath)
    {
        //read ingredients in from file.
    }

    public void SaveToFile()
    {
        try
        {
            File file = new File(DataManager.mealFilePath + name);
            FileWriter writer = new FileWriter(file);
            writer.write(name);
            writer.write(type.toString());
            for (int i = 0; i < ingredients.size(); i++)
            {
                //writer.write(ingredients.SaveToFile()); under construction
            }
            writer.close();
        }
        catch(IOException e)
        {
            DataManager.printExceptionMessage(e);
        }
    }

    public Double GetCost()
    {
        Double cost = 0.0;
        for (int i = 0; i < ingredients.size(); i++)
        {
            cost += ingredients.get(i).GetTotalCost();
        }
        return cost;
    }


}
