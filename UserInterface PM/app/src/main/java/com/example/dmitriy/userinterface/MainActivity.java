package com.example.dmitriy.userinterface;

import android.content.Context;
import android.print.PrintDocumentAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost.TabContentFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, MainActivity.TabInfo>();
    private PagerAdapter mPagerAdapter;

    private class TabInfo {
        private String tag;
        private Class<?> clss;
        private Bundle args;
        private Fragment fragment;
        TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }}

        class TabFactory implements TabContentFactory {

            private final Context mContext;

            /**
             * @param context
             */
            public TabFactory(Context context) {
                mContext = context;
            }

            /** (non-Javadoc)
             * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
             */
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
        // Intialise ViewPager
        this.initialiseViewPager();
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }


    private void initialiseViewPager() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, AlarmsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, CamerasFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, DetectorsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, EventsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, MoreFragment.class.getName()));
        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        this.mViewPager = (ViewPager)super.findViewById(R.id.pager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.getTabWidget().setDividerDrawable(null);
        TabInfo tabInfo = null;
        View tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.alarms_tab_layout, null);
        MainActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab1").setIndicator(tabview), ( tabInfo = new TabInfo("Tab1", AlarmsFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.cameras_tab_layout, null);
        MainActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab2").setIndicator(tabview), (tabInfo = new TabInfo("Tab2", CamerasFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.detectors_tab_layout, null);
        MainActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab3").setIndicator(tabview), (tabInfo = new TabInfo("Tab3", DetectorsFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.events_tab_layout, null);
        MainActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab4").setIndicator(tabview), (tabInfo = new TabInfo("Tab4", EventsFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.more_tab_layout, null);
        MainActivity.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab5").setIndicator(tabview), (tabInfo = new TabInfo("Tab5", MoreFragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        mTabHost.setOnTabChangedListener(this);

    }


    private static void AddTab(MainActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    public void onTabChanged(String tag) {
        //TabInfo newTab = this.mapTabInfo.get(tag);
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // TODO Auto-generated method stub

    }


    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        this.mTabHost.setCurrentTab(position);
    }


    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.menu_main, menu);
        ///menu.add("Setting");
       /// menu.add("Services");
        ///menu.add("Log out");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
class PagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
            return this.fragments.size();
        }
    }
