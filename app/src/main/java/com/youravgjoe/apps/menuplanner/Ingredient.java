package com.youravgjoe.apps.menuplanner;

import android.content.Context;
import android.os.Message;
import android.util.JsonWriter;
import android.util.Xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.JAXBContext;

/**
 * Created by Joey on 10/10/15.
 */
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
        File file = new File(context.getFilesDir(), fileName);

        Xml.newSerializer();

//        try
//        {
//            FileOutputStream out = new FileOutputStream(file);
//            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
//            writer.setIndent("  ");
//            writeMessagesArray(writer, messages);
//            writer.close();
//        }
//        catch(FileNotFoundException e)
//        {
//            //do stuff
//        }
//        catch(UnsupportedEncodingException e)
//        {
//            //do different stuff
//        }
//        catch(IOException e)
//        {
//            //do stuff
//        }


    }

//    public void writeMessagesArray(JsonWriter writer, List messages) throws IOException {
//        writer.beginArray();
//        for (Message message : messages) {
//            writeMessage(writer, message);
//        }
//        writer.endArray();
//    }
//
//    public void writeMessage(JsonWriter writer, Message message) throws IOException {
//        writer.beginObject();
//        writer.name("id").value(message.getId());
//        writer.name("text").value(message.getText());
//        if (message.getGeo() != null) {
//            writer.name("geo");
//            writeDoublesArray(writer, message.getGeo());
//        } else {
//            writer.name("geo").nullValue();
//        }
//        writer.name("user");
//        writeUser(writer, message.getUser());
//        writer.endObject();
//    }
}
