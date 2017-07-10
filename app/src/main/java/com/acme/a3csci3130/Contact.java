package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * FireBase database. This is converted to a JSON format
 *
 * @author Juliano Franz
 * @author Joshua Morash
 * @version 2.0
 */
public class Contact implements Serializable {

    public  String key;
    public  String name;
    public  String businessId;
    public  String primaryBusiness;
    public  String address;
    public  String provinceOrTerritory;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String key, String name, String businessId, String primaryBusiness, String address, String provinceOrTerritory){
        this.key = key;
        this.name = name;
        this.businessId = businessId;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.provinceOrTerritory = provinceOrTerritory;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("businessId", businessId);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("provinceOrTerritory", provinceOrTerritory);

        return result;
    }
}
