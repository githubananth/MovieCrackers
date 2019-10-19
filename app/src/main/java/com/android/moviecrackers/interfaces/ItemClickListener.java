package com.android.moviecrackers.interfaces;

import android.view.View;

public interface ItemClickListener {
    // Get the current view and position of the adapter view from the Recycler view
    void onItemClick(View view, int position);
}
