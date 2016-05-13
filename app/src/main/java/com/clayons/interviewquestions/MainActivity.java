package com.clayons.interviewquestions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.clayons.interviewquestions.ORM.Person;
import com.clayons.interviewquestions.ORM.DaoDbHelper;
import com.clayons.interviewquestions.ORM.PersonDao;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of person objects is given.
 * Show first name, last name, and an avatar image as a list on MainActivity.
 * Show all information on DetailActivity.
 * First name and last name should be editable. add a save button for storing this information.
 * Create an interface class to store the data in a persistent storage.
 *
 * Condition:
 * Alternate the background color for the list on MainActivity - first white, second black, third white, etc.
 * "Like" button on the detail page should override the background color on the main page with a blue color background.
 * bonus points for using MVP structure.
 * bonus points for suggesting/using up-to-date animation and transition effects.
 * bonus points for using well known libraries.
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = "Activity";
    private ListView lv;
    private CustomAdapter customAdapter;
    private SharedPreferences sharedPreferences;
    private DaoDbHelper daoDbHelper;
    private PersonDao personDao;
    private List<Person> personList;


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(sharedPreferences.getBoolean("isEdited", false)){
            personList = personDao.loadAll();
            customAdapter = new CustomAdapter(this, R.layout.item_person_summary, personList);
            lv.setAdapter(customAdapter);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isEdited", false);
            editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sharedPreferences = this.getSharedPreferences("pref_file", Context.MODE_PRIVATE);
        boolean firstInit = sharedPreferences.getBoolean("isFirst", true);
        daoDbHelper = DaoDbHelper.getInstance(this);
        personDao = daoDbHelper.getPersonDao();
        if(firstInit){
            initPerson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirst", false);
            editor.commit();
        }

        personList = personDao.loadAll();

        lv = (ListView) findViewById(R.id.list_view);
        customAdapter = new CustomAdapter(this, R.layout.item_person_summary, personList);
        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Person p = personList.get(position);
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                Parcelable wrapped = Parcels.wrap(p);
                bundle.putParcelable("wrap", wrapped);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


    }

    private void initPerson() {
        personDao.insert(new Person(null, "John", "Doe", 20, "111-222-3333", "http://i58.tinypic.com/2z6fa6t.jpg"));
        personDao.insert(new Person(null, "Jane", "Kish", 30, "111-222-3334", "http://i58.tinypic.com/2z6fdsl.jpg"));
        personDao.insert(new Person(null, "Sam", "Jackson", 24, "111-222-3335", "http://i60.tinypic.com/2z6fdbr.jpg"));
        personDao.insert(new Person(null, "Pete", "Dorey", 15, "111-222-3336", "http://i57.tinypic.com/2z6fb0p.jpg"));
        personDao.insert(new Person(null, "George", "Mime", 5, "111-222-3337", "http://i59.tinypic.com/2z6fakl.jpg"));
    }
}
