package com.clayons.interviewquestions.ORM;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import org.parceler.Parcel;

/**
 * Entity mapped to table "PERSON".
 */
@Parcel
public class Person {

    private Long id;
    private String FirstName;
    private String LastName;
    private Integer Age;
    private String PhoneNum;
    private String PhotoUrl;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String FirstName, String LastName, Integer Age, String PhoneNum, String PhotoUrl) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
        this.PhoneNum = PhoneNum;
        this.PhotoUrl = PhotoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String PhotoUrl) {
        this.PhotoUrl = PhotoUrl;
    }

}
