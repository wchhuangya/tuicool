package com.ch.wchhuangya.dzah.tuicool.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.databinding.ArticlesFragmentBinding;
import com.ch.wchhuangya.dzah.tuicool.vm.MainVM;

/**
 * Created by wchya on 2016-12-04 21:59
 */

public class ArticlesFragment extends Fragment {
    public static String[] title_arr = {"热门", "推荐", "科技", "创业", "数码", "技术", "设计", "营销"};
    public static String[] cid_arr = {"0", "0", "101000000", "101040000", "101050000", "20", "108000000", "114000000"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArticlesFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.articles_fragment, container, false);
        binding.setMain(new MainVM(getContext()));

        binding.articleViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ArticleFragment.createFragment(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title_arr[position];
            }

            @Override
            public int getCount() {
                return title_arr.length;
            }
        });
        binding.articleViewpagertab.setViewPager(binding.articleViewpager);

        return binding.getRoot();
    }
}
