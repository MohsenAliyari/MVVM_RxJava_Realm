package com.example.mvvm_rxjava_realm.activities.detailsActivity.viewModel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvm_rxjava_realm.BR;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.baseActivity.model.Movies;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.model.DetailsModel;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.view.adapter.RatingsAdapter;
import com.example.mvvm_rxjava_realm.realmDatabases.MovieDetails.RealmDetailsModel;
import com.example.mvvm_rxjava_realm.realmDatabases.MovieDetails.RealmRatingsBean;
import com.example.mvvm_rxjava_realm.utils.GlobalThings;
import com.example.mvvm_rxjava_realm.utils.MyApplication;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import java.util.ArrayList;

public class DetailsViewModel extends BaseObservable {
    private Activity activity;
    private DetailsModel detailsModel = new DetailsModel();
    public CompositeDisposable detailsModelCompositeDisposable = new CompositeDisposable();
    DetailsViewModel detailsViewModel;
    private String imdbID = "";
    private Integer gon_Unless;


    public DetailsViewModel(DetailsModel detailsModel) {
        this.detailsModel = detailsModel;
    }

    public DetailsViewModel(final Activity activity) {
        this.activity = activity;
//        setGon_Unless(View.VISIBLE);
        onClickEvents();

    }

    @Bindable
    public DetailsModel getDetailsModel() {
        return detailsModel;
    }


    public void setDetailsModel(DetailsModel detailsModel) {
        this.detailsModel = detailsModel;

    }

    @Bindable
    public DetailsViewModel getDetailsViewModel() {
        return detailsViewModel;
    }

    public void setDetailsViewModel(DetailsViewModel detailsViewModel) {
        this.detailsViewModel = detailsViewModel;
        notifyPropertyChanged(BR.detailsViewModel);
    }

