package com.example.avadhyadav.extra3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Avadh Yadav on 12/19/2015.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.viewHolder>{

    Context context;

    private List<Contact> contactList;

    public AddressAdapter(Context context){
        this.context = context;
    }

    public AddressAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public AddressAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.search_card, viewGroup, false);
        return new viewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(viewHolder viewHolder, int i) {

        Bitmap mb = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.image);

        final Contact ci = contactList.get(i);
        viewHolder.contact_pic.setImageResource(R.drawable.image);
        viewHolder.contact_name.setText(ci.contact_name);
        viewHolder.contact_number.setText(ci.contact_number);
        viewHolder.cardView.setOnClickListener(new ClickListener());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        public View view;
        protected ImageView contact_pic;
        protected TextView contact_name;
        protected TextView contact_number;
        CardView cardView;


        public viewHolder(View v) {
            super(v);
            view = v;;
            contact_pic =  (ImageView) v.findViewById(R.id.contact_pic);
            contact_name = (TextView) v.findViewById(R.id.contact_name);
            contact_number = (TextView) v.findViewById(R.id.contact_number);
            cardView = (CardView) v.findViewById(R.id.card_search);
        }
    }


}