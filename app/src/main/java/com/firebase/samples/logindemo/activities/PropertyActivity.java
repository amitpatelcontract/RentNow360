package com.firebase.samples.logindemo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.samples.logindemo.R;

import com.firebase.samples.logindemo.auth.BaseActivity;
//import com.firebase.samples.logindemo.maplist.OneFragment;
import com.firebase.samples.logindemo.maplist.SFVehiclesFragment;
import com.firebase.samples.logindemo.maplist.SFVehiclesListFragment;
import com.firebase.samples.logindemo.models.UserModel;
import com.firebase.samples.logindemo.utils.HelpUtils;
import com.firebase.samples.logindemo.utils.UserManagement;


import java.util.ArrayList;
import java.util.List;

//import com.firebase.samples.logindemo.adapters.ListOfProductsAdapter;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nos tra13.universalimageloader.core.ImageLoader;

/**
 * Created by apatel on 2/11/16.
 */
public class PropertyActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserModel allUsers;
    private static final String TAG = "PropertyActivity";
    private ActionBarDrawerToggle toggle;
    private Button filter, search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_products);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        filter = (Button) findViewById(R.id.filter_button);
        search = (Button) findViewById(R.id.filter_location_search);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView tvName = (TextView) headerView.findViewById(R.id.nav_header_main_title);
        ImageView nav_header_main_user_icon = (ImageView) headerView.findViewById(R.id.nav_header_main_user_icon);

        if (HelpUtils.getCurrentUser().getPhotoUrl() != null)
            Glide.with(this)
                    .load(HelpUtils.getCurrentUser().getPhotoUrl().toString())
                    .into(nav_header_main_user_icon);


        tvName.setText(HelpUtils.getCurrentUser().getDisplayName());
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HelpUtils.openProfilePage(PropertyActivity.this);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SFVehiclesFragment(), getResources().getString(R.string.map));
        adapter.addFragment(new SFVehiclesListFragment(), getResources().getString(R.string.list));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.filter_button:
                // do your code
                break;

            case R.id.filter_location_search:
                // do your code
                break;


            default:
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.upload_item) {
            // Handle the camera action
            HelpUtils.openUploadItemActivity(PropertyActivity.this);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.sign_out_drawer) {
            UserManagement.signOut(mAuth);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public interface ClickListener {
//        void onClick(View view, int position);
//
//        void onLongClick(View view, int position);
//    }

}

//    private static final String TRANSLATION_Y = "translationY";
//    private ImageButton fab;
//
//    private boolean expanded = false;
//
//    private View fabAction1;
//    private View fabAction2;
//    private View fabAction3;
//
//    private float offset1;
//    private float offset2;
//    private float offset3;

//        Product poduct;
////     public ListOfProductsAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Query ref) {
//        final ListOfProductsAdapter adapter = new ListOfProductsAdapter(poduct, );
//        recyclerView.setAdapter(adapter);
//
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return adapter.isHeader(position) ? manager.getSpanCount() : 1;
//            }
//        });