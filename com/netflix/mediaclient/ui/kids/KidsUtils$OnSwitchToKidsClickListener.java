// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import android.view.ViewConfiguration;
import android.graphics.drawable.Drawable;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.app.Activity;
import android.view.View$OnClickListener;

public class KidsUtils$OnSwitchToKidsClickListener implements View$OnClickListener
{
    private final Activity activity;
    private final UIViewLogging$UIViewCommandName entryName;
    
    public KidsUtils$OnSwitchToKidsClickListener(final Activity activity, final UIViewLogging$UIViewCommandName entryName) {
        this.activity = activity;
        this.entryName = entryName;
    }
    
    public void onClick(final View view) {
        this.activity.startActivity(createSwitchToKidsIntent(this.activity, this.entryName));
    }
}
