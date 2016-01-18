package com.example.avadhyadav.extra3;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by Avadh Yadav on 12/9/2015.
 */
class LongPressListener implements View.OnLongClickListener {


    @Override
    public boolean onLongClick(View view) {
        DragEvent dragEvent;
        final ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.VISIBLE);
        return true;
    }
}
