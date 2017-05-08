// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.Menu;
import android.content.Context;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import android.os.Bundle;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;

public class MementoMovieDetailsActivity extends MovieDetailsActivity
{
    private static final String TAG = "MementoMovieDetailsActivity";
    
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
        if (b) {
            final Intent showIntent = HomeActivity.createShowIntent(this);
            showIntent.putExtra("expandMinPlayer", true);
            this.startActivity(showIntent);
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().getAttributes().width = BarkerUtils.getDetailsPageContentWidth((Context)this);
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
    }
    
    @Override
    protected void showMiniPlayer() {
    }
}
