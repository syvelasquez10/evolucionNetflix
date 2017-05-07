// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.TimeInterpolator;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.util.DeviceCategory;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;
import java.util.List;
import android.view.animation.DecelerateInterpolator;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.Log;

class PostPlay$6 implements Runnable
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$6(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.mContext.getState().noUserInteraction()) {
            Log.d("nf_postplay", "Interrupter process, there was user interaction in meantime. Do nothing");
            return;
        }
        if (this.this$0.mContext.getScreen().getState() == PlayerUiState.Loading) {
            Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, but after 2 minutes we are still loading, postpone for 2 more minutes");
            this.this$0.mContext.getHandler().postDelayed((Runnable)this, 120000L);
            return;
        }
        if (this.this$0.mInterrupter != null) {
            Log.d("nf_postplay", "This is 3rd consecutive auto play with no user interaction, after 2 minutes start interrupter mode");
            this.this$0.mContext.doPause();
            this.this$0.mInterrupter.setVisibility(0);
            this.this$0.mContext.getScreen().moveToState(PlayerUiState.Interrupter);
            this.this$0.mContext.getHandler().postDelayed(this.this$0.onInterrupterDismiss, (long)this.this$0.mInterrputerTimeoutOffset);
            return;
        }
        Log.w("nf_postplay", "Interrupter UI NOT found, this should not happen!");
    }
}
