package vatsal.gallery;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import vatsal.gallery.Ui.photo;
import vatsal.gallery.Ui.video;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager mPager;
    TabLayout mTab;
    GridView mGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mPager = (ViewPager)findViewById(R.id.viewpager);
        mTab = (TabLayout)findViewById(R.id.tabs);
        setSupportActionBar(toolbar);

        Adapter adapter = new Adapter(mPager);
        mPager.setAdapter(adapter);

        mTab.addTab(mTab.newTab().setText("Photos"));
        mTab.addTab(mTab.newTab().setText("Videos"));
        mTab.setTabGravity(TabLayout.GRAVITY_FILL);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public static final class Adapter extends PagerAdapter{

        private static final int PHOTO_POSITION = 0;
        private static final int VIDEO_POSITION = 1;
        private final List<Page> pages;
        private final LayoutInflater layoutInflater;
        private final ViewPager mPager;
        private photo mPhoto;
        private video mVideo;
        GridView gridView;
        Context context;


        public Adapter(ViewPager viewPager) {
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
    }


}

