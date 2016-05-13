package com.clayons.interviewquestions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.clayons.interviewquestions.ORM.DaoDbHelper;
import com.clayons.interviewquestions.ORM.Person;
import com.clayons.interviewquestions.ORM.PersonDao;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import de.greenrobot.dao.async.AsyncSession;


public class DetailActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView firstNameView;
    private TextView lastNameView;
    private TextView ageView;
    private TextView telView;
    private ImageView imageView;
    private Button likeButton;
    private Person person;
    private SharedPreferences sharedPreferences;
    private boolean isLike;
    private boolean isDataUpdated = false;
    private PersonDao personDao;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = this.getSharedPreferences("pref_file", Context.MODE_PRIVATE);
        person = Parcels.unwrap(getIntent().getParcelableExtra("wrap"));
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        int age = person.getAge();
        String phoneNum = person.getPhoneNum();
        String photoUrl = person.getPhotoUrl();
        scrollView = (ScrollView) findViewById(R.id.person_scroll);
        firstNameView = (TextView) findViewById(R.id.tvFristName);
        lastNameView  = (TextView) findViewById(R.id.tvLastName);
        ageView = (TextView) findViewById(R.id.tvAge);
        telView = (TextView) findViewById(R.id.tvPhoneNum);
        imageView = (ImageView) findViewById(R.id.ivAvatar);
        likeButton = (Button) findViewById(R.id.btnLike);
        firstNameView.setText(firstName);
        lastNameView.setText(lastName);
        ageView.setText("Age : " + age);
        telView.setText("Phone number : " + phoneNum);
        Picasso.with(this).load(photoUrl).into(imageView);

        isLike = sharedPreferences.getBoolean(String.valueOf(person.getId()), false);
        if(isLike){
            scrollView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
            likeButton.setText("Unlike");
        }
    }

    public void clickLike(View view) {

        if(isLike){
            scrollView.setBackgroundColor(getResources().getColor(android.R.color.white));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(String.valueOf(person.getId()), false);
            editor.commit();
            isLike = false;
            likeButton.setText("Unlike");
        } else{
            scrollView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(String.valueOf(person.getId()), true);
            editor.commit();
            isLike = true;
            likeButton.setText("like");
        }
        Toast.makeText(this, "Like = " + isLike, Toast.LENGTH_SHORT).show();

    }

    public void clickEdit(View view) {

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.edit_dialog_layout, null);
        final EditText firstNameEditText = (EditText) dialoglayout.findViewById(R.id.edit_first_name);
        final EditText lastNameEditText = (EditText) dialoglayout.findViewById(R.id.edit_last_name);
        firstNameEditText.setText(person.getFirstName());
        lastNameEditText.setText(person.getLastName());

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
        alertDialog.setView(dialoglayout);

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.cancel();
            }
        });

        alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                personDao = DaoDbHelper.getInstance(DetailActivity.this).getPersonDao();
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                person.setFirstName(firstName);
                person.setLastName(lastName);
                personDao.update(person);
                firstNameView.setText(firstName);
                lastNameView.setText(lastName);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isEdited", true);
                editor.commit();
                Toast.makeText(DetailActivity.this, " Data updated!", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        alertDialog.show();

    }

}
