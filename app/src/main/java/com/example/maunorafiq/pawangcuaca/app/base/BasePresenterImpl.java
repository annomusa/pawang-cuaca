package com.example.maunorafiq.pawangcuaca.app.base;

import android.content.Context;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maunorafiq on 10/28/16.
 */

public class BasePresenterImpl implements BasePresenter {
    private CompositeSubscription mCompositeSubscription;
    private Context ctx;

    @Override
    public void onCreate(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        configureSubscription();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        unSubscribeAll();
    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    private void unSubscribeAll() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
        }
    }

    private CompositeSubscription configureSubscription() {
        if(mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    protected <R> void subscribe(Observable<R> observable, Observer<R> observer){
        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
        configureSubscription().add(subscription);
    }
}
