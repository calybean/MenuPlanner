package com.youravgjoe.apps.menuplanner;

import java.util.List;

public class Recipe
{
    String meal; //this will be either Breakfast, Lunch, or Dinner, and will be used to sort meals in DayViewActivity
    String name; //sort meals alphabetically in the list of meals?
    List<Ingredient> ingredients;
    String directions;

    public Recipe(String filePath)
    {
        //read ingredients in from file.
    }

    public void Save(String filePath)
    {

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
