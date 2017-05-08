// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.util.StringUtils;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class NetflixComWatchHandler$2 extends SimpleManagerCallback
{
    final /* synthetic */ NetflixComWatchHandler this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ PlayContext val$playContext;
    final /* synthetic */ Playable val$playable;
    final /* synthetic */ String val$targetDialUuid;
    final /* synthetic */ VideoType val$videoType;
    
    NetflixComWatchHandler$2(final NetflixComWatchHandler this$0, final VideoType val$videoType, final Playable val$playable, final NetflixActivity val$activity, final String val$targetDialUuid, final PlayContext val$playContext) {
        this.this$0 = this$0;
        this.val$videoType = val$videoType;
        this.val$playable = val$playable;
        this.val$activity = val$activity;
        this.val$targetDialUuid = val$targetDialUuid;
        this.val$playContext = val$playContext;
    }
    
    @Override
    public void onScenePositionFetched(final int n, final Status status) {
        if (status.isSucces() && n >= 0 && Log.isLoggable()) {
            Log.d("NetflixComWatchHandler", String.format("%s:%s scene: %s, position: %d", this.val$videoType, this.val$playable.getPlayableId(), this.this$0.scene, n));
            this.this$0.startTimeSeconds = n;
        }
        this.this$0.play(this.val$activity, this.val$playable, this.val$targetDialUuid, this.val$playContext);
    }
}
