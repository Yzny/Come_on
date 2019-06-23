package com.example.myone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToob;
    private ViewPager mVp;
    private TabLayout mTab;
    private List<Fragment> mList;
    private List<String> mTitle;
    private Vp_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToob = findViewById(R.id.toob);
        mToob.setTitle("One");
        mToob.setNavigationIcon(R.mipmap.aa);
        setSupportActionBar(mToob);
        mToob.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mVp,"高低也是点击了奥",Snackbar.LENGTH_SHORT).show();
            }
        });

        mVp = findViewById(R.id.vp);
        mList = new ArrayList<>();
        final FragmentA fragmentA = new FragmentA();
        mList.add(fragmentA);
        mTitle = new ArrayList<>();
        mTitle.add("首页子");
        adapter = new Vp_Adapter(getSupportFragmentManager(), mList, mTitle);
        mVp.setAdapter(adapter);
        mTab = findViewById(R.id.tab);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,11,111,"我的收藏");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 11:
                final Intent intent = new Intent(MainActivity.this, ShouCang.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    public class Vp_Adapter extends FragmentStatePagerAdapter{
        private List<Fragment>mList;
        private List<String>mTitle;

        public Vp_Adapter(FragmentManager fm, List<Fragment> mList, List<String> mTitle) {
            super(fm);
            this.mList = mList;
            this.mTitle = mTitle;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
}
