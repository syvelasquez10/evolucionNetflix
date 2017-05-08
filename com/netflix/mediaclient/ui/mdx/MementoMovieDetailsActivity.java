// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.details.MovieDetailsActivity;

public class MementoMovieDetailsActivity extends MovieDetailsActivity
{
    private static final String TAG = "MementoMovieDetailsActivity";
    
    @Override
    protected void initSlidingPanel() {
        super.initSlidingPanel();
        this.slidingPanel.setPanelHeight(0);
    }
    
    @Override
    public void notifyMdxMiniPlayerShown(final boolean b) {
    }
    
    @Override
    protected void showMiniPlayer() {
    }
}
