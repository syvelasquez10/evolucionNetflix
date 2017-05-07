// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

public class NetflixActionBar$PerformUpActionOnClickListener implements View$OnClickListener
{
    private final NetflixActivity activity;
    
    public NetflixActionBar$PerformUpActionOnClickListener(final NetflixActivity activity) {
        this.activity = activity;
    }
    
    public void onClick(final View view) {
        this.activity.performUpAction();
    }
}
