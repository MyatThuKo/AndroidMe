package com.example.androidme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

// This activity is responsible for displaying the master list of all images
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_LinearLayout) != null) {
            mTwoPane = true;

            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(3);

            if (savedInstanceState == null) {
                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legFragment = new BodyPartFragment();

                int headIndex = getIntent().getIntExtra("headIndex", 0);
                int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
                int legIndex = getIntent().getIntExtra("legIndex", 0);

                headFragment.setImageIds(AndroidImageAssets.getHeads());
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                legFragment.setImageIds(AndroidImageAssets.getLegs());

                headFragment.setListIndex(headIndex);
                bodyFragment.setListIndex(bodyIndex);
                legFragment.setListIndex(legIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .add(R.id.body_container, bodyFragment)
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {

        int bodyPartNumber = position / 12;

        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane == true) {

            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;

                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;

                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
            }

        } else {

            Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;

                case 1:
                    bodyIndex = listIndex;
                    break;

                case 2:
                    legIndex = listIndex;
                    break;

                default:
                    break;
            }

            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}