// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsActivity;

public class MementoBarkerMovieDetailsActivity extends BarkerMovieDetailsActivity
{
    private static final String TAG = "MementoBarkerMovieDetailsActivity";
    
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
