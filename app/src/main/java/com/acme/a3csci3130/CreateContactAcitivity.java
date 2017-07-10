package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/** Creates a Contact by submitting the contents of the five EditText fields to a new node in FireBase.
 *
 * @author Juliano Franz
 * @author Joshua Morash
 * @version 2.0
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessIdField, primaryBusinessField, addressField, provinceOrTerritoryField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        nameField = (EditText) findViewById(R.id.name);
        businessIdField = (EditText) findViewById(R.id.businessId);
        provinceOrTerritoryField = (EditText) findViewById(R.id.provinceOrTerritory);
    }

    /** Submits the contents of the five EditText fields to a new node in FireBase.
     *
     * @author Juliano Franz
     * @author Joshua Morash
     * @version 2.0
     */
    public void submitInfoButton(View v) {

        String key = appState.firebaseReference.push().getKey();
        String businessId = businessIdField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String provinceOrTerritory = provinceOrTerritoryField.getText().toString();
        Contact business = new Contact(key, name, businessId, primaryBusiness, address, provinceOrTerritory);

        appState.firebaseReference.child(key).setValue(business);

        finish();

    }
}
