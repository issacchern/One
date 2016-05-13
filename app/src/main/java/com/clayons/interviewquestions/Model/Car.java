package com.clayons.interviewquestions.Model;

/**
 * Created by jaychung on 11/16/15.
 */
public class Car {
    private String plateName;
    private String plateStateName;
    private Integer yearMade;
    private String color;

    public Car(String plateName, String plateStateName, Integer yearMade, String color) {
        this.plateName = plateName;
        this.plateStateName = plateStateName;
        this.yearMade = yearMade;
        this.color = color;
    }
}
