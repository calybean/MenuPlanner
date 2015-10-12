package com.youravgjoe.apps.menuplanner;

import android.content.Context;

public class Ingredient
{
    Context context;
    public String name;
    public Double cost;

    public Double quantity;

    public Ingredient()
    {

    }

    public Ingredient (Context context, String name, Double cost, Double quantity)
    {
        this.context = context;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Ingredient (Context context, String fileName)
    {
        //load info from a file
        this.context = context;
    }

    public Double GetSingleCost()
    {
        return cost;
    }

    public Double GetTotalCost()
    {
        return cost * quantity;
    }

    public void AddQuantity(Double amount)
    {
        quantity += amount;
    }

    public void RemoveQuantity(Double amount)
    {
        quantity -= amount;
    }

    public void SaveToFile(String fileName)
    {

    }
}
