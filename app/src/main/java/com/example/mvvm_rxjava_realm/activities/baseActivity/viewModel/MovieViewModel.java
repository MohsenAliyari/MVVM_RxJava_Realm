package com.example.mvvm_rxjava_realm.activities.baseActivity.viewModel;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvm_rxjava_realm.BR;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.baseActivity.model.Movies;
import com.example.mvvm_rxjava_realm.activities.baseActivity.view.adapter.MoviesAdapter;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.model.DetailsModel;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.view.DetailsActivity;
import com.example.mvvm_rxjava_realm.realmDatabases.MoviesItem.RealmMoviesItem;
import com.example.mvvm_rxjava_realm.services.MovieRepositoty;
import com.example.mvvm_rxjava_realm.utils.AppConfig;
import com.example.mvvm_rxjava_realm.utils.CheckNetworkConnection;
import com.example.mvvm_rxjava_realm.utils.GlobalThings;
import com.example.mvvm_rxjava_realm.utils.MyApplication;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;

public class MovieViewModel extends BaseObservable {


    private static Activity activity;
    private Movies movies = new Movies();
    private ArrayList<MovieViewModel> modelArrayList = new ArrayList<>();
    private Integer  gonUnless;
    private MovieRepositoty repositoty;
    boolean doubleBackToExitPressedOnce = false;

    public MovieViewModel(Movies movies) {
        this.movies = movies;
    }

    public MovieViewModel(final Activity activity) {
        this.activity = activity;
        repositoty = new MovieRepositoty(activity);


        getMovieListDataRepository(activity);

        repositoty.getListMutableLiveData().observe((LifecycleOwner) activity, new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(final ArrayList<Movies> movies) {
                if (movies.size() > 0) {
                    for (int i = 0; i < movies.size(); i++) {
                        MovieViewModel viewModel = new MovieViewModel(movies.get(i));
                        modelArrayList.add(viewModel);
                    }
                    setModelArrayList(modelArrayList);
                    setGonUnless(View.GONE);
                } else {
                    wrongDialog(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            getMovieListDataRepository(activity);
                        }
                    }, finishOnclick);
                }

            }
        });
    }

    /**
     * check Connection to get data from exist repository
     */
    private void getMovieListDataRepository(Activity activity) {
        if (CheckNetworkConnection.isConnectionAvailable(activity)) {
            setGonUnless(View.VISIBLE);
            repositoty.getMoviesList(AppConfig.Apikey, AppConfig.Source);
        } else {
            repositoty.getDataFromExistRepository();
        }
    }

    @BindingAdapter("bind:recyclerMovies")
    public static void recyclerViewBinder(final RecyclerView recyclerView,
                                          ArrayList<MovieViewModel> modelArrayList) {

        MoviesAdapter adapter = new MoviesAdapter(modelArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(MyApplication.getAppContext(),
                2, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        if (modelArrayList.size() > 0) {
            updateRealmObject_MovieList(modelArrayList);
        }
    }


    @Bindable
    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @Bindable
    public ArrayList<MovieViewModel> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<MovieViewModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
        notifyPropertyChanged(BR.modelArrayList);
    }


    public void onClick(final View view, final Movies movies) {

//                imdbLiveData.setValue(movies.getImdbID());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                ((MyApplication) activity.getApplication()).bus().send(movies);
                getMovieDetails(movies);
            }
        }, 100);

    }

    private void getMovieDetails(final Movies movies) {
        if (CheckNetworkConnection.isConnectionAvailable(activity)) {
            MovieRepositoty repositoty = new MovieRepositoty(activity);
            repositoty.getMovieDetails(AppConfig.Apikey, movies.getImdbID());
            DetailsActivity.StartDeTailsActivity(activity);
        } else {
            MovieRepositoty repositoty = new MovieRepositoty(activity);
            DetailsModel detailsModel = repositoty.getRealmObject_MovieDetails(movies.getImdbID(), activity);
            if (detailsModel.getImdbID() != null) {
                DetailsActivity.StartDeTailsActivity(activity);
            } else {
                wrongDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getMovieDetails(movies);
                    }
                }, dismissOnclick);
            }

        }
    }

    private void wrongDialog(DialogInterface.OnClickListener posetiveBtnOnclick, DialogInterface.OnClickListener negativBtnOnclick) {
        GlobalThings.getInstance().showDialog(activity,
                "Oops!",
                MyApplication.getAppContext().getResources().getString(R.string.somethingWrong),
                "Retry",
                posetiveBtnOnclick, "cancel", negativBtnOnclick);
    }

    private DialogInterface.OnClickListener dismissOnclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

        }
    };
    private DialogInterface.OnClickListener finishOnclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            activity.finish();
        }
    };

    /**
     * Update Realm database
     *
     * @param modelArrayList
     */
    private static void updateRealmObject_MovieList(final ArrayList<MovieViewModel> modelArrayList) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    final RealmResults<RealmMoviesItem> results = realm.where(RealmMoviesItem.class).findAll();

                    for (int i = 0; i < modelArrayList.size(); i++) {
                        final Movies movies = modelArrayList.get(i).getMovies();

                        RealmMoviesItem object = realm.where(RealmMoviesItem.class).equalTo("imdbID",
                                movies.getImdbID()).findFirst();
                        // update if exist field
                        if (object != null) {

                            object.setTitle(movies.getTitle());
                            object.setYear(movies.getYear());
                            object.setImdbID(movies.getImdbID());
                            object.setPoster(movies.getPoster());
                            object.setType(movies.getType());
                            realm.insertOrUpdate(object);

                            //insert not exist fiele
                        } else {
                            RealmMoviesItem realmMoviesItem = realm.createObject(RealmMoviesItem.class);
                            realmMoviesItem.setTitle(movies.getTitle());
                            realmMoviesItem.setYear(movies.getYear());
                            realmMoviesItem.setImdbID(movies.getImdbID());
                            realmMoviesItem.setPoster(movies.getPoster());
                            realmMoviesItem.setType(movies.getType());
                            realm.insertOrUpdate(realmMoviesItem);
                        }
                    }
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Bindable
    public Integer getGonUnless() {
        return gonUnless;
    }

    public void setGonUnless(Integer gonUnless) {
        this.gonUnless = gonUnless;
        notifyPropertyChanged(BR.gonUnless);
    }

    public void onResume() {
        //do something
    }

    public void onBackPressed() {
        onBackKeyPress();
    }

    public boolean onBackKeyPress() {

        if (exit_twiceClick()) {

            return true;
        }

        return false;
    }
        private  boolean exit_twiceClick(){

            if (doubleBackToExitPressedOnce) {
                return true;
            }
            this.doubleBackToExitPressedOnce = true;
            GlobalThings.showToast(activity, activity.getResources().getString(R.string.exit_twice_click));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
            return false;
        }

}
