package com.example.basic;

public class Person{
    private String personName, phoneNo;
    private Integer personImage;
    Person(String personName, String phoneNo, Integer personImage){
        this.personName= personName;
        this.phoneNo= phoneNo;
        this.personImage= personImage;
    }
    public String getPersonName(){
        return personName;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public Integer getPersonImage(){
        return personImage;
    }
}
