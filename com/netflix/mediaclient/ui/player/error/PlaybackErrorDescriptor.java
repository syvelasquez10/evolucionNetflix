// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.app.Activity;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.service.error.ErrorDescriptor;

abstract class PlaybackErrorDescriptor implements ErrorDescriptor
{
    protected static final String TAG = "nf_play_error";
    protected static final String kbHelpUrl = "https://help.netflix.com/en/node/14384";
    protected static final String kbProxyUrl = "https://netflix.com/proxy";
    private AlertDialogFactory$AlertDialogDescriptor mData;
    private int mPriority;
    
    PlaybackErrorDescriptor(final int mPriority, final AlertDialogFactory$AlertDialogDescriptor mData) {
        this.mPriority = mPriority;
        this.mData = mData;
    }
    
    PlaybackErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        this(0, alertDialogFactory$AlertDialogDescriptor);
    }
    
    @Override
    public Runnable getBackgroundTask() {
        return null;
    }
    
    @Override
    public AlertDialogFactory$AlertDialogDescriptor getData() {
        return this.mData;
    }
    
    @Override
    public int getPriority() {
        return this.mPriority;
    }
    
    @Override
    public boolean shouldReportToUserAsDialog(final Activity activity) {
        return activity instanceof PlayerActivity;
    }
}
