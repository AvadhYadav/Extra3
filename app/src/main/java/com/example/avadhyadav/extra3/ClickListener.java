package com.example.avadhyadav.extra3;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Avadh Yadav on 12/26/2015.
 */
public class ClickListener implements View.OnClickListener {

    ShareListener mShare;

    @Override
    public void onClick(View v) {
        mShare.ShareIt(1);
    }

    public interface ShareListener{
        void ShareIt(int x);
    }
}
