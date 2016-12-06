package com.ch.wchhuangya.dzah.tuicool.vm;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.adapter.ArticleAdapter;
import com.ch.wchhuangya.dzah.tuicool.model.ArticlePage;
import com.ch.wchhuangya.dzah.tuicool.util.ArticleUtils;
import com.ch.wchhuangya.dzah.tuicool.util.Constant;
import com.ch.wchhuangya.dzah.tuicool.util.CroutonUtil;
import com.ch.wchhuangya.dzah.tuicool.view.activity.MainActivity;
import com.ch.wchhuangya.dzah.tuicool.view.fragment.ArticleFragment;
import com.ch.wchhuangya.dzah.tuicool.view.fragment.ArticlesFragment;
import com.ch.wchhuangya.lib.util.TimeUtil;

import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * Created by wchya on 2016-12-04 15:36
 */

public class ArticleListViewVM extends BaseVM {

    private ArticleFragment mFragment;
    private Context mContext;
    private ArticlePage[] mArticlePages = new ArticlePage[]{new ArticlePage(), new ArticlePage(), new ArticlePage(), new ArticlePage(),
                                                            new ArticlePage(), new ArticlePage(), new ArticlePage(), new ArticlePage()};
    private int pos = 0;
    private boolean isRefresh;
    private boolean isFirst = true;
    private ArticleAdapter mAdapter;

    public ArticleListViewVM(ArticleFragment fragment, SwipeRefreshLayout srl) {
        mFragment = fragment;
        mContext = fragment.getContext();
        pos = fragment.getArguments().getInt("pos", 0);
    }

    public void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ArticleAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    public void initSwipeRefreshLayout(SwipeRefreshLayout srl) {
        srl.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        srl.setEnabled(false);
        srl.setOnRefreshListener(() -> refresh(srl));
    }

    public void refresh(SwipeRefreshLayout srl) {
        isRefresh = true;
        fetchData(srl, "", "", mArticlePages[pos].getFirstTime(), mArticlePages[pos].getFirstId());
    }

    public void loadMore(SwipeRefreshLayout srl) {
        fetchData(srl, mArticlePages[pos].getLastId(), String.valueOf(mArticlePages[pos].getPageNo() + 1), -1, "");
    }

    public void fetchData(SwipeRefreshLayout srl, String lastId, String pageNo, long firstTime, String firstId) {
        ArticleUtils.article("20", "-1", ArticlesFragment.cid_arr[pos], lastId, pageNo, firstTime, firstId,
                article -> {
                    int count = article.getArticles().size();

                    if (isFirst && mFragment.getUserVisibleHint()) { // 首次加载
                        if (count > 0) {
                            mArticlePages[pos].setFirstId(article.getArticles().get(0).getId());
                            mArticlePages[pos].setFirstTime(TimeUtil.getTimestamp(1));
                            mArticlePages[pos].setLastId(article.getArticles().get(article.getArticles().size() - 1).getId());
                            mArticlePages[pos].setPageNo(0);
                            mAdapter.firstAddData(article.getArticles());
                            isFirst = false;
                        } else
                            mAdapter.setFooterTips("没有任何数据返回");
                    } else if (isRefresh) { // 增量更新
                        if (count > 0) {
                            mArticlePages[pos].setFirstId(article.getArticles().get(0).getId());
                            mArticlePages[pos].setFirstTime(TimeUtil.getTimestamp(1));
                            mAdapter.insertData(article.getArticles(), 0, count);
                            Crouton.showText(((MainActivity) mContext), "本次更新了 " + count + " 条" + ArticlesFragment.title_arr[pos] + "新闻",
                                    CroutonUtil.getStyle(R.color.top_crouton_bg, CroutonUtil.getLongTimeShowCfg()), R.id.common_tips_fl);
                        } else
                            Crouton.showText(((MainActivity) mContext), "没有新数据",
                                    CroutonUtil.getStyle(R.color.top_crouton_bg, CroutonUtil.getLongTimeShowCfg()), R.id.common_tips_fl);
                    } else { // 加载更多
                        mArticlePages[pos].setLastId(article.getArticles().get(article.getArticles().size() - 1).getId());
                        mArticlePages[pos].setPageNo(article.getPn());
                    }

                }, throwable -> {
                    Log.e(Constant.TAG_LOG, "fetchData: 获取列表数据失败——", throwable);
                    isRefresh = false;
                    srl.setEnabled(true);
                    srl.setRefreshing(false);
                }, () -> {
                    isFirst = false;
                    isRefresh = false;
                    srl.setEnabled(true);
                    srl.setRefreshing(false);
                }
        );
    }

}
