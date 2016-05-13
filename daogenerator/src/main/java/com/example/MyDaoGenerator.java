package com.example;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.clayons.interviewquestions.ORM");
        addPerson(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void addPerson (Schema schema){
        Entity person = schema.addEntity("Person");
        person.addIdProperty();
        person.addStringProperty("FirstName");
        person.addStringProperty("LastName");
        person.addIntProperty("Age");
        person.addStringProperty("PhoneNum");
        person.addStringProperty("PhotoUrl");
    }
}
