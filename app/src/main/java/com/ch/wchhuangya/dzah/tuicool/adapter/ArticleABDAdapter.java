package com.ch.wchhuangya.dzah.tuicool.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.model.Article;
import com.ch.wchhuangya.dzah.tuicool.model.CommonListFooter;
import com.ch.wchhuangya.lib.binding.BindingViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchya on 2016-11-23 11:48
 */

public class ArticleABDAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    public static final int LOAD_MORE = 1;
    public static final int LOAD_NORMAL = 2;
    private LayoutInflater mInflater;
    private List<Article.ArticlesBean> mData = new ArrayList<>();
    private boolean loadMore;
    private CommonListFooter mCommonListFooter;

    public interface clickInterface {
        public void onItemClickListener(View view, String data);
    }

    public boolean canLoadMore() {
        return loadMore;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }

    public void removeFooter() {
        if (mData.size() > 0) {
            mData.remove(mData.size() - 1);
        }
    }

    public void setData(List<Article.ArticlesBean> data, int oldNums, int newNums) {
        mData.addAll(oldNums, data);
        //notifyDataSetChanged();
        notifyItemRangeInserted(oldNums, newNums);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size() > 0 && position == mData.size() - 1 && canLoadMore())
            return LOAD_MORE;
        else
            return LOAD_NORMAL;
    }

    public ArticleABDAdapter(Context context, CommonListFooter commonListFooter) {
        mCommonListFooter = commonListFooter;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOAD_NORMAL)
            return new BindingViewHolder(DataBindingUtil.inflate(mInflater, R.layout.article_item, parent, false), null);
        else
            return new BindingViewHolder(DataBindingUtil.inflate(mInflater, R.layout.common_list_footer, parent, false), null);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Article.ArticlesBean article = mData.get(position);
        /*if (canLoadMore() && mData.size() > 0 && position == mData.size() -1)
            holder.getBinding().setVariable(BR.tips, mCommonListFooter);
        else {
            holder.getBinding().setVariable(BR.article, article);
            holder.getBinding().executePendingBindings();
        }*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
