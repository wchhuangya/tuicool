//package com.ch.wchhuangya.dzah.tuicool.view.fragment;
//
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.ch.wchhuangya.dzah.tuicool.R;
//import com.ch.wchhuangya.dzah.tuicool.activity.Main3Activity;
//import com.ch.wchhuangya.dzah.tuicool.adapter.ArticleAdapter;
//import com.ch.wchhuangya.dzah.tuicool.adapter.EndlessRecyclerViewScrollListener;
//import com.ch.wchhuangya.dzah.tuicool.databinding.CommonRecyclerviewBinding;
//import com.ch.wchhuangya.dzah.tuicool.model.Article;
//import com.ch.wchhuangya.dzah.tuicool.model.CommonList;
//import com.ch.wchhuangya.dzah.tuicool.model.CommonListLoadMore;
//import com.ch.wchhuangya.dzah.tuicool.util.Constant;
//import com.ch.wchhuangya.dzah.tuicool.util.CroutonUtil;
//import com.ch.wchhuangya.dzah.tuicool.util.RetrofitUtil;
//import com.ch.wchhuangya.dzah.tuicool.util.TimeUtil;
//import com.ogaclejapan.smarttablayout.SmartTabLayout;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import de.keyboardsurfer.android.widget.crouton.Crouton;
//import rx.Observable;
//
///**
// * 首页新闻列表 Fragment
// * Created by wchya on 2016-11-18 11:25
// */
//
//public class ArticlesFragment extends Fragment {
//
//    public static final String ARTICLE_PAGE_NUMS = "30";
//    public static final String ARTICLE_LANG = "-1";
//    private ViewPager mPager;
//    private SmartTabLayout mStl;
//    private String[] title_arr = {"热门", "推荐", "科技", "创业", "数码", "技术", "设计", "营销"};
//    private String[] cid_arr = {"0", "0", "101000000", "101040000", "101050000", "20", "108000000", "114000000"};
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.articles_fragment, container, false);
//        mPager = (ViewPager) view.findViewById(R.id.article_viewpager);
//        mStl = (SmartTabLayout) view.findViewById(R.id.article_viewpagertab);
//        mPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                CreateArticleFragment fragment = new CreateArticleFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("pos", position);
//                bundle.putString("cid", cid_arr[position]);
//                fragment.setArguments(bundle);
//                return fragment;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return title_arr[position];
//            }
//
//            @Override
//            public int getCount() {
//                return title_arr.length;
//            }
//        });
//        mStl.setViewPager(mPager);
//        mPager.setOffscreenPageLimit(0);
//        return view;
//    }
//
//    public static class CreateArticleFragment extends Fragment {
//
//        private CommonList mCommonList;
//        private CommonListLoadMore mCommonListLoadMore;
//
//        @Nullable
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            CommonRecyclerviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.common_recyclerview, container, false);
//            mCommonList = new CommonList();
//            binding.setCommonListview(mCommonList);
//
//            RecyclerView recyclerview = (RecyclerView) binding.getRoot().findViewById(R.id.common_recyclerview);
//            SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) binding.getRoot().findViewById(R.id.common_refresh_layout);
//            refreshLayout.setColorSchemeColors(getResources().getColor(R.color.toolbar_bg));
//
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//            recyclerview.setLayoutManager(layoutManager);
//            mCommonListLoadMore = new CommonListLoadMore();
//            ArticleAdapter adapter = new ArticleAdapter(getContext(), mCommonListLoadMore);
//            recyclerview.setAdapter(adapter);
//            refreshLayout.setEnabled(false);
//            int pos = getArguments().getInt("pos", 0);
//            String cid = getArguments().getString("cid", "0");
//
//            if (pos == 1) { // 如果是推荐模块
//
//            } else {
//                if (Constant.isFirstLoad) {
//                    mCommonList.pbShow.set(true);
//                    mCommonList.listviewShow.set(false);
//                    mCommonList.ivShow.set(false);
//                    Constant.isFirstLoad = false;
//                }
//
//                refresh(refreshLayout, adapter, cid, recyclerview, false, true, pos);
//
//                refreshLayout.setOnRefreshListener(() -> {
//                    refresh(refreshLayout, adapter, cid, recyclerview, true, false, pos);
//                });
//
//                recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
//                    @Override
//                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                        loadMore(refreshLayout, adapter, cid, view, pos);
//                    }
//                });
//            }
//
//            return binding.getRoot();
//        }
//
//        private void loadMore(SwipeRefreshLayout refreshLayout, ArticleAdapter adapter, String cid, RecyclerView recyclerView, int pos) {
//            Observable.timer(100, TimeUnit.MILLISECONDS).subscribe(aLong -> {
//                mCommonListLoadMore.metaballShow.set(true);
//                mCommonListLoadMore.tipsShow.set(false);
//            });
//            Main3Activity.articlePages[pos].setPageNo(Main3Activity.articlePages[pos].getPageNo() + 1);
//            RetrofitUtil.article(ARTICLE_PAGE_NUMS, ARTICLE_LANG, cid, Main3Activity.articlePages[pos].getLastId(), Main3Activity.articlePages[pos].getPageNo() + "", -1, "", article -> {
//                loadData(refreshLayout, adapter, article, recyclerView, false, false, pos);
//            }, throwable -> {
//                Log.e(Constant.TAG_LOG, "loadMore: 加载更多操作失败——", throwable);
//            });
//        }
//
//        private void refresh(SwipeRefreshLayout refreshLayout, ArticleAdapter adapter, String cid, RecyclerView recyclerView,
//                             boolean refresh, boolean first, int pos) {
//            RetrofitUtil.article(ARTICLE_PAGE_NUMS, ARTICLE_LANG, cid, "", "", Main3Activity.articlePages[pos].getFirstTime(), Main3Activity.articlePages[pos].getFirstId(),
//                    article -> {
//                        loadData(refreshLayout, adapter, article, recyclerView, refresh, first, pos);
//                    }, throwable -> {
//                        Log.e(Constant.TAG_LOG, "onCreateView: ", throwable);
//                    }
//            );
//        }
//
//        private void loadData(SwipeRefreshLayout refreshLayout, ArticleAdapter adapter, Article article, RecyclerView recyclerView,
//                              boolean refresh, boolean first, int pos) {
//            refreshLayout.setEnabled(true);
//            adapter.setLoadMore(article.isHas_next());
//
//            List<Article.ArticlesBean> articles = article.getArticles();
//            int oldNums = adapter.getItemCount();
//
//            Log.d(Constant.TAG_LOG, "loadData: ---" + Main3Activity.articlePages.length);
//            if (first) {
//                if (articles.size() > 0) {
//                    Main3Activity.articlePages[pos].setFirstTime(TimeUtil.getTimestamp(1));
//                    Main3Activity.articlePages[pos].setFirstId(articles.get(0).getId());
//                }
//            }
//            if (refresh) { // 下拉刷新
//                if (articles.size() > 0) { // 如果有新数据，添加到列表头部
//                    adapter.setData(articles, 0, articles.size());
//                    recyclerView.scrollToPosition(0);
//                    Main3Activity.articlePages[pos].setFirstTime(TimeUtil.getTimestamp(1));
//                    Main3Activity.articlePages[pos].setFirstId(articles.get(0).getId());
//                    Crouton.showText(((Main3Activity)getContext()), "更新完成，新增 " + articles.size() + " 条数据",
//                            CroutonUtil.getStyle(R.color.top_crouton_bg, CroutonUtil.getLongTimeShowCfg()), R.id.common_tips_fl);
//                } else { // 如果没有新数据，提示
//                    Crouton.showText(((Main3Activity)getContext()), "没有新数据",
//                            CroutonUtil.getStyle(R.color.top_crouton_bg, CroutonUtil.getLongTimeShowCfg()), R.id.common_tips_fl);
//                }
//            } else { // 非刷新，即上位加载更多数据
//                if (article.isHas_next()) {
//                    adapter.removeFooter();
//                    if (articles.size() > 0) {
//                        Main3Activity.articlePages[pos].setLastId(articles.get(articles.size() - 1).getId());
//                        articles.add(new Article.ArticlesBean());
//                    }
//                } else {
//                    adapter.removeFooter();
//                }
//                adapter.setData(articles, oldNums > 0 ? oldNums - 1 : oldNums, articles.size() - oldNums);
//                recyclerView.scrollToPosition(oldNums > 0 ? oldNums - 1 : oldNums);
//            }
//
//            refreshLayout.setRefreshing(false);
//            mCommonList.pbShow.set(false);
//            mCommonList.listviewShow.set(true);
//        }
//    }
//}
