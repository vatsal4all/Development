package vatsal.gallery.Ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import vatsal.gallery.R;

public class GalleryAdapter extends PagerAdapter {
    private static final int PHOTO_POSITION = 0;
    private static final int VIDEO_POSITION = 1;
    private final List<Page> pages;
    private final LayoutInflater layoutInflater;
    private final ViewPager mPager;
    private photo mPhoto;
    private video mVideo;
    GridView gridView;
    Context context;


    public GalleryAdapter(ViewPager viewPager) {
        context = viewPager.getContext();

        this.mPager = viewPager;
        layoutInflater = LayoutInflater.from(context);

        pages = new ArrayList<>(2);
        pages.add(PHOTO_POSITION, new Page(R.layout.photo,
                "Photo"));
        pages.add(VIDEO_POSITION, new Page(R.layout.video,
                "Video"));

    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        Page page = pages.get(position);
        View view = layoutInflater.inflate(page.layoutId, container, false);
        container.addView(view);

        gridView = (android.widget.GridView) view.findViewById(R.id.photo_gridView);


        if(gridView != null){
            gridView.setAdapter(new ImageAdapter(context));
        }

        if (view instanceof photo) {
            mPhoto = (photo) view;
            mPhoto.setAdapter(this);
        }
        else if (view instanceof video) {
            mVideo = (video) view;
            mVideo.setAdapter(this);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).title;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    private static final class Page {
        private final int layoutId;
        @NonNull
        private final String title;

        private Page(final int layoutId, @NonNull final String title) {
            this.layoutId = layoutId;
            this.title = title;
        }
    }

}
