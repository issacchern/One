package com.clayons.interviewquestions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clayons.interviewquestions.ORM.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Issac on 5/13/2016.
 */
public class CustomAdapter extends ArrayAdapter<Person>{

    private static final String TAG = "Custom adapter";
    private Context context;
    private List<Person> objects;


    public CustomAdapter(Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public Person getItem(int position) {
        return objects.get(position);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Person item = getItem(position);

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.item_person_summary, parent, false);
        }
        ViewHolder vh = new ViewHolder();
        vh.imageView = (ImageView) v.findViewById(R.id.ivAvatarPerson);
        vh.firstNameView = (TextView) v.findViewById(R.id.tvFirstName);
        vh.lastNameView = (TextView) v.findViewById(R.id.tvLastName);


        // even number is black background
        if(position % 2 == 1){
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.item_linear_layout);
            ll.setBackgroundColor(context.getResources().getColor(android.R.color.black));
            vh.firstNameView.setTextColor(context.getResources().getColor(android.R.color.white));
            vh.lastNameView.setTextColor(context.getResources().getColor(android.R.color.white));
        }

        Picasso.with(getContext()).load(item.getPhotoUrl()).fit().into(vh.imageView);
        vh.firstNameView.setText(item.getFirstName());
        vh.lastNameView.setText(item.getLastName());
        v.setTag(vh);
        return v;
    }


    private static class ViewHolder{
        ImageView imageView;
        TextView firstNameView;
        TextView lastNameView;

    }



}
