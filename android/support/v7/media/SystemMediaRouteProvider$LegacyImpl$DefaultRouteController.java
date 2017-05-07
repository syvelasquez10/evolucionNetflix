// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.res.Resources;
import java.util.Collection;
import android.support.v7.mediarouter.R$string;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.media.AudioManager;
import android.content.IntentFilter;
import java.util.ArrayList;

final class SystemMediaRouteProvider$LegacyImpl$DefaultRouteController extends MediaRouteProvider$RouteController
{
    final /* synthetic */ SystemMediaRouteProvider$LegacyImpl this$0;
    
    SystemMediaRouteProvider$LegacyImpl$DefaultRouteController(final SystemMediaRouteProvider$LegacyImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onSetVolume(final int n) {
        this.this$0.mAudioManager.setStreamVolume(3, n, 0);
        this.this$0.publishRoutes();
    }
    
    @Override
    public void onUpdateVolume(final int n) {
        final int streamVolume = this.this$0.mAudioManager.getStreamVolume(3);
        if (Math.min(this.this$0.mAudioManager.getStreamMaxVolume(3), Math.max(0, streamVolume + n)) != streamVolume) {
            this.this$0.mAudioManager.setStreamVolume(3, streamVolume, 0);
        }
        this.this$0.publishRoutes();
    }
}
