// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.View$OnClickListener;
import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils$OnSwitchToKidsClickListener;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsGenreEntryHeader extends RelativeLayout
{
    private final PressedStateHandler pressHandler;
    
    public KidsGenreEntryHeader(final NetflixActivity netflixActivity) {
        super((Context)netflixActivity);
        final int actionBarHeight = netflixActivity.getActionBarHeight();
        final boolean portrait = DeviceUtils.isPortrait((Context)netflixActivity);
        int n;
        if (portrait) {
            n = AndroidUtils.dipToPixels((Context)netflixActivity, 180);
        }
        else {
            n = AndroidUtils.dipToPixels((Context)netflixActivity, 215);
        }
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, n + actionBarHeight));
        this.setPadding(0, actionBarHeight, 0, 0);
        netflixActivity.getLayoutInflater().inflate(2130903102, (ViewGroup)this, true);
        final View viewById = this.findViewById(2131165413);
        int backgroundResource;
        if (portrait) {
            backgroundResource = 2130837707;
        }
        else {
            backgroundResource = 2130837706;
        }
        viewById.setBackgroundResource(backgroundResource);
        this.pressHandler = new PressedStateHandler((View)this);
        this.setOnClickListener((View$OnClickListener)new KidsUtils$OnSwitchToKidsClickListener(netflixActivity, UIViewLogging$UIViewCommandName.genreKidsEntry));
    }
    
    protected void dispatchSetPressed(final boolean b) {
        this.pressHandler.handleSetPressed(b);
        super.dispatchSetPressed(b);
    }
}
