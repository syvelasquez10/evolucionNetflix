// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;

public class VideoDetailsClickListener implements View$OnClickListener, View$OnLongClickListener
{
    private static final String TAG = "VideoDetailsClickListener";
    private final NetflixActivity activity;
    private final PlayContextProvider playContextProvider;
    private boolean useDelayedClick;
    
    public VideoDetailsClickListener(final NetflixActivity activity, final PlayContextProvider playContextProvider) {
        this.activity = activity;
        this.playContextProvider = playContextProvider;
    }
    
    public VideoDetailsClickListener(final NetflixActivity activity, final PlayContextProvider playContextProvider, final boolean useDelayedClick) {
        this.activity = activity;
        this.playContextProvider = playContextProvider;
        this.useDelayedClick = useDelayedClick;
    }
    
    protected void launchDetailsActivity(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        DetailsActivityLauncher.show(netflixActivity, video, playContext, "DeetsClickListener");
    }
    
    public void onClick(final View view) {
        final Object tag = view.getTag(2131820578);
        if (tag == null) {
            Log.w("VideoDetailsClickListener", "No video details for click listener to use");
            return;
        }
        this.launchDetailsActivity(this.activity, (Video)tag, this.playContextProvider.getPlayContext());
    }
    
    public boolean onLongClick(final View view) {
        final Object tag = view.getTag(2131820578);
        if (tag == null) {
            return false;
        }
        UserActionLogUtils.reportCustomAction(view.getContext(), "onLongClick", (IClientLogging$ModalView)null);
        Toast.makeText((Context)this.activity, (CharSequence)((Video)tag).getTitle(), 0).show();
        return true;
    }
    
    public void remove(final View view) {
        Log.v("VideoDetailsClickListener", "Removing click listeners");
        view.setOnClickListener((View$OnClickListener)null);
        view.setOnLongClickListener((View$OnLongClickListener)null);
        view.setTag(2131820578, (Object)null);
    }
    
    public void update(final View view, final Video video, final PressedStateHandler pressedStateHandler) {
        if (this.useDelayedClick) {
            view.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(pressedStateHandler, (View$OnClickListener)this));
        }
        else {
            view.setOnClickListener((View$OnClickListener)this);
        }
        view.setOnLongClickListener((View$OnLongClickListener)this);
        view.setTag(2131820578, (Object)video);
    }
}
