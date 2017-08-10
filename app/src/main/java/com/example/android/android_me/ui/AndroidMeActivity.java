/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
            Log.d("TEST" , "Saved InstanceState is null");

            //Retrieve the index values from Bundle.

            Intent i = getIntent();
            Bundle b = i.getExtras();
            int headIndex = b.getInt("headIndex");
            int bodyIndex = b.getInt("bodyIndex");
            int legIndex =  b.getInt("legIndex");

            BodyPartFragment headPartFragment = new BodyPartFragment();
            headPartFragment.setImageList(AndroidImageAssets.getHeads());
            //set the image index for head.
            headPartFragment.setmImageIndex(headIndex);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.head_container, headPartFragment).commit();

            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setImageList(AndroidImageAssets.getBodies());
            bodyPartFragment.setmImageIndex(bodyIndex);
            fragmentManager.beginTransaction().add(R.id.body_container, bodyPartFragment).commit();

            BodyPartFragment legPartFragment = new BodyPartFragment();
            legPartFragment.setImageList(AndroidImageAssets.getLegs());
            legPartFragment.setmImageIndex(legIndex);
            fragmentManager.beginTransaction().add(R.id.leg_container, legPartFragment).commit();
        }

    }
}
