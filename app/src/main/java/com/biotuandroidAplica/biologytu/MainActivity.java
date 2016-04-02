package com.biotuandroidAplica.biologytu;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.biotuandroidAplica.biologytu.adapter.NavDrawerAdapter;
import com.biotuandroidAplica.biologytu.model.NavDrawerModel;
import com.biotuandroidAplica.biologytu.utils.AppUtils;
import com.biotuandroidAplica.biologytu.utils.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @InjectView(R.id.floating_button)
    FloatingActionButton floatingActionButton;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ActionBarDrawerToggle drawerToggle;
    private ArrayList<NavDrawerModel> drawerList = new ArrayList<>();
    NavDrawerAdapter drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        navigationDrawerJob();

        navDrawerAdapter();
        navDrawerItemClickListener();

        floatingActionButton.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * The downward first four methods are used for NavigationDrawer
     * **/

    // Setup NavigationDrawer
    private void navigationDrawerJob(){

        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void navDrawerAdapter(){

        drawerAdapter = new NavDrawerAdapter(drawerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(drawerAdapter);

        setNavDrawerDataList();
    }

    private void setNavDrawerDataList(){
        NavDrawerModel model = new NavDrawerModel(R.mipmap.ic_launcher , "Microbiology");
        NavDrawerModel model2 = new NavDrawerModel(R.mipmap.ic_launcher , "Zoology");
        NavDrawerModel model3 = new NavDrawerModel(R.mipmap.ic_launcher , "Physics");
        NavDrawerModel model4 = new NavDrawerModel(R.mipmap.ic_launcher , "Chemical Biology");
        NavDrawerModel model5 = new NavDrawerModel(R.mipmap.ic_launcher , "Phycology");
        NavDrawerModel model6 = new NavDrawerModel(R.mipmap.ic_launcher , "Phycholigy");
        NavDrawerModel model7 = new NavDrawerModel(R.mipmap.ic_launcher , "Titloligy");
        drawerList.add(model);
        drawerList.add(model2);
        drawerList.add(model3);
        drawerList.add(model4);
        drawerList.add(model5);
        drawerList.add(model6);
        drawerList.add(model7);
        drawerAdapter.notifyDataSetChanged();
    }

    private void navDrawerItemClickListener() {

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getApplicationContext(), position + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }



    @Override
    public void onClick(View view) {

        AppUtils.showSnackBar(view , "Here's a Snackbar" , this);

    }



    /**
     * RecyclerView OnItemListener
     * http://www.androidhive.info/2016/01/android-working-with-recycler-view/
     *
     * **/
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
