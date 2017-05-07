// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.social.FacebookLoginActivity;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;

public class VideoDetailsClickListener implements View$OnClickListener, View$OnLongClickListener
{
    private static final String TAG = "VideoDetailsClickListener";
    private final NetflixActivity activity;
    private final PlayContextProvider playContextProvider;
    
    public VideoDetailsClickListener(final NetflixActivity activity, final PlayContextProvider playContextProvider) {
        this.activity = activity;
        this.playContextProvider = playContextProvider;
    }
    
    public void onClick(final View view) {
        final Object tag = view.getTag(2131427339);
        if (tag == null) {
            Log.w("VideoDetailsClickListener", "No video details for click listener to use");
            return;
        }
        final Video video = (Video)tag;
        if (VideoType.SOCIAL_POPULAR.equals(video.getType())) {
            Log.v("VideoDetailsClickListener", "Launching connect with facebook activity...");
            FacebookLoginActivity.show((Activity)view.getContext());
            return;
        }
        DetailsActivityLauncher.show(this.activity, video, this.playContextProvider.getPlayContext(), "DeetsClickListener");
    }
    
    public boolean onLongClick(final View view) {
        final Object tag = view.getTag(2131427339);
        if (tag == null) {
            return false;
        }
        Toast.makeText((Context)this.activity, (CharSequence)((Video)tag).getTitle(), 0).show();
        return true;
    }
    
    public void remove(final View view) {
        Log.v("VideoDetailsClickListener", "Removing click listeners");
        view.setOnClickListener((View$OnClickListener)null);
        view.setOnLongClickListener((View$OnLongClickListener)null);
        view.setTag(2131427339, (Object)null);
    }
    
    public void update(final View view, final Video video) {
        if (Log.isLoggable()) {
            Log.v("VideoDetailsClickListener", "Adding click listeners for: " + video.getTitle() + ", trackId: " + this.playContextProvider.getPlayContext().getTrackId());
        }
        view.setOnClickListener((View$OnClickListener)this);
        view.setOnLongClickListener((View$OnLongClickListener)this);
        view.setTag(2131427339, (Object)video);
    }
}
