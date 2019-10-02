package com.example.mvvm_rxjava_realm.realmDatabases.MoviesItem;

import io.realm.RealmObject;

public class RealmMoviesItem extends RealmObject {


    private String Title ;
    private String Year ;
    private String imdbID ;
    private String Type ;
    private String Poster ;

    public RealmMoviesItem() {
  /*      this.setMovies(movies);
        setTitle(movies.getTitle());
        setImdbID(movies.getImdbID());
        setPoster(movies.getPoster());
        setType(movies.getType());
        setYear(movies.getYear());*/
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }


}
