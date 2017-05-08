// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class VideoDetailsViewGroup$5 extends BroadcastReceiver
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    
    VideoDetailsViewGroup$5(final VideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final NetflixActivity netflixActivitySafely = ViewUtils.getNetflixActivitySafely((View)this.this$0);
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivitySafely) && this.this$0.details != null) {
            this.this$0.updateBadges(this.this$0.details, netflixActivitySafely);
        }
    }
}
