package com.example.avadhyadav.extra3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Avadh Yadav on 12/8/2015.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder>  {

    private List<Image> contactList;

    public ContactAdapter(List<Image> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);
        itemView.setOnLongClickListener(new LongPressListener());
        return new viewHolder(itemView);
    }

    public void onBindViewHolder(viewHolder viewHolder, int i) {

        Bitmap mb = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.image );

        Image ci = contactList.get(i);
        viewHolder.Photo_Id.setImageResource(R.drawable.image);
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        protected ImageView Photo_Id;


        public viewHolder(View v) {
            super(v);
            Photo_Id =  (ImageView) v.findViewById(R.id.card_thumbnail_image);

        }
    }


}
