package com.ch.wchhuangya.dzah.tuicool.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.databinding.ArticleItemBinding;
import com.ch.wchhuangya.dzah.tuicool.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchya on 2016-11-18 12:00
 */

public class ArticleAdapter extends BaseAdapter {

    private List<Article.ArticlesBean> mData = new ArrayList<>();
    private Context mContext;

    public ArticleAdapter(Context context) {
        mContext = context;
    }

    public List<Article.ArticlesBean> getData() {
        return mData;
    }

    public void setData(List<Article.ArticlesBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.article_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.setData(mData.get(i));

        return view;
    }

    class ViewHolder {

        private final ArticleItemBinding mBind;

        public ViewHolder(View view) {
            mBind = DataBindingUtil.bind(view);
        }

        public void setData(Article.ArticlesBean articlesBean) {
            mBind.setArticle(articlesBean);
        }
    }
}
