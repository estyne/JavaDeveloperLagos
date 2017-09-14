package com.learnites.javadevelopers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Devs> devsList;
    private Devs mydev;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView messageView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.profile_pix);
            messageView = (TextView) view.findViewById(R.id.info);
            relativeLayout= (RelativeLayout) view.findViewById(R.id.mycontainer);

        }
    }


    public RecyclerAdapter(List<Devs> offersList) {
        this.devsList = offersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        mydev = devsList.get(position);
        holder.messageView.setText(mydev.getLogin());

        Picasso.with(holder.imageView.getContext()).load(mydev.getAvatar()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return devsList.size();
    }
}