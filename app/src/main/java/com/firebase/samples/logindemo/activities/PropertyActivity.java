package com.firebase.samples.logindemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.auth.BaseActivity;
import com.firebase.samples.logindemo.listener.UpdateFragmentData;
import com.firebase.samples.logindemo.maplist.SFVehiclesFragment;
import com.firebase.samples.logindemo.maplist.SFVehiclesListFragment;
import com.firebase.samples.logindemo.models.Filters;
import com.firebase.samples.logindemo.models.UserModel;
import com.firebase.samples.logindemo.utils.ArmsLogs;
import com.firebase.samples.logindemo.utils.HelpUtils;
import com.firebase.samples.logindemo.utils.LocationUtils;
import com.firebase.samples.logindemo.utils.UserManagement;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.firebase.samples.logindemo.maplist.OneFragment;

//import com.firebase.samples.logindemo.adapters.ListOfProductsAdapter;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nos tra13.universalimageloader.core.ImageLoader;

/**
 * Created by apatel on 2/11/16.
 */
public class PropertyActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, UpdateFragmentData {
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserModel allUsers;
    private static final String TAG = "SFVehicles PropertyActivity";
    private ActionBarDrawerToggle toggle;
    private Button filter, search;
    private Filters filters;

    public void setLocation(double[] location) {
        this.location = location;
    }

    private double[] location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_products);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        location = LocationUtils.getLocationManager(getApplicationContext()).loc;
//        location[0] =  37.7789 ;
//        location[1] =  -122.4017;
        ArmsLogs.i(TAG, "onCreate: " + "location0: " + String.valueOf(location[0]));
//        setLocation(location);
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

    ViewPagerAdapter adapter;

    private void setupViewPager(final ViewPager viewPager) {
        ArmsLogs.i(TAG, "setupViewPager");
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
//        bundle.putSerializable("filter", filters);
        bundle.putDoubleArray("location", location);
        SFVehiclesFragment mapFragment = new SFVehiclesFragment();// .setArguments(bundle);
        SFVehiclesListFragment listFragment = new SFVehiclesListFragment();
        mapFragment.setArguments(bundle);
        listFragment.setArguments(bundle);
        adapter.addFragment(mapFragment, getResources().getString(R.string.map));
        adapter.addFragment(listFragment, getResources().getString(R.string.list));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                adapter.notifyDataSetChanged(); //this line will force all pages to be loaded fresh when changing between fragments
                ArmsLogs.i(TAG, "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                ArmsLogs.i(TAG, "onPageSelected");
                Fragment fragment = ((ViewPagerAdapter) viewPager.getAdapter()).getMapFrag(position);
                if (position == 1 && fragment != null) {
                    fragment.onResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ArmsLogs.i(TAG, "onPageScrollStateChanged");


            }
        });


        viewPager.setAdapter(adapter);
    }

    public double[] getLocation() {

        return location;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.filter_button:
                // do your code
                break;

            case R.id.filter_location_search:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                break;


            default:
                break;
        }
    }

    @Override
    public void updateLocation(double[] location, Activity activity) {
        SFVehiclesListFragment sfVehiclesListFragment = new SFVehiclesListFragment();
        sfVehiclesListFragment.updateLocation(location, PropertyActivity.this);
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private Map mapFrag = new HashMap<Integer, String>();
        private FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            mapFrag = new HashMap<Integer, String>();
        }


        public Fragment getMapFrag(int position) {
            String tag = (String) mapFrag.get(position);
            if (tag == null)
                return null;
            return fragmentManager.findFragmentByTag(tag);
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
            ArmsLogs.i(TAG, "addFragment");
        }

        public void removeFragment(Fragment fragment, String title) {
            mFragmentList.remove(fragment);

            ArmsLogs.i(TAG, "addFragment");
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment) {
                Fragment f = (Fragment) obj;
                String tag = f.getTag();
                mapFrag.put(position, tag);
            }
            return obj;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                location[0] = place.getLatLng().latitude;
                location[1] = place.getLatLng().longitude;
                ArmsLogs.i(TAG, "Place: " + place.getName());
                setLocation(location);
//                adapter.   notifyDataSetChanged();
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                ArmsLogs.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
