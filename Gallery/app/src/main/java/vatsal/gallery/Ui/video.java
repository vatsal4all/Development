package vatsal.gallery.Ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by vatsalpatel on 16-07-20.
 */
public class video extends LinearLayout {

    private GalleryAdapter adapter;
    public video(Context context) {
        super(context);
    }

    public video(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public video(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public video(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAdapter(GalleryAdapter adapter) {
        this.adapter = adapter;
    }
}
