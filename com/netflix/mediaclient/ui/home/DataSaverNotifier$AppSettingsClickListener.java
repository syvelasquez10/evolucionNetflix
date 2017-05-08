// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.ui.settings.SettingsActivity;
import android.view.View;
import android.app.Activity;
import android.view.View$OnClickListener;

class DataSaverNotifier$AppSettingsClickListener implements View$OnClickListener
{
    private final Activity activity;
    
    public DataSaverNotifier$AppSettingsClickListener(final Activity activity) {
        this.activity = activity;
    }
    
    public void onClick(final View view) {
        this.activity.startActivity(SettingsActivity.createStartIntent(this.activity));
    }
}
