package com.ch.wchhuangya.dzah.tuicool.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.databinding.ArticleFragmentBinding;
import com.ch.wchhuangya.dzah.tuicool.vm.ArticleListViewVM;

/**
 * Created by wchya on 2016-12-04 22:44
 */

public class ArticleFragment extends Fragment {

    public static ArticleFragment createFragment(int position) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        articleFragment.setArguments(bundle);
        return articleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArticleFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.article_fragment, container, false);
        ArticleListViewVM alvVM = new ArticleListViewVM(this);
        binding.setRv(alvVM);
        binding.articleFragmentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        alvVM.initSrl(binding.articleFragmentSrl);
        binding.articleFragmentSrl.setOnRefreshListener(() -> alvVM.refresh(binding.articleFragmentSrl));
        return binding.getRoot();
    }
}
