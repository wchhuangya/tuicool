package com.ch.wchhuangya.dzah.tuicool.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.adapter.ArticleAdapter;
import com.ch.wchhuangya.dzah.tuicool.customview.SwipeRefreshView;
import com.ch.wchhuangya.dzah.tuicool.databinding.CommonListviewBinding;
import com.ch.wchhuangya.dzah.tuicool.model.CommonListView;
import com.ch.wchhuangya.dzah.tuicool.util.Constant;
import com.ch.wchhuangya.dzah.tuicool.util.RetrofitUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 首页新闻列表 Fragment
 * Created by wchya on 2016-11-18 11:25
 */

public class ArticleFragment extends Fragment {

    public static final String ARTICLE_PAGE_NUMS = "30";
    public static final String ARTICLE_LANG = "-1";
    private static boolean hasNext; // 是否有下一页
    private ViewPager mPager;
    private SmartTabLayout mStl;
    private String[] title_arr = {"热门", "推荐", "科技", "创业", "数码", "技术", "设计", "营销"};
    private String[] cid_arr = {"0", "0", "101000000", "101040000", "101050000", "20", "108000000", "114000000"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment, container, false);
        mPager = (ViewPager) view.findViewById(R.id.article_viewpager);
        mStl = (SmartTabLayout) view.findViewById(R.id.article_viewpagertab);
        mPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CreateArticleFragment fragment = new CreateArticleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                bundle.putString("cid", cid_arr[position]);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title_arr[position];
            }

            @Override
            public int getCount() {
                return 8;
            }
        });
        mStl.setViewPager(mPager);
        return view;
    }

    public static class CreateArticleFragment extends Fragment {

        private CommonListView mCommonListView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.common_listview, null);
            CommonListviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.common_listview, container, false);
            ListView mListview = (ListView) binding.getRoot().findViewById(R.id.common_listview);
            SwipeRefreshView refreshLayout = (SwipeRefreshView) binding.getRoot().findViewById(R.id.common_refresh_layout);
            refreshLayout.setColorSchemeColors(getResources().getColor(R.color.toolbar_bg));
            mListview.setDividerHeight(0);
            ArticleAdapter adapter = new ArticleAdapter(getContext());
            mListview.setAdapter(adapter);

            mCommonListView = new CommonListView();
            binding.setCommonListview(mCommonListView);
            Constant.isFirstLoad = true;

            int pos = getArguments().getInt("pos", 0);
            String cid = getArguments().getString("cid", "0");

            if (pos == 1) {

            } else {
                if (Constant.isFirstLoad) {
                    mCommonListView.pbShow.set(true);
                    mCommonListView.listviewShow.set(false);
                    mCommonListView.ivShow.set(false);
                    Constant.isFirstLoad = false;
                }

                refresh(refreshLayout, adapter, cid);

                refreshLayout.setOnRefreshListener(() -> {
                    refresh(refreshLayout, adapter, cid);
                });
                refreshLayout.setOnLoadListener(() -> {
                    loadMore(refreshLayout);
                });
            }

            return binding.getRoot();
        }

        private void loadMore(SwipeRefreshView refreshLayout) {
            Observable.timer(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> { Log.d(Constant.TAG_LOG, "loadMore: 向上拉喽"); refreshLayout.setLoading(false);});
        }

        private void refresh(SwipeRefreshView refreshLayout, ArticleAdapter adapter, String cid) {
            RetrofitUtil.article(ARTICLE_PAGE_NUMS, ARTICLE_LANG, cid,
                    article -> {
//                        refreshLayout.setLoading(article.isHas_next());
                        adapter.setData(article.getArticles());
                        refreshLayout.setRefreshing(false);
                        mCommonListView.pbShow.set(false);
                        mCommonListView.listviewShow.set(true);
                    }, throwable -> {
                        Log.e(Constant.TAG_LOG, "onCreateView: ", throwable);
                        refreshLayout.setRefreshing(false);
                    }
            );
        }
    }
}
