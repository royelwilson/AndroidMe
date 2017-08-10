package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

/**
 * Created by el on 10/8/17.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headImageIndex;
    private int bodyImageIndex;
    private int legImageIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNextClicked(View v){
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

        switch(partlist){
            case 0:
                    headImageIndex = positionOfImage;
                break;
            case 1:
                    bodyImageIndex = positionOfImage;
                break;
            case 2:
                    legImageIndex = positionOfImage;
                break;
        }


    }
}
