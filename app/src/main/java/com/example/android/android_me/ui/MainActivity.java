package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

/**
 * Created by el on 10/8/17.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener,
        View.OnClickListener{

    private int headImageIndex;
    private int bodyImageIndex;
    private int legImageIndex;

    //tracking 2 pane window
    private boolean mTwoPane;
    private Button nextButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = (Button) findViewById(R.id.button);

        //checking if two pane window is being displayed by the presence of id - android_me_linear_layout
        //on a single pane view - phones - this id will not be availble when the app is created.
        if (findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;
            //The "next" button should not be visible in the
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //populate the body part fragments
            BodyPartFragment headPartFragment = new BodyPartFragment();

            headPartFragment.setImageList(AndroidImageAssets.getHeads());
            headPartFragment.setmImageIndex(0);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.head_container, headPartFragment).commit();

            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setImageList(AndroidImageAssets.getBodies());
            bodyPartFragment.setmImageIndex(0);
            fragmentManager.beginTransaction().add(R.id.body_container, bodyPartFragment).commit();

            BodyPartFragment legPartFragment = new BodyPartFragment();
            legPartFragment.setImageList(AndroidImageAssets.getLegs());
            legPartFragment.setmImageIndex(0);
            fragmentManager.beginTransaction().add(R.id.leg_container, legPartFragment).commit();

            //Also get rid of the NextButton.


        }else{
            mTwoPane = false;
            nextButton.setOnClickListener(this);
        }



    }





    //if single pane - the onClick will handle the button clicks
    public void onClick(View v){
        //use Bundle to pass the index values to the new Activity
        Bundle b = new Bundle();
        b.putInt("headIndex",headImageIndex);
        b.putInt("bodyIndex",bodyImageIndex);
        b.putInt("legIndex",legImageIndex);

        // Intent to invoke the activity - AndroidMeActivity.
        Intent intent = new Intent(MainActivity.this, AndroidMeActivity.class);
        intent.putExtras(b);

        startActivity(intent);
    }

    @Override
    public void onImageClick(int position) {
        Toast.makeText(this,"Image Position = " + position, Toast.LENGTH_SHORT).show();
        //when the user clicks an image in the gridview, to identify whether it was head or body or leg
        // 0 - head, 1 - body , 2 - leg
        int partlist = position / 12;

        // heads, bodies, legs - 12 images - index - 0 to 11
        // to identify the index position
        int positionOfImage = position % 12;

        //For two pane view
        List<Integer> part = null;
        int containerId = 0;

        switch(partlist){
            case 0:
                    headImageIndex = positionOfImage;
                    if(mTwoPane) {
                        part = AndroidImageAssets.getHeads();
                        containerId = R.id.head_container;
                     }
                    break;
            case 1:
                    bodyImageIndex = positionOfImage;
                    if(mTwoPane) {
                        part = AndroidImageAssets.getBodies();
                        containerId = R.id.body_container;
                    }
                    break;
            case 2:
                    legImageIndex = positionOfImage;
                    if(mTwoPane) {
                        part = AndroidImageAssets.getLegs();
                        containerId = R.id.leg_container;
                    }
                break;
        }

        if (mTwoPane){
            BodyPartFragment partFragment = new BodyPartFragment();
            partFragment.setImageList(part);
            partFragment.setmImageIndex(positionOfImage);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(containerId,partFragment).commit();

        }

    }
}
