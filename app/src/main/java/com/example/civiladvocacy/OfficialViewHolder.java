package com.example.civiladvocacy;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class OfficialViewHolder extends RecyclerView.ViewHolder {
    TextView official_name;
    TextView official_post;
    ImageView official_photo;

    OfficialViewHolder(View view){
        super(view);
        official_name =view.findViewById(R.id.official_name_list_vid);
        official_photo = view.findViewById(R.id.official_photo_list_vid);
        official_post = view.findViewById(R.id.official_title_list_vid);
    }
}
