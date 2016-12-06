package com.ch.wchhuangya.dzah.tuicool.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArticleFragmentBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.article_fragment, container, false);

        if (getArguments().getInt("pos") == 1) {
        } else {
            ArticleListViewVM alvVM;

            alvVM = new ArticleListViewVM(this, mBinding.includeCommonRv.commonRefreshLayout);
            mBinding.setList(alvVM);

            alvVM.initRecyclerView(mBinding.includeCommonRv.commonRv);

            alvVM.initSwipeRefreshLayout(mBinding.includeCommonRv.commonRefreshLayout);

            alvVM.fetchData(mBinding.includeCommonRv.commonRefreshLayout, "", "", -1, "");
        }

        return mBinding.getRoot();
    }
}
