package com.youravgjoe.apps.menuplanner;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileManager {
    public static String outputFile(FileOutputStream out, String name, List<String> values)
    {
        String outputString = "";
        outputString += "\"meals\":[\n";

        for (int i = 0; i < values.size(); i++)
        {
            outputString += "{\"" + name + "\": \"" + values.get(i) + "\"},\n"; //output: {"name":"value"}, for every element
        }
        outputString = outputString.substring(0, outputString.length()-2); //this takes off the newline char, and the last comma.
        outputString += "\n"; //add the newline character back on.
        outputString += "]";

        Gson g = new Gson();
        String s = g.toJson(outputString);

        try
        {
            out.write(s.getBytes());
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return outputString; //debug only
    }

    public static List<String> inputFile(Context context, FileInputStream in, List<String> names)
    {
        InputStreamReader inReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inReader);
        StringBuilder builder = new StringBuilder();
        String line;
        String tempJson = "";

        List<String> tempList = new ArrayList<>();

        try
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                builder.append(line);
                String readJson = builder.toString();
                Gson readGson = new Gson();
                tempJson = readGson.fromJson(readJson, String.class);
            }
            in.close(); //close the file here?


            JSONObject jobj = new JSONObject(tempJson);

            for (int i = 0; i < names.size(); i++)
            {
                tempList.add(jobj.getString(names.get(i))); //this adds the JSON string stored at every index i in names.
            }

            //debug:
            Toast.makeText(context, tempList.toString(), Toast.LENGTH_LONG).show();

            return tempList;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            //debug:
            Toast.makeText(context, tempList.toString(), Toast.LENGTH_LONG).show();

            return new ArrayList<>(Arrays.asList("File Input Error!")); //return a blank-ish list?
        }
    }
}



//            returnString = jobj.getString(name); // THIS IS HOW YOU USE THE JSON OBJECT AFTER YOU'VE READ IT IN FROM THE FILE
//            return returnString;