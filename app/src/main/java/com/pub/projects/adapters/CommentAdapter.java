package com.pub.projects.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pub.projects.R;
import com.pub.projects.activities.MainActivity;
import com.pub.projects.model.Comments;
import com.pub.projects.model.Tasks;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private Context mContext;
    private List<Comments> searchList;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.project);

        }
    }


    public CommentAdapter(Context mContext, List<Comments> searchList) {
        this.mContext = mContext;
        this.searchList = searchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Comments results = searchList.get(position);
        holder.title.setText(results.getComment());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ((MainActivity) mContext).displayView(1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
