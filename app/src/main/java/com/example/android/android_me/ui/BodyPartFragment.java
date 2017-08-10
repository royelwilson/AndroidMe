package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.List;

/**
 * Created by el on 10/8/17.
 */

public class BodyPartFragment extends Fragment {
    private List<Integer> mImageIdList;
    private int mImageIndex;


    //mandatory empty constructor for instantiating the fragment
    public BodyPartFragment() {
    }

    //Inflate the fragment's layout -fragment_body_part.xml and set the image view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_part, container, false);
        //get the reference of the ImageView in the layout
        final ImageView imageView = (ImageView) view.findViewById(R.id.body_part_image_view);
        //set the image inside the ImageView.For the time being we want to show the first image in the List<Integer> heads.
        //imageView.setImageResource(AndroidImageAssets.getHeads().get(0));
        imageView.setImageResource(mImageIdList.get(mImageIndex));



        return view;
    }

    //setter methods
    public void setImageList(List<Integer> imageList){
        mImageIdList = imageList;
    }
    public void setmImageIndex(int imageIndex){
        mImageIndex = imageIndex;
    }
}
