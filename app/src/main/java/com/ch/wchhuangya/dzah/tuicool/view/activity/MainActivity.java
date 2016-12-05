package com.ch.wchhuangya.dzah.tuicool.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.ch.wchhuangya.dzah.tuicool.R;
import com.ch.wchhuangya.dzah.tuicool.databinding.MainBinding;
import com.ch.wchhuangya.dzah.tuicool.view.fragment.ArticlesFragment;
import com.ch.wchhuangya.dzah.tuicool.vm.MainVM;

/**
 * Created by wchya on 2016-12-04 21:08
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MainBinding mBinding;
    private MainVM mMainVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main);
        mMainVM = new MainVM(this);
        mBinding.setMain(mMainVM);

        initToolbar();
        getSupportFragmentManager().beginTransaction().add(R.id.main_framelayout, new ArticlesFragment()).commit();
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.mainContent.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.mainContent.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        mMainVM.setBarTitle(getResources().getString(R.string.home));
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_chinese) {
            return true;
        } else if (id == R.id.action_english) {
            return true;
        } else if (id == R.id.action_mixture) {
            return true;
        } else if (id == R.id.action_recommend_settings) {
            return true;
        } else if (id == R.id.action_custom_channel) {
            return true;
        } else if (id == R.id.action_week_miss) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_site) {

        } else if (id == R.id.nav_topic) {

        } else if (id == R.id.nav_weekly) {

        } else if (id == R.id.nav_mode) {

        } else if (id == R.id.nav_offline_download) {

        } else if (id == R.id.nav_settings) {

        }

        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
