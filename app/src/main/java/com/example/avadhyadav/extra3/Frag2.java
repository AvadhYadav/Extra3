package com.example.avadhyadav.extra3;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by Avadh Yadav on 12/18/2015.
 */
public class Frag2 extends Fragment {
    Context context;

    ArrayList<String> ContactName = MainActivity.ContactName;
    ArrayList<String> ContactNumber = MainActivity.ContactNumber;
    ArrayList<String> ContactEmail = MainActivity.ContactEmail;

    ArrayList<String> ContactName1 = new ArrayList<>();
    ArrayList<String> ContactNumber1 = new ArrayList<>();

    public Frag2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflater1 = inflater.inflate(R.layout.frag2, container, false);

        SearchView search = (SearchView)inflater1.findViewById(R.id.searchView);
        Log.v("No. of Numbers", ContactNumber.size() + " ");
        Log.v("No. of Names", ContactName.size() + " ");

        ArrayList<Contact> contact = new ArrayList<Contact>();
        RecyclerView recyclerView = (RecyclerView) inflater1.findViewById(R.id.contact_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        for(int i=0;i<ContactNumber.size();i++) {
            Contact ci = new Contact();
            ci.contact_pic = R.drawable.image;
            ci.contact_name = ContactName.get(i);
            ci.contact_number = ContactNumber.get(i);
//            ci.contact_email = ContactEmail.get(i);
            contact.add(ci);
        }
        AddressAdapter addressAdapter = new AddressAdapter(contact);
        recyclerView.setAdapter(addressAdapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("New Text Entered", newText);
                return false;
            }
        });

        return inflater1;
    }

}
