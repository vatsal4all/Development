package vatsal.androidrxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vatsalpatel on 16-08-29.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context mContext;
    private List<String> mString = new ArrayList<>();


    public MyAdapter (Context context) {
        this.mContext = context;
    }

    public void setString(List<String> newStrings){
        mString.clear();
        mString.addAll(newStrings);
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        holder.rv_text.setText(mString.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mString.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView rv_text;

        public ViewHolder(View itemView) {
            super(itemView);
            rv_text = (TextView) itemView.findViewById(R.id.rv_text);
        }
    }
}
