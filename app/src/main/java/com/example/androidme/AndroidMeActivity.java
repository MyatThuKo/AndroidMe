package com.example.androidme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        FragmentManager fragment = getSupportFragmentManager();


        if (savedInstanceState == null) {
            //This is fragment for the head of android.
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            int headIndex = getIntent().getIntExtra("headInndex", 0);
            headFragment.setListIndex(headIndex);
            fragment.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            //This is fragment for the body of android.
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setListIndex(bodyIndex);
            fragment.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //This is fragment for the leg of the android.
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legFragment.setListIndex(legIndex);
            fragment.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
