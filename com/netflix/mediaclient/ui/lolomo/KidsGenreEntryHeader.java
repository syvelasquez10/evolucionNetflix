// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsGenreEntryHeader extends RelativeLayout
{
    private final PressedStateHandler pressHandler;
    
    public KidsGenreEntryHeader(final Activity activity) {
        super((Context)activity);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, (int)(DeviceUtils.getScreenWidthInPixels((Context)activity) * 0.5625f)));
        activity.getLayoutInflater().inflate(2130903093, (ViewGroup)this, true);
        this.pressHandler = new PressedStateHandler((View)this);
        this.setOnClickListener((View$OnClickListener)new KidsUtils.OnSwitchToKidsClickListener(activity, UIViewLogging.UIViewCommandName.genreKidsEntry));
    }
    
    protected void dispatchSetPressed(final boolean b) {
        this.pressHandler.handleSetPressed(b);
        super.dispatchSetPressed(b);
    }
}
