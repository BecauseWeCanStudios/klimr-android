package ru.raaas.klimr.fragments.data;

/**
 * Created by user on 12.06.17.
 */
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;

import ru.raaas.klimr.R;

public class ListPersonAndTaskAdapter extends ArrayAdapter<PersonAndTask> {

    public ListPersonAndTaskAdapter(Context context,
                         List<PersonAndTask> values) {
        super(context, 0, values);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PersonAndTask PaT = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_person_task, parent, false);
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView task = (TextView) convertView.findViewById(R.id.task);
        // Populate the data into the template view using the data object
        name.setText(PaT.person.getFirstName() + " " + PaT.person.getLastName());
        task.setText(PaT.task);
        //Log.d("I ", person.getFirstName());
        // Return the completed view to render on screen
        return convertView;
    }
}
