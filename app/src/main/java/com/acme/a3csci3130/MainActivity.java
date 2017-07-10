package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/** This Activity has a button that will lead to the ContactCreateActivity. It also displays the
 * name of each business contact from FireBase in a listView and allows the user to click on a
 * name, which leads the user to the DetailViewActivity.
 *
 * @author Juliano Franz
 * @version 1.0
 */
public class MainActivity extends Activity {


    private ListView contactListView;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("contacts");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Contact>(this, Contact.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Contact model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact business = (Contact) firebaseAdapter.getItem(position);
                showDetailView(business);
            }
        });
    }

    /** Starts the CreateContactActivity.
     *
     * @author Juliano Franz
     * @version 1.0
     */
    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateContactAcitivity.class);
        startActivity(intent);
    }

    /** Starts the DetailViewActivity and sends a Contact object to it.
     *
     * @author Juliano Franz
     * @version 1.0
     */
    private void showDetailView(Contact business)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Contact", business);
        startActivity(intent);
    }



}
