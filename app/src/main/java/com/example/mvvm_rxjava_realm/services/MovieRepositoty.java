package com.example.mvvm_rxjava_realm.services;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.baseActivity.model.GenerallRespons;
import com.example.mvvm_rxjava_realm.activities.baseActivity.model.Movies;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.model.DetailsModel;
import com.example.mvvm_rxjava_realm.realmDatabases.MovieDetails.RealmDetailsModel;
import com.example.mvvm_rxjava_realm.realmDatabases.MoviesItem.RealmMoviesItem;
import com.example.mvvm_rxjava_realm.utils.GlobalThings;
import com.example.mvvm_rxjava_realm.utils.MyApplication;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MovieRepositoty {

    private final ApiInterface apiInterface = RetrofitHandler.getApiService();
    private final MutableLiveData<ArrayList<Movies>> listMutableLiveData = new MutableLiveData<>();// to use mutable live data
    private DetailsModel detailsModel = new DetailsModel();
    private final Activity activity;

    public MovieRepositoty(Activity activity) {
        this.activity = activity;
    }

    /**
     * //  you can get movies list as mutable live data
     *
     * @return
     */
    public void getMoviesList(final String apiKey, final String source) {

        Call<GenerallRespons> getMovies = apiInterface.getMovies(apiKey, source);
        getMovies.enqueue(new Callback<GenerallRespons>() {
            @Override
            public void onResponse(Call<GenerallRespons> call, Response<GenerallRespons> response) {
                if (response.code() == 200) {
                    GenerallRespons generallRespons = response.body();
                    if (generallRespons.getResponse()) {
                        listMutableLiveData.setValue(generallRespons.getSearch());

                    } else {
                        wrongDialog(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getMoviesList(apiKey, source);
                            }
                        });
                    }
                } else {
                    wrongDialog(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getMoviesList(apiKey, source);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GenerallRespons> call, Throwable t) {
                Log.e("GetMoviesList :", t.getMessage());
                wrongDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getMoviesList(apiKey, source);
                    }
                });
            }
        });


    }

    private void wrongDialog(DialogInterface.OnClickListener posetiveBtnOnclick) {
        GlobalThings.getInstance().showDialog(activity,
                "Oops!",
                MyApplication.getAppContext().getResources().getString(R.string.somethingWrong),
                "Retry",
                posetiveBtnOnclick, "cancel", dismissOnclick);
    }

    public MutableLiveData<ArrayList<Movies>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    /**
     * you can get DetailsModel as object anyWhere you Called
     *
     * @param apiKey
     * @param imdb
     * @return
     */
    public void getMovieDetails(final String apiKey, final String imdb) {


        Call<DetailsModel> getMovies = apiInterface.getMovieInfo(apiKey, imdb);
        getMovies.enqueue(new Callback<DetailsModel>() {


            @Override
            public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                if (response.code() == 200) {
                    detailsModel = response.body();
                    /**
                     *  you can Observe that every where you need
                     */
                    ((MyApplication) activity.getApplication()).bus().send(detailsModel);
                } else {

                    wrongDialog(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getMovieDetails(apiKey, imdb);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DetailsModel> call, Throwable t) {
                Log.e("GetMoviesList :", t.getMessage());
                wrongDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getMovieDetails(apiKey, imdb);
                    }
                });
            }

        });

    }

    private DialogInterface.OnClickListener dismissOnclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            activity.finish();
        }
    };



    /**
     * Get Movie List from Realm Database
     */
    public void getDataFromExistRepository() {
        final ArrayList<Movies> arrayList = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    final RealmResults<RealmMoviesItem> results = realm.where(RealmMoviesItem.class).findAll();

                    for (int i = 0; i < results.size(); i++) {
                        Movies movies = new Movies();
                        movies.setTitle(results.get(i).getTitle());
                        movies.setImdbID(results.get(i).getImdbID());
                        movies.setYear(results.get(i).getYear());
                        movies.setType(results.get(i).getType());
                        movies.setPoster(results.get(i).getPoster());
                        arrayList.add(movies);
                    }
                    listMutableLiveData.setValue(arrayList);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    /**
     * Update Movie Details Database
     */
    public DetailsModel getRealmObject_MovieDetails(final String imdbId, final Activity activity) {
        final DetailsModel detailsModel = new DetailsModel();
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    final RealmResults<RealmDetailsModel> results = realm.where(RealmDetailsModel.class).findAll();
                    RealmDetailsModel object = realm.where(RealmDetailsModel.class).equalTo("imdbID",
                            imdbId).findFirst();

                    // get item  if exist field
                    if (object != null) {
                        detailsModel.setTitle(object.getTitle());
                        detailsModel.setYear(object.getYear());
                        detailsModel.setImdbID(object.getImdbID());
                        detailsModel.setPoster(object.getPoster());
                        detailsModel.setType(object.getType());
                        detailsModel.setActors(object.getActors());
                        detailsModel.setAwards(object.getAwards());
                        detailsModel.setBoxOffice(object.getBoxOffice());
                        detailsModel.setCountry(object.getCountry());
                        detailsModel.setDirector(object.getDirector());
                        detailsModel.setDVD(object.getDVD());
                        detailsModel.setGenre(object.getGenre());
                        detailsModel.setImdbRating(object.getImdbRating());
                        detailsModel.setImdbVotes(object.getImdbVotes());
                        detailsModel.setLanguage(object.getLanguage());
                        detailsModel.setMetascore(object.getMetascore());
                        detailsModel.setWriter(object.getWriter());
                        detailsModel.setWebsite(object.getWebsite());
                        detailsModel.setPlot(object.getPlot());
                        detailsModel.setRuntime(object.getRuntime());
                        detailsModel.setResponse(object.getResponse());
                        detailsModel.setProduction(object.getProduction());
                        detailsModel.setRated(object.getRated());
                        // to get all ratings
                        ArrayList<DetailsModel.RatingsBean> ratingsBeans = new ArrayList<>();
                        for (int i = 0; i < object.getRatings().size(); i++) {

                            DetailsModel.RatingsBean ratingsBean = new DetailsModel.RatingsBean();
                            ratingsBean.setSource(object.getRatings().get(i).getSource());
                            ratingsBean.setValue(object.getRatings().get(i).getValue());
                            ratingsBeans.add(ratingsBean);
                        }
                        detailsModel.setRatings(ratingsBeans);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ((MyApplication) activity.getApplication()).bus().send(detailsModel);
                            }
                        }, 100);

                    } else {
               /*         wrongDialog(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                getMovieDetails(apiKey, imdb);
                            }
                        });*/
                    }
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return detailsModel;
    }

}
