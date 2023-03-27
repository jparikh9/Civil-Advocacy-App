package com.example.civiladvocacy;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
    }

    @Override
    public int getItemCount() {
        return officialArrayList.size();
    }
}
