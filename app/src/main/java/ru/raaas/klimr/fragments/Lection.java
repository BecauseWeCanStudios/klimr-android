package ru.raaas.klimr.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.net.*;
import java.io.*;
public class Lection extends Fragment {
    private class Teacher {
        String First_name, Second_name;
        int Id;
        public Teacher(int Id) throws Exception{
            URL url = new URL("https://klimr.raaas.ru/api/info/teachers/" + Id);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        }
        public String get_smth(String resurse, String smth){
            //Pattern p = Pattern.compile(smth + " \"(.*)\"");
            //Matcher m = p.matcher(resurse);
            String goal = new String();
            return goal;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(ru.raaas.klimr.R.layout.fragment_lection, container, false);
        TextView Teacher = (TextView) rootView.findViewById(ru.raaas.klimr.R.id.teacher);
        Teacher.setText("Klenin");
        return rootView;
    }
}

/*import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View.*;
import android.widget.TextView;

public class Lection extends AppCompatActivity {
    //private TextView Teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lection);
        TextView Teacher = (TextView)findViewById(R.id.teacher);
        Teacher.setText("Klenin");
    }
}*/