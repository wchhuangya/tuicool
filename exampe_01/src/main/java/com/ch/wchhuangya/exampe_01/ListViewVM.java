package com.ch.wchhuangya.exampe_01;

import android.databinding.ObservableArrayList;

import com.kelin.mvvmlight.base.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.Observable;

/**
 * Created by wchya on 2016-12-04 14:19
 */

public class ListViewVM implements ViewModel {

    public ObservableArrayList<ListViewItemVM> items = new ObservableArrayList<>();

    public ItemViewSelector<ListViewItemVM> itemView = new BaseItemViewSelector<ListViewItemVM>() {
        @Override
        public void select(ItemView itemView, int position, ListViewItemVM item) {
            itemView.set(com.kelin.mvvmlight.BR.text, R.layout.list_view_item);
        }
    };

    public ListViewVM() {
        initData();
    }

    private void initData() {
        List<String> data = getData();
        Observable.from(data).subscribe(s -> {
            items.add(new ListViewItemVM(s));
        });
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("item: " + i);
        }
        return list;
    }
}
