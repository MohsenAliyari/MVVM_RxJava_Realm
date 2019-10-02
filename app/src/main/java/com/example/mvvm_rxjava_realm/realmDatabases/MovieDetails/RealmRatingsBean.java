package com.example.mvvm_rxjava_realm.realmDatabases.MovieDetails;

import io.realm.RealmObject;

public class RealmRatingsBean extends RealmObject {

    private String Source;
    private String Value;

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }
}
