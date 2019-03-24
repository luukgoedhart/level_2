package com.example.geoguessswipe2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.telephony.mbms.MbmsErrors;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    List<GeoObject> mGeoObjects;
    private GestureDetector mGestureDetector;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private static final int DIRECTION_RIGHT = 8;
    public boolean answerGiven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGeoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {
                       mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i], GeoObject.PRE_DEFINED_GEO_OBJECT_ANSWERS[i]));
        }

        final RecyclerView mGeoRecyclerView = findViewById(R.id.geoRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        mGeoRecyclerView.setHasFixedSize(true);
        final GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);
        mGeoRecyclerView.addOnItemTouchListener(this);
        mGeoRecyclerView.getLayoutManager().setMeasurementCacheEnabled(false);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }


        });
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =

                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    // The first integer parameter refers to the dragging directions. We ignore these here.

                    // The second integer parameter refers to the swiping directions.


                    @Override

                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                        return false;

                    }


                    //Called when a user swipes left or right on a ViewHolder

                    @Override

                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {


                        //Get the index corresponding to the selected position

                        int position = (viewHolder.getAdapterPosition()); //Deze regel is niet goed




                        String direction =  Integer.toString(swipeDir);
                        if(swipeDir == DIRECTION_RIGHT){
                            direction = "right";
                            answerGiven = false;
                        }else{
                            direction = "left";
                            answerGiven = true;
                        }
                        //Left swipe is always correct
                        //Right swipe is always wrong
                        showToast(direction);
                        checkAnswer(position, answerGiven);


                    }

                };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView);


    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        int mAdapterPosition = rv.getChildAdapterPosition(child);
        if (child != null && mGestureDetector.onTouchEvent(e)) {
            Toast.makeText(this, mGeoObjects.get(mAdapterPosition).getmGeoName(), Toast.LENGTH_SHORT).show();

        }
        return false;
    }


    public void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void checkAnswer(int position, boolean answerGiven){
        String result;
        if(GeoObject.PRE_DEFINED_GEO_OBJECT_ANSWERS[position] == answerGiven){
            showToast("YOU ARE SO GOOD!"); //correct
        }else{
            showToast("YOU ARE NOT SO GOOD :(");
        }

    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }


    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }


    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
