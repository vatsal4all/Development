package vatsal.gallery.Ui;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vatsal.gallery.util.GalleryUtil;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mImagePaths;
    private final LayoutInflater mLayoutInflater;

    public ImageAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mImagePaths =  new GalleryUtil().getPaths(mContext);
    }

    @Override
    public int getCount() {
        return mImagePaths.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ImageView imageView;

        DisplayMetrics displaymetrics = new DisplayMetrics();

        ((Activity) mContext).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        if(view == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(width/3,width/3));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else {
            imageView = (ImageView)view;
        }
        Glide.with(mContext)
                .load(mImagePaths.get(position))
                .centerCrop()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mImagePaths.get(position),Toast.LENGTH_LONG).show();
            }
        });
        return imageView;
    }


}
