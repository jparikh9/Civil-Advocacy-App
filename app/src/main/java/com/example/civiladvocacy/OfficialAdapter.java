package com.example.civiladvocacy;


import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class OfficialAdapter extends RecyclerView.Adapter<OfficialViewHolder> {

    private MainActivity mainActivity;
    private final ArrayList<Official> officialArrayList;
    private static final String TAG = "OfficialAdapter";

    OfficialAdapter(ArrayList<Official> officialArrayList, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.officialArrayList = officialArrayList;
    }
    @NonNull
    @Override
    public OfficialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW OfficialViewHolder");

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.official_entry_list, parent, false);
        view.setOnClickListener(mainActivity);
        return new OfficialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficialViewHolder holder, int position) {

        Official official = officialArrayList.get(position);
        holder.official_post.setText(official.getOfficial_office());
        holder.official_name.setText(official.getOfficial_name());
        String imageurl = official.getOfficial_photo_url();

        if(imageurl.isEmpty()){
            Glide.with(mainActivity)
                    .load(R.drawable.missing).placeholder(R.drawable.missing)
                    .into(holder.official_photo);
            /*Picasso.get().load(R.drawable.missing).placeholder(R.drawable.missing)
                    .into(holder.official_photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            //long time = System.currentTimeMillis() - start;
                            Log.d(TAG, "onSuccess: ");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d(TAG, "onError: " + e);
                        }
                    });*/
        }
        else {
            String[] k = imageurl.split("//");
            imageurl = "https://"+ k[1];
            Glide.with(mainActivity)
                    .load(imageurl).error(R.drawable.brokenimage).placeholder(R.drawable.missing)
                    .into(holder.official_photo);

            /*Picasso.get().load(imageurl).placeholder(R.drawable.missing).error(R.drawable.brokenimage)
                    .into(holder.official_photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            //long time = System.currentTimeMillis() - start;
                            Log.d(TAG, "onSuccess: " );
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d(TAG, "onError: " + e);
                        }
                    });*/
        }

    }

    @Override
    public int getItemCount() {
        return officialArrayList.size();
    }


}
