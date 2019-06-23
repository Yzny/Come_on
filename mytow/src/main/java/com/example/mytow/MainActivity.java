package com.example.mytow;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;
    private List<Fragment> list;
    private List<String> mTitle;
    private Vp_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVp = findViewById(R.id.vp);
        list = new ArrayList<>();
        final FragmentA fragmentA = new FragmentA();
        final FragmentB fragmentB = new FragmentB();
        list.add(fragmentA);
        list.add(fragmentB);
        mTitle = new ArrayList<>();
            mTitle.add("首页");
            mTitle.add("收藏");
        adapter = new Vp_adapter(getSupportFragmentManager(), list, mTitle);
        mVp.setAdapter(adapter);
        mTab = findViewById(R.id.tab);
        mTab.setupWithViewPager(mVp);
    }
    class Vp_adapter extends FragmentStatePagerAdapter{
        private List<Fragment>list;
        private List<String>mTitle;

        public Vp_adapter(FragmentManager fm, List<Fragment> list, List<String> mTitle) {
            super(fm);
            this.list = list;
            this.mTitle = mTitle;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
}
