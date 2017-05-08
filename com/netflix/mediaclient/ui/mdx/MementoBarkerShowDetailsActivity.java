// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsActivity;

public class MementoBarkerShowDetailsActivity extends BarkerShowDetailsActivity
{
    private static final String TAG = "MementoBarkerShowDetailsActivity";
    
    @Override
    protected boolean allowTransitionAnimation() {
        return false;
    }
    
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
