package com.ch.wchhuangya.lib.vm;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by wchya on 2016-12-05 13:25
 */

public abstract class ViewModel {
    public List<Subscriber> mSubscriberList = new ArrayList<>();

    public void unsubscribe() {
        for (Subscriber subscriber : mSubscriberList) {
            if (subscriber != null && subscriber.isUnsubscribed())
                subscriber.unsubscribe();
        }
    }
}
