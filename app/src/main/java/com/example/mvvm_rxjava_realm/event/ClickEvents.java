package com.example.mvvm_rxjava_realm.event;

public class ClickEvents {


    String objectClicked;

    String objectID;

    public ClickEvents(String object, String id) {
        this.objectClicked = object;
        this.objectID = id;
    }


    public String getObjectClicked() {
        return objectClicked;
    }

    public String getObjectID() {
        return objectID;
    }


}
