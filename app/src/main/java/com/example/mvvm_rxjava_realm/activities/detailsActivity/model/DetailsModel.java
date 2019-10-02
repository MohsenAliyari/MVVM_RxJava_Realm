package com.example.mvvm_rxjava_realm.activities.detailsActivity.model;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * i used the GSONFormat to Create this Data Model object
 */
public class DetailsModel {


    /**
     * Title : Batman Begins
     * Year : 2005
     * Rated : PG-13
     * Released : 15 Jun 2005
     * Runtime : 140 min
     * Genre : Action, Adventure
     * Director : Christopher Nolan
     * Writer : Bob Kane (characters), David S. Goyer (story), Christopher Nolan (screenplay), David S. Goyer (screenplay)
     * Actors : Christian Bale, Michael Caine, Liam Neeson, Katie Holmes
     * Plot : After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.
     * Language : English, Urdu, Mandarin
     * Country : USA, UK
     * Awards : Nominated for 1 Oscar. Another 14 wins & 72 nominations.
     * Poster : https://m.media-amazon.com/images/M/MV5BZmUwNGU2ZmItMmRiNC00MjhlLTg5YWUtODMyNzkxODYzMmZlXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg
     * Ratings : [{"Source":"Internet Movie Database","Value":"8.2/10"},{"Source":"Rotten Tomatoes","Value":"84%"},{"Source":"Metacritic","Value":"70/100"}]
     * Metascore : 70
     * imdbRating : 8.2
     * imdbVotes : 1,212,892
     * imdbID : tt0372784
     * Type : movie
     * DVD : 18 Oct 2005
     * BoxOffice : $204,100,000
     * Production : Warner Bros. Pictures
     * Website : https://www.warnerbros.com/batman-begins
     * Response : True
     */

    @SerializedName("Title")
    private String Title;
    @SerializedName("Year")
    private String Year;
    @SerializedName("Rated")
    private String Rated;
    @SerializedName("Released")
    private String Released;
    @SerializedName("Runtime")
    private String Runtime;
    @SerializedName("Genre")
    private String Genre;
    @SerializedName("Director")
    private String Director;
    @SerializedName("Writer")
    private String Writer;
    @SerializedName("Actors")
    private String Actors;
    @SerializedName("Plot")
    private String Plot;
    @SerializedName("Language")
    private String Language;
    @SerializedName("Country")
    private String Country;
    @SerializedName("Awards")
    private String Awards;
    @SerializedName("Poster")
    private String Poster;
    @SerializedName("Metascore")
    private String Metascore;
    @SerializedName("imdbRating")
    private String imdbRating;
    @SerializedName("imdbVotes")
    private String imdbVotes;
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("Type")
    private String Type;
    @SerializedName("DVD")
    private String DVD;
    @SerializedName("BoxOffice")
    private String BoxOffice;
    @SerializedName("Production")
    private String Production;
    @SerializedName("Website")
    private String Website;
    @SerializedName("Response")
    private String Response;
    @SerializedName("Ratings")
    private ArrayList<RatingsBean> Ratings;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String Released) {
        this.Released = Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String Awards) {
        this.Awards = Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String Metascore) {
        this.Metascore = Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
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

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDVD() {
        return DVD;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String BoxOffice) {
        this.BoxOffice = BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String Production) {
        this.Production = Production;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }

    public ArrayList<RatingsBean> getRatings() {
        return Ratings;
    }

    public void setRatings(ArrayList<RatingsBean> Ratings) {
        this.Ratings = Ratings;
    }

    public static class RatingsBean {
        /**
         * Source : Internet Movie Database
         * Value : 8.2/10
         */

        @SerializedName("Source")
        private String Source;
        @SerializedName("Value")
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

    @BindingAdapter("tools:imagUrl")
    public static void setImageUrl(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
