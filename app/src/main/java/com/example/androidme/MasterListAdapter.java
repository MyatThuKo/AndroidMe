package com.example.androidme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class MasterListAdapter extends BaseAdapter {

    //Keeps track of the context and the list of images to display
    private Context mContext;
    private List<Integer> mImageIDs;

    /**
     * Constructor method
     * @param mContext The context
     * @param mImageIDs The list of images to display
     */
    public MasterListAdapter(Context mContext, List<Integer> mImageIDs) {
        this.mContext = mContext;
        this.mImageIDs = mImageIDs;
    }

    @Override
    public int getCount() {
        return mImageIDs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mImageIDs.get(position));
        return imageView;
    }
}
