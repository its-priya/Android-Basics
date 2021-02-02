package com.example.basic;

import java.io.Serializable;

public class PersonData implements Serializable {
    String firstName, lastName, emailId;
    PersonData(String fName, String lName, String email){
        firstName= fName;
        lastName= lName;
        emailId= email;
    }
}
