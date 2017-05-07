// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.media.session.PlaybackState$Builder;
import android.media.session.MediaSession$Callback;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.media.VolumeProvider;

class RemoteVolumeController$2 extends VolumeProvider
{
    final /* synthetic */ RemoteVolumeController this$0;
    
    RemoteVolumeController$2(final RemoteVolumeController this$0, final int n, final int n2, final int n3) {
        this.this$0 = this$0;
        super(n, n2, n3);
    }
    
    public void onAdjustVolume(final int n) {
        if (Log.isLoggable("nf_remote_volume_controller", 4)) {
            Log.i("nf_remote_volume_controller", "onAdjustVolume: " + n);
        }
        if (n == 1) {
            RemoteVolumeController.access$012(this.this$0, 10);
        }
        else if (n == -1) {
            RemoteVolumeController.access$020(this.this$0, 10);
        }
        else {
            if (Log.isLoggable("nf_remote_volume_controller", 4)) {
                Log.i("nf_remote_volume_controller", "onAdjustVolume strange direction, skipping: " + n);
            }
            return;
        }
        this.this$0.updateCurrentVolume(this.this$0.mVolume, true);
    }
    
    public void onSetVolumeTo(final int n) {
        if (Log.isLoggable("nf_remote_volume_controller", 4)) {
            Log.i("nf_remote_volume_controller", "onSetVolumeTo: " + n);
        }
        this.this$0.updateCurrentVolume(n * 10, true);
    }
}
