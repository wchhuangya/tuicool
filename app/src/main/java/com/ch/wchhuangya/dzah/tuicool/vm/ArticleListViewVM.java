package com.ch.wchhuangya.dzah.tuicool.vm;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.model.ArticlePage;
import com.ch.wchhuangya.dzah.tuicool.util.Constant;
import com.ch.wchhuangya.dzah.tuicool.util.RetrofitUtil;
import com.ch.wchhuangya.dzah.tuicool.view.fragment.ArticleFragment;
import com.ch.wchhuangya.dzah.tuicool.view.fragment.ArticlesFragment;
import com.kelin.mvvmlight.base.ViewModel;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.Observable;

/**
 * Created by wchya on 2016-12-04 15:36
 */

public class ArticleListViewVM implements ViewModel {
    public ObservableBoolean pbShow = new ObservableBoolean(true);
    public ObservableBoolean listviewShow = new ObservableBoolean(false);
    public ObservableBoolean ivShow = new ObservableBoolean(false);

    public ObservableArrayList<ArticleListViewItemVM> items = new ObservableArrayList<>();
    public ItemViewSelector<ArticleListViewItemVM> itemView = new BaseItemViewSelector<ArticleListViewItemVM>() {
        @Override
        public void select(ItemView itemView, int position, ArticleListViewItemVM item) {
            itemView.set(com.kelin.mvvmlight.BR.item, R.layout.article_item);
        }
    };
    private Context mContext;
    private ArticlePage[] mArticlePages = new ArticlePage[]{new ArticlePage(), new ArticlePage(), new ArticlePage(), new ArticlePage(),
                                                            new ArticlePage(), new ArticlePage(), new ArticlePage(), new ArticlePage()};
    private int pos = 0;

    public ArticleListViewVM(ArticleFragment fragment) {
        mContext = fragment.getContext();
        pos = fragment.getArguments().getInt("pos", 0);
        fetchData(null);
    }

    public void fetchData(SwipeRefreshLayout srl) {
        RetrofitUtil.article("20", "-1", ArticlesFragment.cid_arr[pos], mArticlePages[pos].getLastId(), mArticlePages[pos].getPageNo() + "",
                mArticlePages[pos].getFirstTime(), mArticlePages[pos].getFirstId(),
        article -> {
            Observable.from(article.getArticles()).subscribe(articlesBean -> {
                items.add(new ArticleListViewItemVM(articlesBean));
            });
            if (srl != null)
                srl.setRefreshing(false);
        }, throwable -> {
            Log.e(Constant.TAG_LOG, "fetchData: 获取列表数据失败——", throwable);
        });
    }

    public void refresh(SwipeRefreshLayout srl) {
        items.clear();
        fetchData(srl);
    }
}
