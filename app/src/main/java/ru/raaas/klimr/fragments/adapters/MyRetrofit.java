package ru.raaas.klimr.fragments.data;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 11.06.17.
 */

public class MyRetrofit {
    private Lessons[] lessArr;
    private static PersonApi personApi;
    private static CommonApi commonApi;
    private Retrofit retrofit;
    private int thr = 0;
    //TextView Teacher;
    List<Person> list;
    public MyRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://klimr.raaas.ru/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
    }
    public Person GetPerson(int id) {
        id = 2;
        personApi = retrofit.create(PersonApi.class); //Создаем объект, при помощи которого будем выполнять запросы
        //Call<List<Person>> call = personApi.getData();
        list = new ArrayList<>();
        Person per = new Person();
        try {
            per = personApi.getData(id).execute().body();
            Log.d("inform for me: ", per.getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*List<Person> values, String tasks []) {
        List<PersonAndTask> PaT = new ArrayList<>();
        for (int i = 0;  i < values.size(); i++){
            PaT.add(new PersonAndTask(values.get(i), tasks[i]));
        }*/
        /*Call<Person> call = personApi.getData(id);
        synchronized(list) {
            call.enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                    list.add(response.body());
                    list.notify();
                    thr = 1;
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {
                    Log.d("myi", "not");
                }
            });
        }
        commonApi = retrofit.create(CommonApi.class);
        commonApi.getData().enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                Common c = response.body();
                Log.d("myi", c.getPersons().get(0).getFName());
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                Log.d("myi", "not");
            }
        });
        synchronized(list) {
            try {
                if (thr != 1)
                    list.wait();
                Teacher.setText(list.get(0).getFName());
                Log.d("myi", "2: " + list.get(0).getFName());
                return rootView;
            }catch(Exception e) {e.printStackTrace(); Log.d("myi", "3 ");}
        }*/
        return per;
    }
    public List<Person> GetPerson() {
        commonApi = retrofit.create(CommonApi.class);
        commonApi.getData().enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                Common c = response.body();
                list = c.getPersons();
                Log.d("myi", c.getPersons().get(0).getFirstName());
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                Log.d("myi", "not");
            }
        });
        return list;
    }
}
