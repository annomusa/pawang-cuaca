package com.icehousecorp.maunorafiq.domain

import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

/**
 * Created by maunorafiq on 11/28/16.
 */

abstract class UseCase {

    private var subscription = Subscriptions.empty()

    protected abstract fun buildUseCaseObservable(): Observable<*>

    fun execute(useCaseSubscriber: Subscriber<Any>) {
        subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(useCaseSubscriber)
    }

    fun unsubscribe() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}
