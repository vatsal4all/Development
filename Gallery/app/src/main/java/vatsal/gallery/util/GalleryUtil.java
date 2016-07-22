package vatsal.gallery.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by Shahed on 2016-07-22. Gallery2
 */

public class GalleryUtil {

    public GalleryUtil(){
    }

   public ArrayList<String> getPaths(Context context){
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