    @BindingAdapter("tools:recyclerRatings")
    public static void recyclerViewBinder(final RecyclerView recyclerView,
                                          ArrayList<DetailsModel.RatingsBean> ratingsBeans) {
        if (ratingsBeans != null) {
            if (ratingsBeans.size() > 0) {
                RatingsAdapter adapter = new RatingsAdapter(ratingsBeans);
                recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getAppContext(),
                        RecyclerView.HORIZONTAL, false));
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
        }
    }

    public void web_play_OnClick(View view, String url) {

        try {
            Uri marketUri = Uri.parse(url);
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, marketUri));
        } catch (Exception e) {
            e.printStackTrace();
            showOkDialog();
        }
    }

    private void showOkDialog() {
        GlobalThings.getInstance().showOkDialog(activity, "خطا!!", activity.getResources().getString(R.string.cantPlayVideo),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }


    private void onClickEvents() {

        this.detailsModelCompositeDisposable.add(
                ((MyApplication) activity.getApplication())
                        .bus()
                        .toObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object object) throws Exception {
                                if (object instanceof Movies) {
                                    imdbID = ((Movies) object).getImdbID();
                                    // do something
                                }
                                if (object instanceof DetailsModel) {
                                    DetailsViewModel detailsViewModel = new DetailsViewModel((DetailsModel) object);
                                    setDetailsViewModel(detailsViewModel);
                                    updateRealmObject_MovieDetails((DetailsModel) object);
                                    setGon_Unless(View.GONE);
                                }
                            }
                        }));

    }

    /**
     * Update Movie Details Database
     *
     * @param detailsModel
     */
    private static void updateRealmObject_MovieDetails(final DetailsModel detailsModel) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    final RealmResults<RealmDetailsModel> results = realm.where(RealmDetailsModel.class).findAll();
                    RealmDetailsModel object = realm.where(RealmDetailsModel.class).equalTo("imdbID",
                            detailsModel.getImdbID()).findFirst();

                    // update if exist field
                    if (object != null) {

                        object.setTitle(detailsModel.getTitle());
                        object.setYear(detailsModel.getYear());
                        object.setImdbID(detailsModel.getImdbID());
                        object.setPoster(detailsModel.getPoster());
                        object.setType(detailsModel.getType());
                        object.setActors(detailsModel.getActors());
                        object.setAwards(detailsModel.getAwards());
                        object.setBoxOffice(detailsModel.getBoxOffice());
                        object.setCountry(detailsModel.getCountry());
                        object.setDirector(detailsModel.getDirector());
                        object.setDVD(detailsModel.getDVD());
                        object.setGenre(detailsModel.getGenre());
                        object.setImdbRating(detailsModel.getImdbRating());
                        object.setImdbVotes(detailsModel.getImdbVotes());
                        object.setLanguage(detailsModel.getLanguage());
                        object.setMetascore(detailsModel.getMetascore());
                        object.setWriter(detailsModel.getWriter());
                        object.setWebsite(detailsModel.getWebsite());
                        object.setPlot(detailsModel.getPlot());
                        object.setRuntime(detailsModel.getRuntime());
                        object.setResponse(detailsModel.getResponse());
                        object.setProduction(detailsModel.getProduction());
                        object.setRated(detailsModel.getRated());
                        // to get all ratings
                        RealmList<RealmRatingsBean> ratingsBeanRealmList = new RealmList<>();
                        for (int i = 0; i < detailsModel.getRatings().size(); i++) {
                            RealmRatingsBean realmRatingsBean = realm.createObject(RealmRatingsBean.class);
                            realmRatingsBean.setSource(detailsModel.getRatings().get(i).getSource());
                            realmRatingsBean.setValue(detailsModel.getRatings().get(i).getValue());
                            ratingsBeanRealmList.add(realmRatingsBean);
                        }
                        object.setRatings(ratingsBeanRealmList);

                        realm.insertOrUpdate(object);

                        //insert not exist field
                    } else {
                        RealmDetailsModel realmObject = realm.createObject(RealmDetailsModel.class);

                        realmObject.setTitle(detailsModel.getTitle());
                        realmObject.setYear(detailsModel.getYear());
                        realmObject.setImdbID(detailsModel.getImdbID());
                        realmObject.setPoster(detailsModel.getPoster());
                        realmObject.setType(detailsModel.getType());
                        realmObject.setActors(detailsModel.getActors());
                        realmObject.setAwards(detailsModel.getAwards());
                        realmObject.setBoxOffice(detailsModel.getBoxOffice());
                        realmObject.setCountry(detailsModel.getCountry());
                        realmObject.setDirector(detailsModel.getDirector());
                        realmObject.setDVD(detailsModel.getType());
                        realmObject.setGenre(detailsModel.getType());
                        realmObject.setImdbRating(detailsModel.getType());
                        realmObject.setImdbVotes(detailsModel.getType());
                        realmObject.setLanguage(detailsModel.getType());
                        realmObject.setMetascore(detailsModel.getType());
                        realmObject.setWriter(detailsModel.getType());
                        realmObject.setWebsite(detailsModel.getType());
                        realmObject.setPlot(detailsModel.getType());
                        realmObject.setRuntime(detailsModel.getType());
                        realmObject.setResponse(detailsModel.getType());
                        realmObject.setPoster(detailsModel.getType());
                        realmObject.setProduction(detailsModel.getType());
                        realmObject.setRated(detailsModel.getType());
                        // to get all ratings
                        RealmList<RealmRatingsBean> ratingsBeanRealmList = new RealmList<>();
                        for (int i = 0; i < detailsModel.getRatings().size(); i++) {
                            RealmRatingsBean realmRatingsBean = realm.createObject(RealmRatingsBean.class);
                            realmRatingsBean.setSource(detailsModel.getRatings().get(i).getSource());
                            realmRatingsBean.setValue(detailsModel.getRatings().get(i).getValue());
                            ratingsBeanRealmList.add(realmRatingsBean);
                        }
                        realmObject.setRatings(ratingsBeanRealmList);

                        realm.insertOrUpdate(realmObject);
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
    public Integer getGon_Unless() {
        return gon_Unless;
    }


    public void setGon_Unless(Integer gon_Unless) {
        this.gon_Unless = gon_Unless;
        notifyPropertyChanged(BR.gon_Unless);
    }
}
