package vatsal.gallery;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<String> itemList = new ArrayList<String>();
    ContentResolver mContentResolver;
    ArrayList<String> p;
    private final LayoutInflater layoutInflater;
    public ImageAdapter(Context context){
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
         p =  getImagePath(mContext);
    }

    void add(String path){
        itemList.add(path);
    }

    @Override
    public int getCount() {
        return p.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(185,185));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else {
            imageView = (ImageView)view;
        }

       //ArrayList<String> p =  getImagePath(mContext);
        Glide.with(mContext).load(p.get(position)).into(imageView);
        //imageView.setImageResource(mThumbID[position]);
        return imageView;
    }

//    public Integer[] mThumbID = {
//            R.drawable.one,
//            R.drawable.two,
//            R.drawable.three
//    };


    public static ArrayList<String> getImagePath(Context context){
        Uri uri;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        String PathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        };

        cursor = context.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(PathOfImage);
        }
        cursor.close();
        return listOfAllImages;
    }


}
