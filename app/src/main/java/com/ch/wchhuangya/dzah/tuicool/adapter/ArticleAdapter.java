package com.ch.wchhuangya.dzah.tuicool.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.tuicool.BR;
import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.databinding.ArticleItemBinding;
import com.ch.wchhuangya.dzah.tuicool.databinding.CommonListFooterBinding;
import com.ch.wchhuangya.dzah.tuicool.databinding.CommonListTipsBinding;
import com.ch.wchhuangya.dzah.tuicool.model.Article;
import com.ch.wchhuangya.dzah.tuicool.model.ArticleObservable;
import com.ch.wchhuangya.dzah.tuicool.model.CommonListFooter;
import com.ch.wchhuangya.dzah.tuicool.model.CommonListTips;
import com.ch.wchhuangya.lib.binding.BindingViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchya on 2016-12-05 16:01
 */

public class ArticleAdapter extends RecyclerView.Adapter<BindingViewHolder> {


    public static final int VIEW_TYPE_TIPS = 0; // 用于异常(如：网络连接失败)并且列表没有数据时显示
    public static final int VIEW_TYPE_BODY = 1; // 内容，显示列表
    public static final int VIEW_HOLDER_FOOTER = 2; // 顶部，用于显示加载更多
    private boolean canLoadMore;
    private List<Article.ArticlesBean> mData = new ArrayList<>();
    private CommonListTips mTips;
    private CommonListFooter mFooter;

    public List<Article.ArticlesBean> getData() {
        return mData;
    }

    public void setData(List<Article.ArticlesBean> data) {
        mData = data;
    }

    public void firstAddData(List<Article.ArticlesBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void insertData(List<Article.ArticlesBean> data, int start, int count) {
        mData.addAll(data);
        notifyItemRangeInserted(start, count);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size() == 0)
            return VIEW_TYPE_TIPS;
        else if (canLoadMore && position == mData.size() - 1)
            return VIEW_HOLDER_FOOTER;
        else
            return VIEW_TYPE_BODY;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TIPS) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.common_list_tips, parent, false);
            CommonListTips tips = new CommonListTips();
            binding.setVariable(BR.tips, tips);
            return new BindingViewHolder(binding, tips);
        } else if (viewType == VIEW_TYPE_BODY) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.article_item, parent, false);
            ArticleObservable article = new ArticleObservable();
            binding.setVariable(BR.article, article);
            return new BindingViewHolder(binding, article);
        } else {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.common_list_footer, parent, false);
            CommonListFooter footer = new CommonListFooter();
            binding.setVariable(BR.footer, footer);
            return new BindingViewHolder(binding, footer);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();

        if (binding instanceof CommonListTipsBinding) {
            mTips = (CommonListTips) binding.getRoot().getTag();
            mTips.tips.set("暂无数据");
        } else if (binding instanceof ArticleItemBinding) {
            Article.ArticlesBean articlesBean = mData.get(position);
            ArticleObservable article = (ArticleObservable) binding.getRoot().getTag();
            article.img.set(articlesBean.getImg());
            article.title.set(articlesBean.getTitle());
            article.feedTitle.set(articlesBean.getFeed_title());
            article.time.set(articlesBean.getTime());
        } else if (binding instanceof CommonListFooterBinding) {
            mFooter = (CommonListFooter) binding.getRoot().getTag();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() > 0 ? (canLoadMore ? mData.size() + 1 : mData.size()) : 1;
    }

    /** 设置提示的语句 */
    public void setTips(String tips) {
        mTips.tips.set(tips);
    }

    public boolean isCanLoadMore() {
        return canLoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    /**  */
    public void setFooterTips(String footerTips) {
        mFooter.tips.set(footerTips);
    }

    public void changeFooter(boolean tipsShow) {
        mFooter.tipsShow.set(tipsShow);
        mFooter.metaballShow.set(!tipsShow);
    }
}
