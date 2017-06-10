package ru.raaas.klimr.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.String;
import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
import java.net.URL;
import java.net.URLConnection;
import java.net.*;
import java.io.*;
import android.view.LayoutInflater;

public class Person {
    int id;
    String first_name, second_name;
    public Person(){}
    public Person(int _id) {
        //...
    }
}
public class DwafDeserializer implements JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Person dwarf = new Person();
        dwarf.setDwarfAge(jsonObject.get("age").getAsInt());

        dwarf.setFacialHair((FacialHair) context.deserialize(jsonObject.get("facialHair"), FacialHair.class));

        JsonArray weapons = jsonObject.getAsJsonArray("weapons");
        for(JsonElement weapon : weapons) {
            if(weapon.isJsonPrimitive()) {
                dwarf.addWeapon(new Weapon(weapon.getAsString()));
            } else {
                dwarf.addWeapon((UniqueWeapon) context.deserialize(weapon, UniqueWeapon.class));
            }
        }

        return dwarf;
    }
}