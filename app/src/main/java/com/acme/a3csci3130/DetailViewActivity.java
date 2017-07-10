package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/** Displays the contact information for the selected business.
 *
 * @author Juliano Franz
 * @author Joshua Morash
 * @version 2.0
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, businessIdField, primaryBusinessField, addressField, provinceOrTerritoryField;
    private String key;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());


        nameField = (EditText) findViewById(R.id.name);
        businessIdField = (EditText) findViewById(R.id.businessId);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceOrTerritoryField = (EditText) findViewById(R.id.provinceOrTerritory);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessIdField.setText(receivedPersonInfo.businessId);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceOrTerritoryField.setText(receivedPersonInfo.provinceOrTerritory);
        }
    }

    /** Updates the contact in FireBase.
     *
     * @author Joshua Morash
     * @version 2.0
     */
    public void updateContact(View v){
        key = receivedPersonInfo.key;
        nameField = (EditText) findViewById(R.id.name);
        businessIdField = (EditText) findViewById(R.id.businessId);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceOrTerritoryField = (EditText) findViewById(R.id.provinceOrTerritory);


        appState.firebaseReference.child(key).child("name").setValue(nameField.getText().toString());
        appState.firebaseReference.child(key).child("primaryBusiness").setValue(primaryBusinessField.getText().toString());
        appState.firebaseReference.child(key).child("provinceOrTerritory").setValue(provinceOrTerritoryField.getText().toString());
        appState.firebaseReference.child(key).child("address").setValue(addressField.getText().toString());
        appState.firebaseReference.child(key).child("businessId").setValue(businessIdField.getText().toString());
        finish();
    }

    /** Deletes the contact from FireBase.
     *
     * @author Joshua Morash
     * @version 2.0
     */
    public void eraseContact(View v)
    {
        key = receivedPersonInfo.key;
        appState.firebaseReference.child(key).removeValue();
        finish();
        //TODO: Erase contact functionality
    }

}
