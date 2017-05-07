// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.media.session.PlaybackState$Builder;
import android.media.session.MediaSession$Callback;
import android.content.IntentFilter;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import android.annotation.TargetApi;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class RemoteVolumeController$3 extends BroadcastReceiver
{
    final /* synthetic */ RemoteVolumeController this$0;
    
    RemoteVolumeController$3(final RemoteVolumeController this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        Log.i("nf_remote_volume_controller", "Update capabilities: " + intent);
        final String stringExtra = intent.getStringExtra("stringBlob");
        this.this$0.mIsVolumeControlSupported = false;
        try {
            final MdxTargetCapabilities mdxTargetCapabilities = new MdxTargetCapabilities(stringExtra);
            if (mdxTargetCapabilities != null) {
                this.this$0.mIsVolumeControlSupported = mdxTargetCapabilities.isVolumeControl();
            }
        }
        catch (Exception ex) {
            Log.e("nf_remote_volume_controller", "Failed to extract capability data: ", ex);
        }
    }
}
