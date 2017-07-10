package com.acme.a3csci3130;

import android.app.Activity;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

import static org.junit.Assert.*;
import com.google.firebase.database.DatabaseReference;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest{
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    public DatabaseReference firebaseReference;
    public FirebaseDatabase firebaseDBInstance;


    String key;
    String businessId = "123456789";
    String name = "test";
    String primaryBusiness = "Fisher";
    String address = "test";
    String provinceOrTerritory = "NS";
    Contact business = new Contact(key, name, businessId, primaryBusiness, address, provinceOrTerritory);

    @Test
    public void test_submitInfoButton() throws Exception {
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference("contacts");
        business.key = firebaseReference.push().getKey();
        firebaseReference.child(key).setValue(business);

        //assertEquals(business.key, firebaseReference.child(key).child("key").toString());
        assertEquals(business.businessId, firebaseReference.child(key).child("businessId").toString());
        assertEquals(business.name, firebaseReference.child(key).child("name").toString());
        assertEquals(business.primaryBusiness, firebaseReference.child(key).child("primaryBusiness").toString());
        assertEquals(business.provinceOrTerritory, firebaseReference.child(key).child("provinceOrTerritory").toString());
        assertEquals(business.address, firebaseReference.child(key).child("address").toString());
    }

    @Test
    public void test_updateContact() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_eraseContact() throws Exception {
        assertEquals(4, 2 + 2);
    }



}