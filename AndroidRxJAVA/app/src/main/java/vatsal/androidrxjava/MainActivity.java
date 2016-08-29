package vatsal.androidrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(myAdapter);

        createObservable();
    }

    private void createObservable() {

        /*
        here i have created Observable object which emits the data.Data will come from getColorList method.Now,when we reqired
        this data, we can observe the observable object by calling subscribe method.
         */
        Observable<List<String>> listObservable = Observable.just(getColorList());

        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                myAdapter.setString(strings);
            }
        });
    }

    private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        colors.add("chartreuse");
        colors.add("Van Dyke Brown");
        return colors;
    }
}
