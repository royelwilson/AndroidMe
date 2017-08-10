package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 10/8/17.
 */

public class BodyPartFragment extends Fragment {
    private List<Integer> mImageIdList;
    private int mImageIndex;

    private static final String IMAGE_ID_LIST = "image_ids_list";
    private static final String IMAGE_ID_INDEX = "image_id_index";


    //mandatory empty constructor for instantiating the fragment
    public BodyPartFragment() {
    }

    //Inflate the fragment's layout -fragment_body_part.xml and set the image view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //when the phone is rotated, the previous state of the screen was saved
        if (savedInstanceState != null) {
            mImageIdList = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mImageIndex = savedInstanceState.getInt(IMAGE_ID_INDEX);
        }


        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_part, container, false);
        //get the reference of the ImageView in the layout
        final ImageView imageView = (ImageView) view.findViewById(R.id.body_part_image_view);
        //set the image inside the ImageView.For the time being we want to show the first image in the List<Integer> heads.
        //imageView.setImageResource(AndroidImageAssets.getHeads().get(0));
        imageView.setImageResource(mImageIdList.get(mImageIndex));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageIndex < mImageIdList.size() - 1){
                    mImageIndex ++;
                }
                imageView.setImageResource(mImageIdList.get(mImageIndex));
            }
        });

        return view;
    }

    //setter methods
    public void setImageList(List<Integer> imageList) {
        mImageIdList = imageList;
    }

    public void setmImageIndex(int imageIndex) {
        mImageIndex = imageIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIdList);
        currentState.putInt(IMAGE_ID_INDEX, mImageIndex);

    }
}
