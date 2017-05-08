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

class SystemMediaRouteProvider$LegacyImpl extends SystemMediaRouteProvider
{
    private static final ArrayList<IntentFilter> CONTROL_FILTERS;
    final AudioManager mAudioManager;
    int mLastReportedVolume;
    private final SystemMediaRouteProvider$LegacyImpl$VolumeChangeReceiver mVolumeChangeReceiver;
    
    static {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
        intentFilter.addCategory("android.media.intent.category.LIVE_VIDEO");
        (CONTROL_FILTERS = new ArrayList<IntentFilter>()).add(intentFilter);
    }
    
    public SystemMediaRouteProvider$LegacyImpl(final Context context) {
        super(context);
        this.mLastReportedVolume = -1;
        this.mAudioManager = (AudioManager)context.getSystemService("audio");
        context.registerReceiver((BroadcastReceiver)(this.mVolumeChangeReceiver = new SystemMediaRouteProvider$LegacyImpl$VolumeChangeReceiver(this)), new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
        this.publishRoutes();
    }
    
    @Override
    public MediaRouteProvider$RouteController onCreateRouteController(final String s) {
        if (s.equals("DEFAULT_ROUTE")) {
            return new SystemMediaRouteProvider$LegacyImpl$DefaultRouteController(this);
        }
        return null;
    }
    
    void publishRoutes() {
        final Resources resources = this.getContext().getResources();
        final int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
        this.mLastReportedVolume = this.mAudioManager.getStreamVolume(3);
        this.setDescriptor(new MediaRouteProviderDescriptor$Builder().addRoute(new MediaRouteDescriptor$Builder("DEFAULT_ROUTE", resources.getString(R$string.mr_system_route_name)).addControlFilters(SystemMediaRouteProvider$LegacyImpl.CONTROL_FILTERS).setPlaybackStream(3).setPlaybackType(0).setVolumeHandling(1).setVolumeMax(streamMaxVolume).setVolume(this.mLastReportedVolume).build()).build());
    }
}
