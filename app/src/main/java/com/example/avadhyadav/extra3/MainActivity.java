package com.example.avadhyadav.extra3;

import android.animation.Animator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int width = 0, height = 0, number = 0;

    RelativeLayout relativeLayout ;

    public static ArrayList<String> ContactNumber = new ArrayList<String>();
    public static ArrayList<String> ContactName = new ArrayList<String>();
    public static ArrayList<String> ContactEmail = new ArrayList<String>();

    public static List<Image> result2 = new ArrayList<Image>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Load Contacts
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();
        ////////

        ////ToolBar
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        relativeLayout  = (RelativeLayout)findViewById(R.id.rel);
        ////////

        ////MainActivity Recycler View
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        for(int i=0;i<10;i++) {
            Image ci = new Image();
            ci.Photo_id = R.drawable.image;
            result2.add(ci);
        }

        ContactAdapter ca = new ContactAdapter(result2);
        recyclerView.setAdapter(ca);
        /////////


        /////For height and width of screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        ///////////

        /////// Handling Green and Pink Button on MainScreen
        final ImageButton GreenButton = (ImageButton)findViewById(R.id.imageButton);
        GreenButton.animate()
                   .x((float) 0.8 * width)
                   .y((float) 0.7 * height)
                .setDuration(1)
                .start();
        final ImageButton PinkButton = (ImageButton)findViewById(R.id.imageButton2);
        PinkButton.animate()
                .x((float)0.8*width)
                .y((float)0.7*height)
                .setDuration(1)
                .start();


        GreenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PinkButton.setVisibility(View.VISIBLE);
                GreenButton.animate()
                        .x((float) 0.8 * width)
                        .y(16)
                        .setDuration(500)
                        .start();
                PinkButton.animate()
                        .x((float) 0.8 * width)
                        .y(16)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                Log.v("Value of Button", GreenButton.getY() + " ");
                                RelativeLayout c_list = (RelativeLayout) findViewById(R.id.c_list);
                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

                                c_list.setVisibility(View.VISIBLE);

                                recyclerView.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .setDuration(500)
                        .start();
            }
        });


        PinkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PinkButton.animate()
                        .x((float) 0.8 * width)
                        .y((float) 0.7 * height)
                        .setDuration(500)
                        .start();
                GreenButton.animate()
                        .x((float) 0.8 * width)
                        .y((float) 0.7 * height)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                RelativeLayout c_list = (RelativeLayout)findViewById(R.id.c_list);
                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                                c_list.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .setDuration(500)
                        .start();
                PinkButton.setVisibility(View.INVISIBLE);
                Log.v("Value of button 2",PinkButton.getY() + " ");
            }
        });

        ///////////////


        /////////////Dragging Carviews of Main Sceen
        relativeLayout.setOnDragListener(new View.OnDragListener(){

            int num = 0;
            @Override
            public boolean onDrag(View v, DragEvent event) {


                final View view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_LOCATION:
                        float bx = GreenButton.getX()+60, by = GreenButton.getY()+80;

                        Log.v(" Shadow X and Y",event.getX() + " " + event.getY());
                        if (event.getX() > bx - 100 && event.getY() < by + 100 && event.getX() < bx + 100 && event.getY() > by - 100)
                        {
                            LongPressListener ll = new LongPressListener();
                            view.setVisibility(View.VISIBLE);
                            PinkButton.setVisibility(View.VISIBLE);
                            if(num == 0) {
                                Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                vib.vibrate(50);
                                num = 1;
                            }

                        }
                        else
                        {
                            view.setVisibility(View.VISIBLE);
                            PinkButton.setVisibility(View.INVISIBLE);
                            num = 0;

                        }
                        break;

                    case DragEvent.ACTION_DROP:
                            if(num == 1) {
                                PinkButton.setVisibility(View.INVISIBLE);
                                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                                startActivity(i);
                                num = 0;
                            }


                        break;
                }
                return true;
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //////////////Load Contacts in Background
    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // TODO Auto-generated method stub

            Log.v("Adding Contacts","true");
            Log.v("contacts initially", ContactName.size() + " ");
            ContentResolver cr = getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if(cursor.moveToFirst())
            {

                do
                {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    {
                        Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{ id }, null);
                        Cursor emailCur = cr.query(
                                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext())
                        {
                            String contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            ContactNumber.add(contactNumber);
                            String contactName = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            ContactName.add(contactName);



                            break;
                        }
                        pCur.close();
//                        while (emailCur.moveToNext()){
//                            String email = emailCur.getString(
//                                    emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//                            ContactEmail.add(email);
//                            Log.v("Email", email);
//                            break;
//                        }
                    }

                } while (cursor.moveToNext()) ;
//                Log.v("number of emails added",ContactEmail.size()+"");
            }

            Log.v("contacts added", ContactName.size() + " ");

            return ContactName;
        }
        @Override
        protected void onPostExecute(ArrayList<String> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);
        }
    }
    /////////////////////


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
