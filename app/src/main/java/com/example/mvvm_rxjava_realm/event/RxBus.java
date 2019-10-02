package com.example.mvvm_rxjava_realm.event;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {


    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object object) {
        bus.onNext(object);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

}
