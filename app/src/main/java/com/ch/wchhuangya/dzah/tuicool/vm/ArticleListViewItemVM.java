package com.ch.wchhuangya.dzah.tuicool.vm;

import android.databinding.ObservableField;

import com.ch.wchhuangya.dzah.tuicool.model.Article;
import com.kelin.mvvmlight.base.ViewModel;

/**
 * Created by wchya on 2016-12-04 15:29
 */

public class ArticleListViewItemVM implements ViewModel {
    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> feedTitle = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();

    public ArticleListViewItemVM(Article.ArticlesBean article) {
        img.set(article.getImg());
        title.set(article.getTitle());
        feedTitle.set(article.getFeed_title());
        time.set(article.getTime());
    }
}
