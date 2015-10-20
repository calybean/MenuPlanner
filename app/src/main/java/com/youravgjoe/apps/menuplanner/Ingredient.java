/*
It is likely that we will need two seperate objects for ingrdients in a recipe and items on a shopping list.
This is due to different measurment types. It may be helpful, when we get to that point, to override the + and -
opperators to deal with two ingredient instances as well as deal with differnt measurments units etc.
 */

package com.youravgjoe.apps.menuplanner;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;

public class Ingredient
{
    int nameLength; // Used for file io
    String name;
    Double cost;
    Double quantity;
//    Measurments.Unit measurementUnit;

    public Ingredient()
    {

    }

    public Ingredient (Context context, String name, Double cost, Double quantity)
    {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Ingredient (String fileName)
    {
        //load info from a file
        try
        {
            // By default, java reads/writes everything as bytes.
            File file = new File(fileName);
            FileInputStream stream = new FileInputStream(file);


            nameLength = stream.read();         //Find out how long the name is. This should be the first thing in the file.
            byte[] data = new byte[nameLength]; // Create a byte array exactally the size of the name
            stream.read(data);                  // Read in and store that many bytes
            name = new String(data);            // Convert the array into a string

            data = new byte[Double.SIZE];
            stream.read(data);
            cost = 0.0;
            for (int i = 0; i < data.length; i++)   // Run the length of the byte array and total the values.
            {
                cost += data[i];
            }

        }
        catch (IOException e)
        {

        }
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
